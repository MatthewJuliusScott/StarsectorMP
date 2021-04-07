
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

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

				// write
				String s = "" + System.currentTimeMillis();
				byte[] message = s.getBytes();
				ByteBuffer buffer = ByteBuffer.wrap(message);
				while (buffer.hasRemaining()) {
					client.write(buffer);
				}

				// read
				ByteBuffer inBuf = ByteBuffer.allocate(1024);
				while (client.read(inBuf) > 0) {
					String result = new String(inBuf.array());
					Console.showMessage("Message received: " + result);
					Global.getSector().getCampaignUI()
					        .addMessage("Message received: " + result);
				}

				Console.showMessage("sending: " + s);
				buffer.clear();
				lastRun = System.currentTimeMillis();
			}
		} catch (Exception e) {
			Console.showMessage(e.getMessage());
		}
	}
}