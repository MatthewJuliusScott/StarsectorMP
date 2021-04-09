
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
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.Set;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.CargoData;
import com.thoughtworks.xstream.XStream;

//http://rox-xmlrpc.sourceforge.net/niotut/
//https://stackoverflow.com/questions/11745686/java-nio-client
/**
 * The Class MultiplayerServerScript.
 */
public class MultiplayerServerScript implements EveryFrameScript {

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

	/** The x stream. */
	private XStream								xStream			= Serializer
	        .createXStream();

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
	 * @param port 
	 *
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

						// TODO send session start messages

						accept();
						// Tests whether this key's channel is ready for reading
					} else if (key.isReadable() && key.channel().isOpen()) {
						read(key);
					} else if (key.isWritable() && key.channel().isOpen()) {

						// delete, this is a test
						CargoData cargo;
						if (Global.getSector() != null) {
							cargo = (CargoData) Global.getSector()
							        .getPlayerFleet().getCargo();
						} else {
							cargo = new CargoData(true);
							cargo.getCredits().add(54321F);
						}
						
						// TODO send messages
						send(key, cargo, CargoData.class);

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
					Console.showMessage("Sending " + item.length + " bytes.");
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
	 * @throws IOException            Signals that an I/O exception has
	 *                                occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void send(SelectionKey key, Object object,
	        @SuppressWarnings("rawtypes") Class clazz)
	        throws IOException, ClassNotFoundException {

		byte[] bytes = Serializer.serialize(object, clazz);

		try {
			if (!(key.channel() instanceof ServerSocketChannel)) {
				SocketChannel channel = (SocketChannel) key.channel();
				Queue<byte[]> pendingData = this.dataMap.get(channel);
				if (pendingData == null) {
					pendingData = new LinkedList<byte[]>();
				}
				Console.showMessage("Creating message: " + object.toString());
				pendingData.add(bytes);
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
				Console.showMessage("Message received, size: " + messageSize + " bytes.");
				ByteBuffer inBuf = ByteBuffer.allocate(messageSize);

				// TODO handle result
				if (channel.read(inBuf) > 0) {

					// TODO extract out
					byte[] bytes = Utils.gzipUncompress(inBuf.array());
					String result = new String(bytes);
					Console.showMessage("Message received: " + result);

					if (hashCode == CampaignFleet.class.getName().hashCode()) {
						CampaignFleet campaignFleet = (CampaignFleet) Serializer
						        .deserialize(bytes);
					} else if (hashCode == CargoData.class.getName()
					        .hashCode()) {
						CargoData cargoData = (CargoData) Serializer
						        .deserialize(bytes);
					}

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