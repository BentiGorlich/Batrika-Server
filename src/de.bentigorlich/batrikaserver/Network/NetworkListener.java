package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.Main;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class NetworkListener implements Runnable
{
	ServerSocket server;
	Thread       t;

	public NetworkListener(int port)
	{
		try
		{
			server = new ServerSocket(port);
			t = new Thread(this);
			t.start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("waiting for devices to connect...");
				Socket clientSocket = server.accept();
				System.out.println("one connected from " + clientSocket.getRemoteSocketAddress().toString());
				Client c            = new Client(clientSocket);
				Main.connectedDevices.add(c);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void stop()
	{
		t.interrupt();
	}
}
