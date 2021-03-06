
package com.dasmatarix.multiplayer.starsector;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.lazywizard.console.Console;

import com.dasmatarix.multiplayer.MessageManager;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.HandlerNotFoundException;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.dasmatarix.multiplayer.model.ExampleObject;
import com.dasmatarix.util.Utils;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

//http://rox-xmlrpc.sourceforge.net/niotut/
//https://stackoverflow.com/questions/11745686/java-nio-client
/**
 * The Class MultiplayerServerScript.
 */
public class MultiplayerServerScript implements EveryFrameScript {

	/** The serializer. */
	MessageManager								messageManager	= StarsectorMessageManager
	        .createServerMessageManager();

	/** The Constant CYCLE_MILLIS. */
	private static final long					CYCLE_MILLIS	= 1000L;

	/** The selector. */
	private Selector							selector;

	/** The server socket channel. */
	private ServerSocketChannel					serverSocketChannel;

	/** The data map. */
	private Map<SocketChannel, Queue<byte[]>>	dataMap			= new HashMap<SocketChannel, Queue<byte[]>>();

	/** The last run. */
	private long								lastRun			= System
	        .currentTimeMillis();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		MultiplayerServerScript server = new MultiplayerServerScript(7777);
		while (true) {
			server.advance(1);
		}
	}

	/**
	 * Instantiates a new multiplayer server script.
	 *
	 * @param port the port
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MultiplayerServerScript(int port) throws IOException {

		// Selector: multiplexor of SelectableChannel objects
		selector = SelectorProvider.provider().openSelector();

		// ServerSocketChannel: selectable channel for stream-oriented listening
		// sockets
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress host = new InetSocketAddress("localhost", port);

		// Binds the channel's socket to a local address and configures the
		// socket to listen for connections
		serverSocketChannel.bind(host);

		// Adjusts this channel's blocking mode.
		serverSocketChannel.configureBlocking(false);

		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		Console.showMessage("Server started.");
	}

	/**
	 * Checks if is done.
	 *
	 * @return true when the script is finished and can be cleaned up by the
	 *         engine.
	 */
	@Override
	public boolean isDone() {
		return false;
	}

	/**
	 * Run while paused.
	 *
	 * @return whether advance() should be called while the campaign engine is
	 *         paused.
	 */
	@Override
	public boolean runWhilePaused() {
		return true;
	}

	/**
	 * Use SectorAPI.getClock() to convert to campaign days.
	 * 
	 * @param amount seconds elapsed during the last frame.
	 */
	@Override
	public void advance(float amount) {
		try {
			if (System.currentTimeMillis() - lastRun > CYCLE_MILLIS) {

				// Selects a set of keys whose corresponding channels are ready
				// for
				// I/O operations
				selector.select(10L);

				// token representing the registration of a SelectableChannel
				// with a
				// Selector
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();

					// Tests whether this key's channel is ready to accept a new
					// socket connection
					if (key.isAcceptable()) {
						accept();
						// Tests whether this key's channel is ready for reading
					} else if (key.isReadable() && key.channel().isOpen()) {
						read(key);
					} else if (key.isWritable() && key.channel().isOpen()) {

						// TODO send messages
						write(key);
					}
				}
				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Console.showMessage(e);
		}
	}

	/**
	 * Write data to a client 'key'.
	 *
	 * @param key the key
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		LinkedList<byte[]> pendingData = (LinkedList<byte[]>) this.dataMap
		        .get(channel);
		try {
			if (pendingData != null) {
				Iterator<byte[]> items = pendingData.iterator();
				while (items.hasNext()) {
					byte[] item = items.next();
					items.remove();
					// TODO convert pendingData to a ByteBuffer so wrap doesn't
					// have to be performed here
					channel.write(ByteBuffer.wrap(item));
					Console.showMessage("Sending message: " + item.length
					        + " bytes. Message: "
					        + Utils.bytesToHexString(item));
				}
			}
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			Console.showMessage("Client disconnected.");
			key.cancel();
			channel.close();
			return;
		}
		key.interestOps(SelectionKey.OP_READ);
	}

	/**
	 * Queue a message to send to a client.
	 *
	 * @param key    the client key to send this message to
	 * @param object the object
	 * @param clazz  the clazz
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception   the exception
	 */
	public void send(SelectionKey key, Object object,
	        @SuppressWarnings("rawtypes") Class clazz)
	        throws IOException, Exception {

		// TODO optimize, 1 object so as to avoid System.arraycopy()
		byte[] body = messageManager.serialize(object, clazz);
		byte[] header = messageManager.getHeader(body, clazz);

		byte[] bytes = new byte[header.length + body.length];

		System.arraycopy(body, 0, bytes, MessageSerializer.HEADER_LENGTH,
		        body.length);
		System.arraycopy(header, 0, bytes, 0, header.length);

		try {
			if (!(key.channel() instanceof ServerSocketChannel)) {
				SocketChannel channel = (SocketChannel) key.channel();
				Queue<byte[]> pendingData = this.dataMap.get(channel);
				if (pendingData == null) {
					pendingData = new LinkedList<byte[]>();
				}

				pendingData.add(bytes);
				dataMap.put(channel, pendingData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read the incoming data, close the connection cleanly if the client closes
	 * the connection.
	 *
	 * @param key the key
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 * @throws SerializerNotFoundException
	 * @throws HandlerNotFoundException 
	 */
	private void read(SelectionKey key)
	        throws IOException, SerializerNotFoundException, HandlerNotFoundException {

		SocketChannel channel = (SocketChannel) key.channel();

		ByteBuffer header = ByteBuffer.allocate((Integer.SIZE / 8) * 2);
		int messageSize = 0;
		int hashCode = 0;
		try {
			if (channel.read(header) > 0) {
				header.rewind();
				messageSize = header.getInt();
				hashCode = header.getInt();
			}
			if (messageSize > 0) {
				Console.showMessage("Message received, size: "
				        + (messageSize + MessageSerializer.HEADER_LENGTH)
				        + " bytes.");

				// if the message size is bigger than the heap size it will
				// crash
				// clearly I need to learn how to use NIO sockets better, the
				// buffer
				// is supposed to be small and only read part of a large message
				// at a time, not just allocate more memory to read all of a
				// large
				// message in one go. The problem with that is I'm not sure
				// where to
				// start with reading a message across multiple frames.
				ByteBuffer inBuf = ByteBuffer.allocate(messageSize);

				// TODO handle result
				if (channel.read(inBuf) > 0) {

					// TODO extract out
					byte[] bytes = new byte[messageSize];
					System.arraycopy(inBuf.array().clone(), 0, bytes, 0,
					        bytes.length);
					Console.showMessage("Message received: "
					        + Utils.bytesToHexString(header.array())
					        + Utils.bytesToHexString(bytes));

					messageManager.handle(bytes, hashCode);

				} else {
					// TODO did we get a bad message?
				}
			}
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			Console.showMessage("Client disconnected.");
			key.cancel();
			channel.close();
			return;
		}
		if (messageSize == -1) {
			// Remote entity shut the socket down cleanly. Do
			// the
			// same from our end and cancel the channel.
			Console.showMessage("Client disconnected.");
			key.channel().close();
			key.cancel();
			return;
		}
		key.interestOps(SelectionKey.OP_WRITE);
	}

	/**
	 * Accept a connection.
	 *
	 * @throws IOException            Signals that an I/O exception has
	 *                                occurred.
	 * @throws ClosedChannelException the closed channel exception
	 * @throws Exception              the exception
	 */
	public void accept() throws IOException, ClosedChannelException, Exception {
		SocketChannel client = serverSocketChannel.accept();

		if (client != null) {

			Console.showMessage(
			        "Connection Accepted: " + client.getLocalAddress() + "\n");

			// Adjusts this channel's blocking mode to false
			client.configureBlocking(false);

			// TODO send session start messages
			SelectionKey key = client.register(selector, SelectionKey.OP_WRITE);

			// TODO delete later, this is a test
			ExampleObject expected = new ExampleObject();

			// Serialize values of primitive types
			expected.setB(true); // boolean value
			expected.setI(10); // int value
			expected.setD(10.5); // double value

			// Serialize objects of primitive wrapper types
			expected.setWb(Boolean.TRUE);
			expected.setWi(new Integer(10));
			expected.setWd(new Double(10.5));

			// Serialize various types of arrays
			expected.setIa(new int[]{1, 2, 3, 4});
			expected.setDa(new Double[]{10.5, 20.5});
			expected.setSa(new String[]{"msg", "pack", "for", "java"});
			expected.setBa(new byte[]{0x30, 0x31, 0x32}); // byte array

			// Serialize various types of other reference values
			expected.setWs("MesagePack"); // String object
			expected.setBuf(ByteBuffer.wrap(new byte[]{0x30, 0x31, 0x32})); // ByteBuffer
			                                                                // object
			expected.setBi(BigInteger.ONE); // BigInteger object

			// Serialize List object
			List<String> list = new ArrayList<String>();
			list.add("msgpack");
			list.add("for");
			list.add("java");
			expected.setDstList(list); // List object

			// Serialize Map object
			Map<String, String> map = new HashMap<String, String>();
			map.put("sadayuki", "furuhashi");
			map.put("muga", "nishizawa");
			expected.setDstMap(map); // Map object
			send(key, expected, ExampleObject.class);
		}
	}
}