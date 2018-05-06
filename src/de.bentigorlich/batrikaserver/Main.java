package batrikaserver;

import batrikaserver.Network.NetworkListener;



public class Main
{
	public static void main(String[] args)
	{
		new NetworkListener(ConfigurationManager.getInstance().getPort());
	}
}
