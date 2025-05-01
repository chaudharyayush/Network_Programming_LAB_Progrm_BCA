package socketConstructorMethod;
//public Socket(InetAddress host, int port) throws IOException
import java.net.*;
import java.io.*;

public class Method1 {
    public static void main(String[] args) {
        String hostName = "example.com";
        int portNumber = 80; // HTTP port
        
        try {
            // Resolve the host name to get InetAddress
            InetAddress hostAddress = InetAddress.getByName(hostName);
            
            // Create a socket and connect to the server
            Socket socket = new Socket(hostAddress, portNumber);
            
            // It retrieves the output stream from the socket object to send data to the server
            OutputStream outputStream = socket.getOutputStream();
            
            //Wraps the OutputStream inside a PrintWriter for easier text-based communication
            //The second argument true enables auto-flushing
            PrintWriter writer = new PrintWriter(outputStream, true);
            
            //println() automatically handles newline characters
            writer.println("GET / HTTP/1.1");
            
			/*
			 * Sends the Host header required in HTTP 1.1 to tells the server which website
			 * we are trying to reach In HTTP 1.1, a single server/IP can host multiple
			 * websites (Virtual Hosting) so The Host header tells the server which specific website we want.
			 */
            writer.println("Host: " + hostName);
            
            //Sends a blank line to indicate the end of the HTTP request
            writer.println();
            //writer.flush();
            
            // Read response from the server
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Close the socket when done
            socket.close();
            
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

