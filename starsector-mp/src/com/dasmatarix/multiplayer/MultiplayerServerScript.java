
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

public class MultiplayerServerScript implements EveryFrameScript {

	private Selector			selector;

	private ServerSocketChannel	serverSocketChannel;

	private long				lastRun	= System.currentTimeMillis();

	public static void main(String[] args)
	        throws IOException, InterruptedException {
		MultiplayerServerScript server = new MultiplayerServerScript();
		while (true) {
			server.advance(1);
		}
	}

	public MultiplayerServerScript() throws IOException {
		// Selector: multiplexor of SelectableChannel objects
		selector = Selector.open(); // selector is open here

		// ServerSocketChannel: selectable channel for stream-oriented listening
		// sockets
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress host = new InetSocketAddress("localhost", 7777);

		// Binds the channel's socket to a local address and configures the
		// socket to listen for connections
		serverSocketChannel.bind(host);

		// Adjusts this channel's blocking mode.
		serverSocketChannel.configureBlocking(false);

		int ops = serverSocketChannel.validOps();
		SelectionKey selectKey = serverSocketChannel.register(selector, ops,
		        null);
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public boolean runWhilePaused() {
		return true;
	}

	@Override
	public void advance(float amount) {
		try {
			if (System.currentTimeMillis() - lastRun > 1000L) {
				Console.showMessage(
				        "I'm a server and i'm waiting for new connection and buffer select...");
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

					// Tests whether this key's channel is ready to accept a new
					// socket connection
					if (key.isAcceptable()) {
						SocketChannel client = serverSocketChannel.accept();

						// Adjusts this channel's blocking mode to false
						client.configureBlocking(false);

						// Operation-set bit for read operations
						client.register(selector, SelectionKey.OP_READ);
						Console.showMessage("Connection Accepted: "
						        + client.getLocalAddress() + "\n");

						// Tests whether this key's channel is ready for reading
					} else if (key.isReadable()) {

						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(256);
						client.read(buffer);
						String result = new String(buffer.array()).trim();

						Console.showMessage("Message received: " + result);
						Global.getSector().getCampaignUI()
						        .addMessage("Message received: " + result);

						if (result.equals("Close")) {
							client.close();
							Console.showMessage(
							        "Closing connection to client.");
							Console.showMessage(
							        "Server will keep running. Try running client again to establish a new connection.");
						}
					}
					iterator.remove();
				}
				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			Console.showMessage(e.getMessage());
		}
	}
}