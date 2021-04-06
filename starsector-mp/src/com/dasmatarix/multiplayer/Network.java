package com.dasmatarix.multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.fs.starfarer.rpg.Person;

// This class is a convenient place to keep things common to both the client and server.
public class Network {
	static public class AddPlayer {
		public Player player;
	}

	static public class Login {
		public String name;
	}

	static public class Register {
		public String	name;
		public String	otherStuff;
	}

	static public class RegistrationRequired {
	}

	static public class RemovePlayer {
		public int id;
	}

	static public class UpdatePlayer {
		public int id;
	}

	static public final int port = 54555;

	// This registers objects that are going to be sent over the network.
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Login.class);
		kryo.register(RegistrationRequired.class);
		kryo.register(Register.class);
		kryo.register(AddPlayer.class);
		kryo.register(UpdatePlayer.class);
		kryo.register(RemovePlayer.class);
		kryo.register(Player.class);
	}
}