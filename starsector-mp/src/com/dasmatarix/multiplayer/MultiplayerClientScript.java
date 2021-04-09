
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
import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.CargoData;
import com.thoughtworks.xstream.XStream;

//http://rox-xmlrpc.sourceforge.net/niotut/
//https://stackoverflow.com/questions/11745686/java-nio-client
/**
 * The Class MultiplayerClientScript.
 * 
 */
public class MultiplayerClientScript implements EveryFrameScript {

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
		MultiplayerClientScript client = new MultiplayerClientScript("localhost", 7777);
		while (true) {
			client.advance(1);
		}
	}

	/**
	 * Instantiates a new multiplayer client script.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MultiplayerClientScript(String host, int port) throws IOException {
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
	 * @param host the host
	 * @param port the port
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

		// channel.register(selector, SelectionKey.OP_WRITE);
		// TODO send session start messages

		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Send player fleet.
	 */
	public void sendPlayerFleet() {
		CampaignFleetAPI campaignFleet = Global.getSector().getPlayerFleet();

		String id = campaignFleet.getId();
		Vector2f location = campaignFleet.getLocation();
		List<FleetMemberAPI> fleetMembers = campaignFleet
		        .getMembersWithFightersCopy();

		StarSystemAPI starSystem = campaignFleet.getStarSystem();
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
			Console.showMessage("Sending " + item.length + " bytes.");
		}
		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Send data to the server.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @throws IOException            Signals that an I/O exception has
	 *                                occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void send(Object object, @SuppressWarnings("rawtypes") Class clazz)
	        throws IOException, ClassNotFoundException {
		byte[] bytes = Serializer.serialize(object, clazz);
		Console.showMessage("Creating message: " + object.toString());
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

					// TODO remove, just for testing for now
					CargoAPI cargo;
					if (Global.getSector() != null) {
						cargo = Global.getSector().getPlayerFleet().getCargo();
					} else {
						cargo = new CargoData(true);
						cargo.getCredits().add(12345F);
					}

					// TODO send messages
					send(cargo, CargoData.class);
					
					write();
				}

				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Console.showMessage(e);
		}
	}
	
	ByteBuffer header;
	ByteBuffer inBuf;
	
	/**
	 * Read data from the server.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void read() throws IOException {
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
				Console.showMessage("Message received, size: " + messageSize + " bytes.");
				inBuf = ByteBuffer.allocate(messageSize);

				// TODO handle result
				if (channel.read(inBuf) > 0) {

					// TODO extract out
					byte[] bytes = Utils.gzipUncompress(inBuf.array());
					String result = new String(bytes);
					Console.showMessage("Message received, message: " + result);
					
					if (hashCode == CampaignFleet.class.getName().hashCode()) {
						CampaignFleet campaignFleet = (CampaignFleet)Serializer.deserialize(bytes);
					} else if (hashCode == CargoData.class.getName().hashCode()) {
						CargoData cargoData = (CargoData)Serializer.deserialize(bytes);
					}

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