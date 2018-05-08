package de.bentigorlich.batrikaserver;

public class ConfigurationManager
{
	//type keys:
	public static final String key_type      = "type";
	public static final String key_message   = "message";
	public static final String key_groupname = "groupname";
	public static final String key_roomname  = "roomname";
	public static final String key_username  = "username";
	public static final String key_password  = "password";
	public static final String key_email     = "email";

	private        int                  port = 22500;
	private static ConfigurationManager instance;

	private ConfigurationManager()
	{

	}

	public static ConfigurationManager getInstance()
	{
		if(instance == null)
		{
			instance = new ConfigurationManager();
		}
		return instance;
	}

	public static String getRdbmsUsername() {
		return "root";
	}

	public static String getRdbmsPassword() {
		return "";
	}

	public static String getDBName() {
		return "batrika";
	}

	public int getPort()
	{
		return port;
	}
}
