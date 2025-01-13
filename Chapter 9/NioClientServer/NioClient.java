package NioClientServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8888);
        
        //Opens a SocketChannel and connects it to the specified InetSocketAddress. This establishes a connection to the server at localhost on port 8888.
        SocketChannel client = SocketChannel.open(address);
        
        ByteBuffer buffer = ByteBuffer.allocate(256);
        
        String message = "Hello!";
        buffer.put(message.getBytes());
        
        //Prepares the buffer for reading by setting the limit to the current position and the position to zero. 
        //This is necessary before writing to the channel.
        buffer.flip();
        
        //Writes the contents of the buffer to the server via the SocketChannel.
        client.write(buffer);
        
       //Clears the buffer, resetting the position to zero and the limit to the buffer’s capacity. 
        //This prepares the buffer for reading data from the server.
        buffer.clear();
        
        //Reads data from the server into the buffer.
        client.read(buffer);
        buffer.flip();
        
        //Creates a byte array with the size of the remaining bytes in the buffer.
        byte[] bytes = new byte[buffer.remaining()];
        
        // Reads the remaining bytes from the buffer into the byte array
        buffer.get(bytes);
        
        System.out.println("Received from server: " + new String(bytes));
        
        client.close();
    }
}