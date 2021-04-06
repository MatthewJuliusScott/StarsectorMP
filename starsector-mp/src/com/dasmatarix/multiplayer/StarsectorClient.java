package com.dasmatarix.multiplayer;

import java.io.IOException;

import com.dasmatarix.multiplayer.Network.AddPlayer;
import com.dasmatarix.multiplayer.Network.Login;
import com.dasmatarix.multiplayer.Network.Register;
import com.dasmatarix.multiplayer.Network.RegistrationRequired;
import com.dasmatarix.multiplayer.Network.RemovePlayer;
import com.dasmatarix.multiplayer.Network.UpdatePlayer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.fs.starfarer.api.Global;

public class StarsectorClient {
	Client client;

	public StarsectorClient(String host) {
		client = new Client();
		client.start();

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		// ThreadedListener runs the listener methods on a different thread.
		client.addListener(new ThreadedListener(new Listener() {
			@Override
			public void connected(Connection connection) {
			}

			@Override
			public void disconnected(Connection connection) {
				System.exit(0);
			}

			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof RegistrationRequired) {
					Register register = new Register();
					register.name = Global.getSector().getCharacterData().getName();
					client.sendTCP(register);
				}
				if (object instanceof AddPlayer) {
					AddPlayer msg = (AddPlayer) object;
					// do stuff with msg.player
					return;
				}

				if (object instanceof UpdatePlayer) {
					// do stuff
					return;
				}

				if (object instanceof RemovePlayer) {
					RemovePlayer msg = (RemovePlayer) object;
					// do stuff with player msg.id
					return;
				}
			}
		}));

		try {
			client.connect(5000, host, Network.port);
			// Server communication after connection can go here, or in Listener#connected().
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Login login = new Login();
		login.name = Global.getSector().getCharacterData().getName();
		client.sendTCP(login);
	}
}