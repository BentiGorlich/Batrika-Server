package de.bentigorlich.batrikaserver.Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;



public class ProcessInput implements Runnable
{
	private InputStreamListener isl;
	private Socket              socket;
	private boolean             running = true;

	public ProcessInput(Socket s)
	{
		socket = s;
		try
		{
			isl = new InputStreamListener(new DataInputStream(s.getInputStream()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void stop()
	{
		running = false;
	}

	@Override
	public void run()
	{
		while(running)
		{
			String input = isl.run();
			//TODO process
		}
	}
}
