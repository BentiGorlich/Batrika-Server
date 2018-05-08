package de.bentigorlich.batrikaserver.Entities;

import de.bentigorlich.batrikaserver.Network.Client;
import org.json.JSONObject;

public class User extends Chat
{
	private String username;
	public  Client client;

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public void send(JSONObject construct) {
		client.send(construct);
	}
}
