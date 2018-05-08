package de.bentigorlich.batrikaserver.Network;

import de.bentigorlich.batrikaserver.Entities.Chat;
import de.bentigorlich.batrikaserver.Entities.User;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Client extends Chat {
	private ProcessInput     process;
	public  Socket           socket;
	public  User             user;
	public  boolean          isLoggedIn = false;
	public  DataOutputStream out;

	public Client(Socket clientSocket) {
		socket = clientSocket;
		process = new ProcessInput(this);
		new Thread(process).start();
		try {
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void send(JSONObject construct) {
		try {
			String toSend = construct.toString();
			if(toSend.getBytes().length < 65000) {
				out.writeUTF(toSend);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
