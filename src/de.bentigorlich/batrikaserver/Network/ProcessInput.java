package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.Commands.CommandSendLoginFail;
import de.bentigorlich.batrikaserver.Commands.CommandSendMessageReceived;
import de.bentigorlich.batrikaserver.Commands.CommandSendSelfInfo;
import de.bentigorlich.batrikaserver.Commands.CommenadSendCreateUserFail;
import de.bentigorlich.batrikaserver.DataBase.Queries.QueryCheckUserExists;
import de.bentigorlich.batrikaserver.DataBase.Queries.QueryGetLogin;
import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Entities.Messages.MediaMessage;
import de.bentigorlich.batrikaserver.Entities.Messages.MessageBase;
import de.bentigorlich.batrikaserver.Entities.Messages.TextMessage;
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
			MessageBase message = MessageBase.parse(input, this.client.user);
			if(message != null) {
				System.out.println("parsed to: " + message.toString());
				if(client.isLoggedIn) {
					process(message);
				}
				else {
					waitForLogin(message);
				}
			}
		}
	}

	private void waitForLogin(MessageBase message) {
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
					User temp = Main.getUser(username);
					this.client.user = temp;
					this.client.isLoggedIn = true;
					ControlMessage sendSucc = new ControlMessage();
					sendSucc.setType(MessageType.login);
					client.send(sendSucc.construct());
				}
				else {
					new CommandSendLoginFail(this.client).execute();
				}
			}
			else if(message.getType() == MessageType.user_create) {
				System.out.println("creating user...");
				if(controlMessage.isValidCreatingUser()) {
					String               username   = controlMessage.getUsername();
					String               password   = controlMessage.getPassword();
					String               email      = controlMessage.getEmail();
					QueryCheckUserExists userExists = new QueryCheckUserExists(username);
					if(!userExists.exists()) {
						User temp = new User(username);
						Main.users.add(temp);
						this.client.user = temp;
						this.client.isLoggedIn = true;
						new CommandSendSelfInfo(this.client.user).execute();
					}
					else {
						new CommenadSendCreateUserFail(this.client, "This user already exists!").execute();
					}
				}
				else {
					new CommenadSendCreateUserFail(this.client, "Submitted information is not valid...").execute();
				}
			}
		}
	}

	private void process(MessageBase message) {
		if(message instanceof ControlMessage) {
			ControlMessage cm = (ControlMessage)message;
			switch(cm.getType()) {

				default:
					break;
			}
		}
		else if(message instanceof TextMessage) {
			TextMessage tm = (TextMessage)message;
			switch(tm.getType()) {
				case message:
					tm.getDesintation().send(tm.construct());
					new CommandSendMessageReceived(this.client.user, tm.getSendersID()).execute();
					break;
				default:
					break;
			}
		}
		else if(message instanceof MediaMessage) {
			MediaMessage mm = (MediaMessage)message;
			switch(mm.getType()) {

				default:
					break;
			}
		}
	}
}
