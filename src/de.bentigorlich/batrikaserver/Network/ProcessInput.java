package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.DataBase.Queries.QueryCreateUser;
import de.bentigorlich.batrikaserver.DataBase.Queries.QueryGetLogin;
import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Entities.Messages.MessageBase;
import de.bentigorlich.batrikaserver.Entities.User;
import de.bentigorlich.batrikaserver.Main;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
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
			InputStream     is  = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			isl = new InputStreamListener(dis);
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
			JSONObject input;
			try {
				input = new JSONObject(isl.run());
			}
			catch(IOException e) {
				stop();
				return;
			}
			if(client.isLoggedIn) {
				process(input);
			}
			else {
				waitForLogin(input);
			}
		}
	}

	private void waitForLogin(JSONObject input) {
		MessageBase message = MessageBase.parse(input);
		System.out.println("parsed to: " + message.toString());
		if(message != null) {
			if(message instanceof ControlMessage) {
				ControlMessage controlMessage = (ControlMessage)message;
				if(message.getType() == MessageType.login) {
					System.out.println("logging in...");
					String        username = controlMessage.getUsername();
					String        password = controlMessage.getPassword();
					QueryGetLogin qgl      = new QueryGetLogin(username, password);
					qgl.execute();
					if(qgl.isLoginCorrect()) {
						System.out.println("Logged in " + username);
						User temp = null;
						for(User user : Main.loggedInUsers) {
							if(user.getUsername().equals(username)) {
								temp = user;
								break;
							}
						}
						if(temp == null) {
							temp = new User(username);
							Main.loggedInUsers.add(temp);
							this.client.user = temp;
							this.client.isLoggedIn = true;
							ControlMessage sendSucc = new ControlMessage();
							sendSucc.setType(MessageType.login);
							client.send(sendSucc.construct());
						}
					}
					else {

					}
				}
				else if(message.getType() == MessageType.user_create) {
					System.out.println("creating user...");
					String username = controlMessage.getUsername();
					String password = controlMessage.getPassword();
					String email    = controlMessage.getEmail();
					new QueryCreateUser(username, password, email).execute();

					User temp = new User(username);
					Main.loggedInUsers.add(temp);
					this.client.user = temp;
					this.client.isLoggedIn = true;
				}
			}
		}
	}

	private void process(JSONObject input) {

	}
}
