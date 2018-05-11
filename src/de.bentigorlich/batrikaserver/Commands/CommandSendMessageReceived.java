package de.bentigorlich.batrikaserver.Commands;

import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Entities.User;



public class CommandSendMessageReceived extends CommandBase {
	private User user;
	private int  sendersID;

	public CommandSendMessageReceived(User user, int sendersID) {
		this.user = user;
		this.sendersID = sendersID;
	}

	@Override
	public void execute() {
		ControlMessage cm = new ControlMessage();
		cm.setType(MessageType.message_received);
		cm.setSendersID(sendersID);
	}
}
