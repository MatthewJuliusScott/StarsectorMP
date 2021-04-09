
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;

//http://rox-xmlrpc.sourceforge.net/niotut/
//https://stackoverflow.com/questions/11745686/java-nio-client
/**
 * The Class MultiplayerClientScript.
 * 
 */
public class MultiplayerClientScript implements EveryFrameScript {

	private static final long	CYCLE_MILLIS	= 1000L;

	/** The host. */
	private String				host			= "localhost";

	/** The port. */
	private int					port			= 7777;

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
	 * @throws IOException          Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args)
	        throws IOException, InterruptedException {
		MultiplayerClientScript client = new MultiplayerClientScript();
		while (true) {
			client.advance(1);
		}
	}

	/**
	 * Instantiates a new multiplayer client script.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MultiplayerClientScript() throws IOException {
		Console.showMessage("Connecting to Server on port " + port + "...");
		open(host, port);
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
	 * @param timeout the timeout
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void open(String host, int port) throws IOException {
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
	 * @param bytes the bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write() throws IOException {

		Iterator<byte[]> items = pendingData.iterator();
		while (items.hasNext()) {
			byte[] item = items.next();
			items.remove();
			channel.write(ByteBuffer.wrap(item));
			Console.showMessage("Sending message: " + new String(item).trim());
		}
		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Send data to the server.
	 *
	 * @param bytes the bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void send(byte[] bytes) throws IOException {
		pendingData.add(Utils.createPacket(bytes));
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
					send(("" + System.currentTimeMillis()).getBytes());
					write();
				}

				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Console.showMessage(e);
		}
	}

	/**
	 * Read data from the server.
	 *
	 * @param key the key
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void read() throws IOException {
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