
package com.dasmatarix.multiplayer.starsector;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * The Class MultiplayerClientScript.
 * 
 */
public class MultiplayerClientScript implements EveryFrameScript {

	/** The serializer. */
	MessageManager				messageManager	= StarsectorMessageManager
	        .createClientMessageManager();

	/** The Constant CYCLE_MILLIS. */
	private static final long	CYCLE_MILLIS	= 1000L;

	/** The pending data. */
	List<byte[]>				pendingData		= new ArrayList<byte[]>();

	/** The last run. */
	private long				lastRun			= System.currentTimeMillis();

	/** The selector. */
	private Selector			selector;

	/** The channel. */
	SocketChannel				channel;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		MultiplayerClientScript client = new MultiplayerClientScript(
		        "localhost", 7777);
		while (true) {
			client.advance(1);
		}
	}

	/**
	 * Instantiates a new multiplayer client script.
	 *
	 * @param host the host
	 * @param port the port
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MultiplayerClientScript(String host, int port) throws IOException {
		Console.showMessage("Connecting to Server on port " + port + "...");
		try {
			open(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if is done.
	 *
	 * @return true, if is done
	 */
	@Override
	public boolean isDone() {
		return false;
	}

	/**
	 * Run while paused.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean runWhilePaused() {
		return true;
	}

	/**
	 * Open.
	 *
	 * @param host the host
	 * @param port the port
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception   the exception
	 */
	public void open(String host, int port) throws IOException, Exception {
		channel = SocketChannel.open();
		channel.configureBlocking(true);
		channel.connect(new InetSocketAddress(host, port));
		while (!channel.finishConnect()) {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				Console.showMessage(e);
			}
		}
		Console.showMessage("Connected.");
		channel.configureBlocking(false);
		selector = SelectorProvider.provider().openSelector();

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
		send(expected, ExampleObject.class);

		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Close.
	 */
	public void close() {
		try {
			if (channel != null)
				channel.close();
		} catch (Exception ex) {
			// do nothing
		}
		try {
			if (selector != null)
				selector.close();
		} catch (Exception ex) {
			// do nothing
		}
	}

	/**
	 * Write binary data to the server.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write() throws IOException {

		Iterator<byte[]> items = pendingData.iterator();
		while (items.hasNext()) {
			byte[] item = items.next();
			items.remove();
			channel.write(ByteBuffer.wrap(item));
			Console.showMessage("Sending message: " + item.length
			        + " bytes. Message: " + Utils.bytesToHexString(item));
		}
		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Send data to the server.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception   the exception
	 */
	public void send(Object object, @SuppressWarnings("rawtypes") Class clazz)
	        throws IOException, Exception {

		// TODO optimize, 1 object so as to avoid System.arraycopy()
		byte[] body = messageManager.serialize(object, clazz);
		byte[] header = messageManager.getHeader(body, clazz);

		byte[] bytes = new byte[header.length + body.length];

		System.arraycopy(body, 0, bytes, MessageSerializer.HEADER_LENGTH,
		        body.length);
		System.arraycopy(header, 0, bytes, 0, header.length);
		pendingData.add(bytes);
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

				if (channel.isConnected() && channel.isOpen()) {
					read();
				}
				if (channel.isConnected() && channel.isOpen()) {

					// TODO send messages
					write();
				}

				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Console.showMessage(e);
		}
	}

	/** The header. */
	ByteBuffer	header;

	/** The in buf. */
	ByteBuffer	inBuf;

	/**
	 * Read data from the server, close the connection cleanly if the server
	 * closes the connection.
	 *
	 * @return the list
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 * @throws SerializerNotFoundException
	 * @throws HandlerNotFoundException 
	 */
	public void read() throws IOException, SerializerNotFoundException, HandlerNotFoundException {
		header = ByteBuffer.allocate((Integer.SIZE / 8) * 2);
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
				// crash clearly I need to learn how to use NIO sockets better, the
				// buffer is supposed to be small and only read part of a large message
				// at a time, not just allocate more memory to read all of a
				// large message in one go. The problem with that is I'm not sure
				// where to start with reading a message across multiple frames.
				inBuf = ByteBuffer.allocate(messageSize);

				if (channel.read(inBuf) > 0) {

					byte[] bytes = new byte[messageSize];
					System.arraycopy(inBuf.array().clone(), 0, bytes, 0,
					        bytes.length);
					Console.showMessage("Message received, message: "
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
			Console.showMessage("Server closed");
			channel.close();
			return;
		}
		if (messageSize == -1) {
			// Remote entity shut the socket down cleanly. Do
			// the
			// same from our end and cancel the channel.
			Console.showMessage("Server closed");
			channel.close();
			return;
		}
		channel.register(selector, SelectionKey.OP_WRITE);
	}
}