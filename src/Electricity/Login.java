package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;
    Choice c1;

    // Constructor
    Login() {
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Labels for username and password
        l1 = new JLabel("Username");
        l1.setBounds(300, 20, 100, 20);
        add(l1);
        l2 = new JLabel("Password");
        l2.setBounds(300, 60, 100, 20);
        add(l2);

        // Fields for user and password input
        tf1 = new JTextField(15);
        tf1.setBounds(400, 20, 150, 20);
        add(tf1);
        pf2 = new JPasswordField(15);
        pf2.setBounds(400, 60, 150, 20);
        add(pf2);

        // Label for selecting user type
        l4 = new JLabel("Logging in as");
        l4.setBounds(300, 100, 100, 20);
        add(l4);

        // Dropdown menu for selecting user type
        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(400, 100, 150, 20);
        add(c1);

        // Button for login
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        b1.setBounds(330, 160, 100, 20);
        add(b1);

        // Button for canceling login
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel", new ImageIcon(i2));
        b2.setBounds(450, 160, 100, 20);
        add(b2);

        // Button for signup
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i4 = ic4.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b3 = new JButton("Signup", new ImageIcon(i4));
        b3.setBounds(380, 200, 130, 20);
        add(b3);

        // Add action listeners for buttons
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Background image
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l3 = new JLabel(icc3);
        l3.setBounds(0, 0, 250, 250);
        add(l3);

        setLayout(new BorderLayout());

        setSize(640, 300);
        setLocation(600, 300);
        setVisible(true);
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                // Database connection
                DBConnection c = new DBConnection();
                String a = tf1.getText();
                char[] passwordChars = pf2.getPassword();
                String b = new String(passwordChars);
                String user = c1.getSelectedItem();
                String q = "select * from login where username = '" + a + "' and password = '" + b + "' and user = '"
                        + user + "'";
                ResultSet rs = c.s.executeQuery(q);
                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    // Open main window with user info
                    new MainScreen(meter, user).setVisible(true);
                    this.setVisible(false);
                } else {
                    // Error message for invalid login
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    tf1.setText("");
                    pf2.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error: " + e);
            }
        } else if (ae.getSource() == b2) {
            // Close login window
            this.setVisible(false);
        } else if (ae.getSource() == b3) {
            // Open signup window
            this.setVisible(false);
            new Signup().setVisible(true);
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}