package de.bentigorlich.batrikaserver.Entities.Messages;

import de.bentigorlich.batrikaserver.ConfigurationManager;
import de.bentigorlich.batrikaserver.Entities.MessageType;
import de.bentigorlich.batrikaserver.Entities.User;
import org.json.JSONObject;



public abstract class MessageBase {

	private MessageType type;
	private String      message;

	public MessageType getType() {
		return type;
	}

	public abstract JSONObject construct();

	public static MessageBase parse(JSONObject input, User user) {
		if(input.has(ConfigurationManager.key_type)) {
			MessageType type = MessageType.get(input.getInt(ConfigurationManager.key_type));
			if(type == MessageType.media) {
				MediaMessage erg = new MediaMessage();
				erg.setType(type);
				//TODO
				return erg;
			}
			else if(type == MessageType.message) {
				if(input.has(ConfigurationManager.key_groupname)) {
					GroupTextMessage erg = new GroupTextMessage();
					erg.setType(type);
					erg.setMessage(input.getString(ConfigurationManager.key_message));
					erg.setDestination(input.getString(ConfigurationManager.key_groupname));
					return erg;
				}
				else if(input.has(ConfigurationManager.key_roomname)) {
					RoomTextMessage erg = new RoomTextMessage();
					erg.setType(type);
					erg.setMessage(input.getString(ConfigurationManager.key_message));
					erg.setDestination(input.getString(ConfigurationManager.key_roomname));
					return erg;
				}
				else {
					if(user != null) {
						TextMessage erg = new TextMessage(user);
						erg.setType(type);
						erg.setMessage(input.getString(ConfigurationManager.key_message));
						erg.setDestination(input.getString(ConfigurationManager.key_username));
						erg.setSendersID(input.getInt(ConfigurationManager.key_sendersID));
						return erg;
					}
				}
			}
			else {
				ControlMessage erg = new ControlMessage();
				if(input.has(ConfigurationManager.key_username)) {
					erg.setUsername(input.getString(ConfigurationManager.key_username));
				}
				if(input.has(ConfigurationManager.key_password)) {
					erg.setPassword(input.getString(ConfigurationManager.key_password));
				}
				if(input.has(ConfigurationManager.key_email)) {
					erg.setEmail(input.getString(ConfigurationManager.key_email));
				}
				erg.setType(type);
				return erg;
			}
		}

		return null;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public abstract String toString();
}
