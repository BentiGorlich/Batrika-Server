package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.ConfigurationManager;
import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.MessageBase;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;



public class ProcessInput implements Runnable
{
	private InputStreamListener isl;
	private Socket              socket;
	private Client              client;
	private boolean             running = true;

	public ProcessInput(Client client) {
		this.client = client;
		socket = client.socket;
		try
		{
			isl = new InputStreamListener(new DataInputStream(socket.getInputStream()));
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
			JSONObject input = new JSONObject(isl.run());
			if(client.isLoggedin) {
				process(input);
			}
			else {
				waitForLogin(input);
			}
		}
	}

	private void waitForLogin(JSONObject input) {
		MessageBase message = MessageBase.parse(input);
	}

	private void process(JSONObject input) {

	}
}
