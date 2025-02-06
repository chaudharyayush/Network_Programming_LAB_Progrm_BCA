package Chapter2;

import java.io.*;
import java.net.*;

public class Inet4AddressExample {
	public static void main(String[] args) {
		try {
			//Get the Inet4Address object for a given IP address string
			//A type cast is used here to explicitly cast the returned InetAddress object to Inet4Address.
			//This cast is necessary because Inet4Address.getByName() technically returns an InetAddress, not directly an Inet4Address.
			Inet4Address ip = (Inet4Address) Inet4Address.getByName("8.8.8.8");

			System.out.println("Host Name: " + ip.getHostName());
			System.out.println("IP Address: " + ip.getHostAddress());

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
