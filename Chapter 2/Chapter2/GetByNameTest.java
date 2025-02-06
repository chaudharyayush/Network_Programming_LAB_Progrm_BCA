package Chapter2;

import java.net.InetAddress; //imports the InetAddress class from the java.net package, which is used to work with IP addresses and hostnames in Java
public class GetByNameTest {
	
	public static void main(String[] args){
	try{
	InetAddress address=InetAddress.getByName("www.esewa.com");
	 System.out.println(address);

	}catch(Exception e){System.out.println(e);}
	}
	}