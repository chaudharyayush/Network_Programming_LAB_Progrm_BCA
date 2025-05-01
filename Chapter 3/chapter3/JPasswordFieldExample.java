package chapter3;

import javax.swing.*; //Imports all Swing components used for GUI creation (JFrame, JLabel, JTextField, JPasswordField, JButton)
import java.awt.event.ActionEvent;//Handles button click events
import java.awt.event.ActionListener; //Allows handling actions like button clicks

public class JPasswordFieldExample {

    public static void main(String[] args) {
    	
    	//Creates a JFrame object named f
    	//"Login Example" is the title of the window
        JFrame f = new JFrame("Login Example");

        // Username Label and TextField
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField();
        usernameLabel.setBounds(20, 50, 100, 30); // x, y, width, height
        usernameField.setBounds(100, 50, 120, 30);
        
        

        // Password Label and PasswordField
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        passwordLabel.setBounds(20, 100, 100, 30); // x, y, width, height
        passwordField.setBounds(100, 100, 120, 30);

        // Creates a button labeled "Submit"
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 150, 120, 30);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            
        	//This annotation tells the compiler that actionPerformed() is overriding a method from the ActionListener interface.
        	@Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
        });

        // Adds all UI components to the JFrame
        f.add(usernameLabel);
        f.add(usernameField);
        f.add(passwordLabel);
        f.add(passwordField);
        f.add(submitButton);

        // Frame settings
        f.setLayout(null);
        //Disables the default layout manager, allowing manual positioning of components
        
        //Sets the window size to 300×300 pixels
        f.setSize(300, 300);
        
        //Closes the application when the user clicks the X button
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Displays the window
        f.setVisible(true);
    }
}