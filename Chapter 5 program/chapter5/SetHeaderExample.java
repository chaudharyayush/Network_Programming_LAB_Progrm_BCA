package chapter5;

import java.net.URL;
import java.net.URLConnection;
import java.util.List; //Used to store multiple values for a header.
import java.util.Map; //Used to store header names and their corresponding values.

public class SetHeaderExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");

            URLConnection connection = url.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //Adds a custom header named "Custom-Header" with the value "swastik"
            connection.setRequestProperty("Custom-Header", "swastik");
            
            //Retrieves all request headers as a Map<String, List<String>>
            //Each header can have multiple values, so they are stored in a List<String>
            Map<String, List<String>> requestProperties = connection.getRequestProperties();
            
            //entrySet() returns a Set of all key-value pairs in the Map
            //Each entry is a Map.Entry<String, List<String>> → A single key-value pair
            for (Map.Entry<String, List<String>> entry : requestProperties.entrySet()) {
                String headerName = entry.getKey();
                List<String> headerValues = entry.getValue();
                
                System.out.println(headerName + ": " + String.join(", ", headerValues)); //combines multiple values into a single string, separated by commas.
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
