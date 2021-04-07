
package com.dasmatarix.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

public class MultiplayerClientScript implements EveryFrameScript {

	private Socket			clientSocket;

	private PrintWriter		out;

	private BufferedReader	in;

	private boolean			connected	= false;

	private long			lastRun		= System.currentTimeMillis();

	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(
		        new InputStreamReader(clientSocket.getInputStream()));
	}

	public void sendMessage(String msg) throws IOException {
		out.println(msg);
	}
	
	public String readMessage() throws IOException {
		String resp = in.readLine();
		return resp;
	}

	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}

	private String	host	= "127.0.0.1";

	private int		port	= 7777;

	public MultiplayerClientScript() throws IOException {
		try {
			startConnection(host, port);
			connected = true;
		} catch (IOException e) {
			Console.showMessage(e.getMessage());
		}
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public boolean runWhilePaused() {
		return true;
	}

	private int counter = 0;

	@Override
	public void advance(float amount) {
		if (System.currentTimeMillis() - lastRun > 10000L) {
			if (connected) {
				if (counter < 100) {
					try {
						sendMessage("" + counter);
					} catch (IOException e) {
						Console.showMessage(e.getMessage());
					}
				} else if (counter == 100) {
					try {
						sendMessage(".");
						stopConnection();
						connected = false;
					} catch (IOException e) {
						Console.showMessage(e.getMessage());
					}
				}
				counter++;
			}
			lastRun = System.currentTimeMillis();
		}
	}
}