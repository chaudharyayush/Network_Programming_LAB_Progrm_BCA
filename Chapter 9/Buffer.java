
//Imports the ByteBuffer class from the java.nio package, which is used for buffer manipulation.
import java.nio.ByteBuffer; 

public class Buffer {
    public static void main(String[] args) {
        // Allocates a ByteBuffer with a capacity of 7 bytes. The buffer is initially empty.
        ByteBuffer buffer = ByteBuffer.allocate(7);
        System.out.println("Before FILLING:");
        
        //Calls the printBufferState method to print the buffer's position, limit, and capacity before any data is added.
        printBufferState(buffer);
        String str = "Hello";
        byte [] data = str.getBytes();
        buffer.put(data);
        System.out.println("after FILLING the buffer:");
        printBufferState(buffer);

        // Flip the buffer before reading
        // flip() => this will set limit to current position and position to zero
        buffer.flip();
        System.out.println("after Flipping the buffer:");
        printBufferState(buffer);


        //Stores the current limit (which is the end of the data) in a variable.
        int limit = buffer.limit();
        
        //Creates a new byte array output with the same length as the limit of the buffer.
        byte [] output = new byte[limit];
        
        
        //Reads bytes from the buffer into the output array.
        buffer.get(output);
        
        // Print the buffer data
        System.out.println(new String(output));
        System.out.println("after reading the buffer:");
        printBufferState(buffer);
    }

    private static void printBufferState(ByteBuffer buffer) {
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Capacity: " + buffer.capacity());
    }
    
}