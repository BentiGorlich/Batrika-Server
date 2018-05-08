package de.bentigorlich.batrikaserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public class ClientTest {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 22500);

			while(true) {
				System.out.println("Type what to send:");
				BufferedReader in     = new BufferedReader(new InputStreamReader(System.in));
				String         toSend = in.readLine();
				new DataOutputStream(s.getOutputStream()).writeUTF(toSend);
				System.out.println("sent: " + toSend);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
