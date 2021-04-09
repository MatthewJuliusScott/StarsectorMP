
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;

//http://rox-xmlrpc.sourceforge.net/niotut/
//https://stackoverflow.com/questions/11745686/java-nio-client
/**
 * The Class MultiplayerServerScript.
 */
public class MultiplayerServerScript implements EveryFrameScript {
	
	private static final long					CYCLE_MILLIS	= 1000L;

	/** The selector. */
	private Selector							selector;

	/** The server socket channel. */
	private ServerSocketChannel					serverSocketChannel;

	/** The data map. */
	private Map<SocketChannel, List<byte[]>>	dataMap			= new HashMap<SocketChannel, List<byte[]>>();

	/** The last run. */
	private long								lastRun			= System
	        .currentTimeMillis();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException          Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args)
	        throws IOException, InterruptedException {
		MultiplayerServerScript server = new MultiplayerServerScript();
		while (true) {
			server.advance(1);
		}
	}

	/**
	 * Instantiates a new multiplayer server script.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MultiplayerServerScript() throws IOException {
		// Selector: multiplexor of SelectableChannel objects
		selector = SelectorProvider.provider().openSelector();

		// ServerSocketChannel: selectable channel for stream-oriented listening
		// sockets
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress host = new InetSocketAddress("localhost", 7777);

		// Binds the channel's socket to a local address and configures the
		// socket to listen for connections
		serverSocketChannel.bind(host);

		// Adjusts this channel's blocking mode.
		serverSocketChannel.configureBlocking(false);

		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		Console.showMessage("Server started.");
	}

	/**
	 * @return true when the script is finished and can be cleaned up by the
	 *         engine.
	 */
	@Override
	public boolean isDone() {
		return false;
	}

	/**
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
				selector.select();

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
					} else if (key.isReadable()) {
						read(key);
					} else if (key.isWritable()) {
						// TODO remove, just for testing for now
						send(key, ("" + System.currentTimeMillis()).getBytes());
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
	 * Write data to a client 'key'
	 *
	 * @param key the key
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> pendingData = this.dataMap.get(channel);
		if (pendingData != null) {
			Iterator<byte[]> items = pendingData.iterator();
			while (items.hasNext()) {
				byte[] item = items.next();
				items.remove();
				channel.write(ByteBuffer.wrap(item));
				Console.showMessage("Sending message: " + new String(item).trim());
			}
		}
		key.interestOps(SelectionKey.OP_READ);
	}

	/**
	 * Queue data to send to a channel
	 *
	 * @param bytes the bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void send(SelectionKey key, byte[] bytes) throws IOException {

		try {
			if (!(key.channel() instanceof ServerSocketChannel)) {
				SocketChannel channel = (SocketChannel) key.channel();
				List<byte[]> pendingData = this.dataMap.get(channel);
				if (pendingData == null) {
					pendingData = new ArrayList<byte[]>();
				}
				pendingData.add(Utils.createPacket(bytes));
				dataMap.put(channel, pendingData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read the incoming data, close the connection cleanly if the client closes
	 * connection.
	 *
	 * @param key the key
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void read(SelectionKey key) throws IOException {

		// TODO move the buffer out and reuse it, otherwise what is the
		// point of
		// a buffer!
		SocketChannel channel = (SocketChannel) key.channel();

		ByteBuffer header = ByteBuffer.allocate(Integer.SIZE / 8);
		int messageSize = 0;
		try {
			if (channel.read(header) > 0) {
				header.rewind();
				messageSize = header.getInt();
			}
			if (messageSize > 0) {
				ByteBuffer inBuf = ByteBuffer.allocate(messageSize);

				// TODO this will become deal with actual messages
				if (channel.read(inBuf) > 0) {
					String result = new String(inBuf.array());
					Console.showMessage("Message received: " + result);
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
	 * Accept a connection
	 *
	 * @throws IOException            Signals that an I/O exception has
	 *                                occurred.
	 * @throws ClosedChannelException the closed channel exception
	 */
	public void accept() throws IOException, ClosedChannelException {
		SocketChannel client = serverSocketChannel.accept();

		// Adjusts this channel's blocking mode to false
		client.configureBlocking(false);

		// Operation-set bit for read operations
		client.register(selector, SelectionKey.OP_READ);
		Console.showMessage(
		        "Connection Accepted: " + client.getLocalAddress() + "\n");
	}
}