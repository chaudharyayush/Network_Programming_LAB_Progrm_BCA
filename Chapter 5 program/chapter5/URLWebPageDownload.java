//Download a web page with a URLConnection
package chapter5;


import java.io.*;
import java.net.*;

public class URLWebPageDownload {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://example.com");
        URLConnection connection = url.openConnection();
        InputStream raw = connection.getInputStream();
        InputStream buffer = new BufferedInputStream(raw);
        Reader reader = new InputStreamReader(buffer);
        int c;
        //The read() method reads a single character from the stream and returns its Unicode value as an int (range: 0 to 65535)
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
        reader.close();
    }
}
