package chapter6;

import java.io.BufferedReader;//Reads text from an input stream efficiently
import java.io.InputStream;//Reads raw bytes from an input stream
import java.io.InputStreamReader;//Converts byte streams to character streams
import java.net.Socket;//Allows the program to connect to a remote server over TCP

public class DayTimeClient {
    public static void main(String[] args) {
        final String hostname = "time.nist.gov";
        
        //Specifies the port number 13, which is traditionally used by the Daytime Protocol
        final int port = 13;
        
        //Declares a Socket object to establish a network connection
        Socket socket = null;

        try {
        	
        	//Creates a Socket object and connects it to the hostname (time.nist.gov) on port 13
            socket = new Socket(hostname, port);

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "ASCII"));

            StringBuilder time = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                time.append(line).append("\n");
            }
            
            //Converts the StringBuilder content into a String and prints it
            System.out.println(time.toString());

            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
