package chapter3;

import java.net.*;
import java.io.*;

public class ProxySystemProperty {
    public static void main(String[] args) {
        // Set the  HTTP proxy host and port system property
        // live proxy list: https://www.proxynova.com/proxy-server-list/
        System.setProperty("http.proxyHost", "13.234.24.116");
        System.setProperty("http.proxyPort", "1080");
        //These properties are used by the URL class to route HTTP requests through the specified proxy
        try {
            // Calls the validateProxyServer method to check if the proxy server is reachable. 
        	//It passes the proxy host (13.234.24.116) and port (1080) as arguments.
            validateProxyServer("13.234.24.116", 1080);

            // Create a URL object to make an HTTP request
            URL url = new URL("http://example.com");

            // Create a Proxy object
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("13.234.24.116", 1080));

            // Open an HTTP connection using the URL object and the proxy
            //Casts the connection object to HttpURLConnection for HTTP-specific operations.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);

            // Sets the HTTP request method to GET. This indicates that the program is requesting data from the server
            conn.setRequestMethod("GET");

            // Get the input stream from the HTTP connection
            InputStream inputStream = conn.getInputStream();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response to the console
            System.out.println(response.toString());

            // Close the HTTP connection
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateProxyServer(String proxyHost, int proxyPort) throws IOException {
        try (Socket socket = new Socket()) {
            // Attempt to connect to the proxy server
            socket.connect(new InetSocketAddress(proxyHost, proxyPort), 5000); // 5 seconds timeout
            System.out.println("Proxy server is reachable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}