package de.bentigorlich.batrikaserver.Entities;

import org.json.JSONObject;



public abstract class Chat
{
	public abstract void send(JSONObject toSend);
}
