package de.bentigorlich.batrikaserver.Commands;

import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.Messages.ControlMessage;
import de.bentigorlich.batrikaserver.Entities.User;



public class CommandSendLoginFail extends CommandBase {
	private User user;

	public CommandSendLoginFail(User user) {
		this.user = user;
	}

	@Override
	public void execute() {
		ControlMessage cm = new ControlMessage();
		cm.setType(MessageType.login_fail);
		user.send(cm.construct());
	}
}
