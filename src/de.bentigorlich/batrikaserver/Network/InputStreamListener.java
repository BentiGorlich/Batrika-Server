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
	public String run() throws IOException {
		String input = in.readUTF();
		System.out.println("got: " + input);
		return input;
	}
}
