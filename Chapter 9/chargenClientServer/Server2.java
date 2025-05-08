package chargenClientServer;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Server2 {

    public static void main(String[] args) {
    	
//    	Create a byte array rotation of size 190 (95 × 2). This is used to store printable ASCII characters twice in a row to allow easy shifting.
        byte[] rotation = new byte[95 * 2];
        
//      This loop fills the rotation array with printable ASCII characters (' ' to '~', i.e., ASCII 32 to 126).
//      It duplicates the characters to make shifting output easier during writing.
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }
        
//      Declares serverChannel (for accepting connections) and selector (for managing multiple channels). 
        ServerSocketChannel serverChannel;
        Selector selector;
        try {
        	
//        	Opens a ServerSocketChannel.
//        	Gets the associated ServerSocket.
//        	Binds the server to port 8888.
            
        	serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(8888);
            ss.bind(address);

//          Makes the server non-blocking, so it doesn’t wait for operations to finish.
            serverChannel.configureBlocking(false);
            
//          Opens a Selector (a multiplexer for channels).
//          Registers the server to the selector to listen for incoming connections.
            
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        while (true) {
            try {
//            	blocks the current thread until at least one registered channel is ready for an operation (e.g., accept, read, write) that it's interested in.
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            
//          selector.selectedKeys() returns a set of SelectionKey objects, each representing a channel ready for an I/O operation (like accept or write). Using .iterator() lets us loop through these ready keys.
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
           
//          This loop continues as long as there are more ready keys to process.
            while (iterator.hasNext()) {
            	
//          Get the next SelectionKey from the set. This key tells:
//			Which channel is ready (using key.channel()).
//			What it's ready for (like key.isAcceptable(), key.isReadable(), etc.).
            	SelectionKey key = iterator.next();
                
//          Removes the current key from the selected set which keeps the selector's set clean and ready for the next select() call.
            	iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        
//                    Registers the client to the selector, interested in writing data to the client.
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
                        
//                    Creates a buffer with 72 printable characters followed by \r\n.
                        ByteBuffer buffer = ByteBuffer.allocate(74);
                        buffer.put(rotation, 0, 72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');
                        
//                    Flips the buffer to prepare for writing.
                        buffer.flip();
                        
//                    Attaches the buffer to the key, so it can be reused later.
                        key2.attach(buffer);
                   
//                 If the channel is ready for writing, get the client and its attached buffer, and write data to it.                     
                    } else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        client.write(buffer);
                        if (!buffer.hasRemaining()) {
//                        	If all data has been written, rewind the buffer.
                        	buffer.rewind();
                            
//             Get the first character to decide where to start the next line (to rotate).
                        	int first = buffer.get();
                            buffer.rewind();
                            
//             Refill the buffer with the next 72 characters, and flip it again for writing.
                            int position = first - ' ' + 1;
                            buffer.put(rotation, position, 72);
                            buffer.put((byte) '\r');
                            buffer.put((byte) '\n');
                            buffer.flip();
                        }
//                        Re-register the client for another write.
                        client.register(selector, SelectionKey.OP_WRITE, buffer);
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }
}
