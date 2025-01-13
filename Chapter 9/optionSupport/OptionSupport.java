package optionSupport;

import java.io.*;
import java.net.*;
import java.nio.channels.*;

public class OptionSupport {
	public static void main(String[] args) throws IOException {
		printOptions(SocketChannel.open());//Opens a SocketChannel (client-side channel for TCP connections).
		printOptions(ServerSocketChannel.open());//Opens a ServerSocketChannel (server-side channel for TCP connections).
		printOptions(AsynchronousSocketChannel.open());//Opens an AsynchronousSocketChannel (asynchronous client-side channel for TCP).
		printOptions(AsynchronousServerSocketChannel.open());//Opens an AsynchronousServerSocketChannel (asynchronous server-side channel for TCP).
		printOptions(DatagramChannel.open());//Opens a DatagramChannel (channel for UDP datagrams).

	}

	private static void printOptions(NetworkChannel channel) throws IOException {
		System.out.println(channel.getClass().getSimpleName() + " supports:");
		for (SocketOption<?> option : channel.supportedOptions()) {
			System.out.println(option.name() + ": " + channel.getOption(option));
		}
		System.out.println();
		channel.close();
	}
}
