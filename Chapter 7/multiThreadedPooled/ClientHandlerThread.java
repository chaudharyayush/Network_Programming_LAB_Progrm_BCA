package multiThreadedPooled;

import java.net.Socket;
import java.io.*;

//This class extends Thread and is responsible for handling an individual client connection
public class ClientHandlerThread extends Thread{
	//Stores the client's socket connection
	private final Socket connection;
	
	//Receives the client socket when a new instance of ClientHandlerThread is created
    public ClientHandlerThread(Socket socket) {
        this.connection = socket;//Stores the socket in connection for further processing
    }

    // overrides the run() method of Thread, making the class executable as a separate thread
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             PrintWriter writer = new PrintWriter(connection.getOutputStream(), true)) {

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received message: " + message);
                writer.println("You said: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
