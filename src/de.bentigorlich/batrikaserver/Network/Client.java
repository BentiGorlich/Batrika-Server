package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.Entities.MessageTypes.MessageBase;

import java.net.Socket;
import java.util.ArrayList;



public class Client
{
	private ProcessInput process;
	private Socket       socket;

	public ArrayList<MessageBase> unseenMessage = new ArrayList<>();

	public Client(Socket clientSocket)
	{
		process = new ProcessInput(clientSocket);
		socket = clientSocket;
	}
}
