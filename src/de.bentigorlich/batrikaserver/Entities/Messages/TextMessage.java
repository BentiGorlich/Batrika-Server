package de.bentigorlich.batrikaserver.Entities.Messages;

import de.bentigorlich.batrikaserver.ConfigurationManager;
import de.bentigorlich.batrikaserver.Entities.User;
import de.bentigorlich.batrikaserver.Main;
import org.json.JSONObject;



public class TextMessage extends MessageBase
{
	private String destination; //username
	private int    sendersID;
	private User   user;

	TextMessage(User user) {
		this.user = user;
	}

	@Override
	public JSONObject construct() {
		return new JSONObject()
				.put(ConfigurationManager.key_type, this.getType().toInt())
				.put("message", this.getMessage())
				.put("sender", user.getUsername())
				;
	}

	@Override
	public String toString() {
		return "[type=" + getType() + ", destination=" + destination + ", message=" + getMessage() + "]";
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public User getDesintation() {
		return Main.getUser(destination);
	}

	public void setSendersID(int id) {
		sendersID = id;
	}

	public int getSendersID() {
		return sendersID;
	}
}
