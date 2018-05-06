package batrikaserver;

import sun.security.krb5.Config;



public class ConfigurationManager
{
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

	public int getPort()
	{
		return port;
	}
}
