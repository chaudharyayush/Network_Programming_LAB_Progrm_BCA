package Chapter2;

import java.net.InetAddress;

public class TestRechability {
    
    public static void main(String[] args) {
        
        try {
            // Check if Google is reachable with a timeout of 5 seconds
            boolean isGoogleReachable = InetAddress.getByName("www.facebook.com").isReachable(5000);
            System.out.println("Is Google reachable? " + isGoogleReachable);
            
            // Check if host is reachable with a custom TTL and timeout
            boolean ishostReachable = InetAddress.getByName("google.com").isReachable(null, 0, 1000);//null: No custom network interface is specified (default network interface is used).
            //1: TTL (Time To Live) value, which limits how far packets can travel.
            //10: Timeout in milliseconds for the reachability check.
            System.out.println("Is host reachable? " + ishostReachable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
