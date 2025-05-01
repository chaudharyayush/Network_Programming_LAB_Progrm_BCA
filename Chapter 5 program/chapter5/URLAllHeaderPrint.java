//WAP to print all the headers present in a URL
package chapter5;

import java.io.*;
import java.net.*;

public class URLAllHeaderPrint {
	public static void main(String[] args) {
		String url= "https://swastikcollege.edu.np/";
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			
			//Starts an infinite loop to iterate over the headers in the HTTP response
			for (int i = 1;; i++) {
				//Retrieves the value of the header at index i
				String header = uc.getHeaderField(i);
				
				//If header is null, it means there are no more headers to read, so the loop is terminated using break
				if (header == null)
					break;
				System.out.println(uc.getHeaderFieldKey(i) + ": " + header );
			}
			//System.out.println("\n" +uc.getHeaderFieldKey(5));
			//System.out.println(uc.getHeaderField(5));
			//String data = uc. getHeaderField("date");
			//String expires = uc. getHeaderField("expires");
			//System.out.println(expires);
			//System.out.println(data);
		} catch (MalformedURLException ex) {
			System.err.println(url+": is not a URL I understand.");
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
