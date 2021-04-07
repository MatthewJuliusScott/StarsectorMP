
package com.dasmatarix.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

public class MultiplayerServerScript implements EveryFrameScript {

	private ServerSocket		serverSocket;

	private Socket				clientSocket;

	private PrintWriter			out;

	private BufferedReader		in;

	private static final int	port		= 7777;

	private boolean				connected	= false;

	private long			lastRun		= System.currentTimeMillis();

	public MultiplayerServerScript() throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(
		        new InputStreamReader(clientSocket.getInputStream()));
		connected = true;
	}
	
	public void sendMessage(String msg) throws IOException {
		out.println(msg);
	}
	
	public String readMessage() throws IOException {
		String resp = in.readLine();
		return resp;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		connected = false;
	}

	@Override
	public boolean runWhilePaused() {
		return true;
	}

	@Override
	public void advance(float amount) {
		if (System.currentTimeMillis() - lastRun > 10000L) {
			try {
				String inputLine;
				if (connected && (inputLine = in.readLine()) != null) {
					if (".".equals(inputLine)) {
						sendMessage("good bye");
						stopConnection();
					}
					Console.showMessage(inputLine);
					Global.getSector().getCampaignUI().addMessage(inputLine);
					sendMessage("Recieved " + inputLine);
				}
			} catch (IOException e) {
				Console.showMessage(e.getMessage());
			}
			lastRun = System.currentTimeMillis();
		}
	}
}