package de.bentigorlich.batrikaserver;

import java.io.IOException;
import java.net.Socket;



public class ClientTest {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 22500);
			while(true) {

			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
