package com.dasmatarix.multiplayer;

import java.nio.channels.SocketChannel;

class ServerDataEvent {

	public MultiplayerServerScript	server;

	public SocketChannel			socket;

	public byte[]					data;

	public ServerDataEvent(MultiplayerServerScript server, SocketChannel socket,
	        byte[] data) {
		this.server = server;
		this.socket = socket;
		this.data = data;
	}
}