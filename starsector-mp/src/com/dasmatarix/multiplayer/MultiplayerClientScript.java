
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;

public class MultiplayerClientScript implements EveryFrameScript {

	private long	lastRun	= System.currentTimeMillis();

	SocketChannel	client;
	
	public static void main(String[] args)
	        throws IOException, InterruptedException {
		MultiplayerClientScript client = new MultiplayerClientScript();
		while (true) {
			client.advance(1);
		}
	}

	public MultiplayerClientScript() throws IOException {
		InetSocketAddress host = new InetSocketAddress("localhost", 7777);
		client = SocketChannel.open(host);
		Console.showMessage("Connecting to Server on port 7777...");
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
				String s = "" + System.currentTimeMillis();
				byte[] message = s.getBytes();
				ByteBuffer buffer = ByteBuffer.wrap(message);
				client.write(buffer);

				Console.showMessage("sending: " + s);
				buffer.clear();
				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			Console.showMessage(e.getMessage());
		}
	}
}