package multiThreadedPooled;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private static final int PORT = 7000;
    
    //Creates a thread pool with 50 threads.
    static ExecutorService pool = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");

            while (true) {
            	
            	//Returns a Socket object representing the client's connection
                Socket socket = serverSocket.accept();
                
                //Displays the client's IP address and port when a new client connects
                System.out.println("Client connected: " + socket);

                //Creates a new ClientHandlerThread object to handle client communication
                ClientHandlerThread clientHandler = new ClientHandlerThread(socket);
                
                //Submits the task to the thread pool, so a free thread handles the client
                pool.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
