package NioClientServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException {
        //  Opens a new Selector instance for monitoring multiple channels.
        Selector selector = Selector.open();
        
        // Opens a ServerSocketChannel, which listens for incoming connection requests.
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        
        //Binds the server socket to port 8888, making it listen for connections on this port.
        serverSocket.bind(new InetSocketAddress(8888));
        
        //Configures the server socket to be non-blocking, allowing the use of non-blocking I/O operations
        serverSocket.configureBlocking(false);
        
        // Register the server socket channel with the selector for accept events
        //This means the selector will monitor this channel for incoming connection requests.
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        
        ByteBuffer buffer = ByteBuffer.allocate(256);
        
        while (true) {
            // select() blocks until at least one channel is ready for the operation
            selector.select();
            
            // returns the keys of channels that are ready for operation
            
            //Gets an iterator over the set of SelectionKey objects representing the channels that are ready for operations.
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            //Iterates through the selected keys.
            while (keys.hasNext()) {
            	//Retrieves the next SelectionKey from the iterator
                SelectionKey key = keys.next();
                //Removes the current key from the iterator to avoid processing it again.
                keys.remove();
                
                // Checks if the key is acceptable, meaning the server socket channel is ready to accept a new connection.
                if (key.isAcceptable()) {
                	//Retrieves the ServerSocketChannel associated with the key.
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    
                    //Registers the client socket channel with the selector for read operations.
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("Accepted connection from " + client.getRemoteAddress());
                
                // Checks if the key is readable, meaning the client socket channel is ready for reading data.
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    buffer.clear();
                    int bytesRead = client.read(buffer);
                    
                    if (bytesRead == -1) {
                        client.close();
                    } else {
                        buffer.flip();
                        //Writes the contents of the buffer back to the client, echoing the received data.
                        client.write(buffer);
                    }
                }
            }
        }
    }
}
