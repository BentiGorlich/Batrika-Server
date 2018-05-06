package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.Entities.Messages.MessageBase;
import de.bentigorlich.batrikaserver.Entities.User;

import java.net.Socket;
import java.util.ArrayList;



public class Client
{
	private ProcessInput process;
	public  Socket       socket;
	public  User         user;
	public  boolean      isLoggedin = false;


	public ArrayList<MessageBase> unseenMessage = new ArrayList<>();

	public Client(Socket clientSocket)
	{
		process = new ProcessInput(this);
		socket = clientSocket;
	}
}
