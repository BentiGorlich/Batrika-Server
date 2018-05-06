package batrikaserver.Network;

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
				Socket clientSocket = server.accept();
				Client c            = new Client(clientSocket);
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
