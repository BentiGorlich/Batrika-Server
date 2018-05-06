package de.bentigorlich.batrikaserver.Network;

import java.io.DataInputStream;
import java.io.IOException;



public class InputStreamListener
{
	private DataInputStream in;
	private boolean         running = true;

	public InputStreamListener(DataInputStream stream)
	{
		in = stream;
	}

	/**
	 * @return the received String or null if IOException
	 */
	public String run()
	{
		try
		{
			String input = in.readUTF();
			return input;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
