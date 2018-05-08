package de.bentigorlich.batrikaserver.Entities.Messages;

import org.json.JSONObject;



public class TextMessage extends MessageBase
{
	private String destination; //username
	private String message;

	@Override
	public JSONObject construct() {
		return null;
	}

	@Override
	public String toString() {
		return "[type=" + getType() + ", destination=" + destination + ", message=" + message + "]";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
