package com.dasmatarix.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import com.dasmatarix.multiplayer.Network.AddPlayer;
import com.dasmatarix.multiplayer.Network.Login;
import com.dasmatarix.multiplayer.Network.Register;
import com.dasmatarix.multiplayer.Network.RegistrationRequired;
import com.dasmatarix.multiplayer.Network.RemovePlayer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class StarsectorServer {
	// This holds per connection state.
	static class PlayerConnection extends Connection {
		public Player player;
	}

	public static void main(String[] args) throws IOException {
		Log.set(Log.LEVEL_DEBUG);
		new StarsectorServer();
	}

	Server			server;

	HashSet<Player>	loggedIn	= new HashSet<Player>();

	public StarsectorServer() throws IOException {
		server = new Server() {
			@Override
			protected Connection newConnection() {
				// By providing our own connection implementation, we can store per
				// connection state without a connection ID to state look up.
				return new PlayerConnection();
			}
		};

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);

		server.addListener(new Listener() {
			@Override
			public void disconnected(Connection c) {
				PlayerConnection connection = (PlayerConnection) c;
				if (connection.player != null) {
					loggedIn.remove(connection.player);

					RemovePlayer removePlayer = new RemovePlayer();
					removePlayer.id = connection.player.id;
					server.sendToAllTCP(removePlayer);
				}
			}

			private boolean isValid(String value) {
				if (value == null) {
					return false;
				}
				value = value.trim();
				if (value.length() == 0) {
					return false;
				}
				return true;
			}

			@Override
			public void received(Connection c, Object object) {
				// We know all connections for this server are actually PlayerConnections.
				PlayerConnection connection = (PlayerConnection) c;
				Player player = connection.player;

				if (object instanceof Login) {
					// Ignore if already logged in.
					if (player != null) {
						return;
					}

					// Reject if the name is invalid.
					String name = ((Login) object).name;
					if (!isValid(name)) {
						c.close();
						return;
					}

					// Reject if already logged in.
					for (Player other : loggedIn) {
						if (other.name.equals(name)) {
							c.close();
							return;
						}
					}

					player = loadPlayer(name);

					// Reject if couldn't load player.
					if (player == null) {
						c.sendTCP(new RegistrationRequired());
						return;
					}

					loggedIn(connection, player);
					return;
				}

				if (object instanceof Register) {
					// Ignore if already logged in.
					if (player != null) {
						return;
					}

					Register register = (Register) object;

					// Reject if the login is invalid.
					if (!isValid(register.name)) {
						c.close();
						return;
					}
					if (!isValid(register.otherStuff)) {
						c.close();
						return;
					}

					// Reject if player alread exists.
					if (loadPlayer(register.name) != null) {
						c.close();
						return;
					}

					player = new Player();
					player.name = register.name;
					if (!savePlayer(player)) {
						c.close();
						return;
					}

					loggedIn(connection, player);
					return;
				}
			}
		});
		server.bind(Network.port);
		server.start();
	}

	Player loadPlayer(String name) {
		File file = new File("players", name.toLowerCase());
		if (!file.exists()) {
			return null;
		}
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream(file));
			Player player = new Player();
			player.id = input.readInt();
			player.name = name;
			input.close();
			return player;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ignored) {
			}
		}
	}

	void loggedIn(PlayerConnection c, Player player) {
		c.player = player;

		// Add existing players to new logged in connection.
		for (Player other : loggedIn) {
			AddPlayer addPlayer = new AddPlayer();
			addPlayer.player = other;
			c.sendTCP(addPlayer);
		}

		loggedIn.add(player);

		// Add logged in player to all connections.
		AddPlayer addPlayer = new AddPlayer();
		addPlayer.player = player;
		server.sendToAllTCP(addPlayer);
	}

	boolean savePlayer(Player player) {
		File file = new File("players", player.name.toLowerCase());
		file.getParentFile().mkdirs();

		if (player.id == 0) {
			String[] children = file.getParentFile().list();
			if (children == null) {
				return false;
			}
			player.id = children.length + 1;
		}

		DataOutputStream output = null;
		try {
			output = new DataOutputStream(new FileOutputStream(file));
			output.writeInt(player.id);
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				output.close();
			} catch (IOException ignored) {
			}
		}
	}
}