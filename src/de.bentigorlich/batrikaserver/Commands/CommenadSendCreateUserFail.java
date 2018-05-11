package de.bentigorlich.batrikaserver.Commands;

import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Network.Client;



public class CommenadSendCreateUserFail extends CommandBase {
	private Client client;
	private String message = "";

	public CommenadSendCreateUserFail(Client c) {
		this.client = c;
	}

	public CommenadSendCreateUserFail(Client c, String message) {
		this.client = c;
		if(message != null && !message.equals("")) {
			this.message = message;
		}
	}

	@Override
	public void execute() {
		ControlMessage cm = new ControlMessage();
		cm.setType(MessageType.user_create_fail);
		cm.setMessage(message);
		client.send(cm.construct());
	}
}
