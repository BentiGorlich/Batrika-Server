package de.bentigorlich.batrikaserver;

import de.bentigorlich.batrikaserver.Entities.User;
import de.bentigorlich.batrikaserver.Network.Client;
import de.bentigorlich.batrikaserver.Network.NetworkListener;

import java.util.ArrayList;

public class Main
{
	public static ArrayList<Client> connectedDevices = new ArrayList<>();
	public static ArrayList<User>   users            = new ArrayList<>();

	public static void main(String[] args)
	{
		new NetworkListener(ConfigurationManager.getInstance().getPort());
	}

	public static User getUser(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
