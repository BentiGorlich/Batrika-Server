package de.bentigorlich.batrikaserver.Entities.Messages;

import de.bentigorlich.batrikaserver.ConfigurationManager;
import de.bentigorlich.batrikaserver.Entities.MessageType;
import org.json.JSONObject;
import sun.plugin2.message.Message;



public class MessageBase
{

	MessageType type;

	public static MessageBase parse(JSONObject input) {
		if(input.has(ConfigurationManager.key_type)) {
			MessageType type = MessageType.get(input.getInt(ConfigurationManager.key_type));
			//TODO
		}

		return null;
	}
}
