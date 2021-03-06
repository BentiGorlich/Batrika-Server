package de.bentigorlich.batrikaserver.Commands;

import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Network.Client;



public class CommandSendLoginFail extends CommandBase {
	private Client client;
	private String message = "";

	public CommandSendLoginFail(Client c) {
		this.client = c;
	}

	public CommandSendLoginFail(Client c, String message) {
		if(message != null && !message.equals("")) {
			this.message = message;
		}
		this.client = c;
	}

	@Override
	public void execute() {
		ControlMessage cm = new ControlMessage();
		cm.setType(MessageType.login_fail);
		cm.setMessage(message);
		client.send(cm.construct());
	}
}
