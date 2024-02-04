package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// NewCustomer class that extends JFrame and implements ActionListener
public class NewCustomer extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l11;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JButton b1, b2;

    // Constructor for the NewCustomer class
    NewCustomer() {
        // Setting the location and size of the JFrame
        setLocation(600, 200);
        setSize(700, 500);

        // Creating a JPanel with a null layout and a background color
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        p.setBackground(new Color(173, 216, 230));

        // Adding a title label to the panel
        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);

        // Labels and text fields for customer information
        l1 = new JLabel("Customer Name");
        l1.setBounds(100, 80, 100, 20);
        t1 = new JTextField();
        t1.setBounds(240, 80, 200, 20);
        p.add(l1);
        p.add(t1);

        l2 = new JLabel("Meter No");
        l2.setBounds(100, 120, 100, 20);
        l11 = new JLabel();
        l11.setBounds(240, 120, 200, 20);
        p.add(l2);
        p.add(l11);

        l3 = new JLabel("Address");
        l3.setBounds(100, 160, 100, 20);
        t3 = new JTextField();
        t3.setBounds(240, 160, 200, 20);
        p.add(l3);
        p.add(t3);

        l5 = new JLabel("City");
        l5.setBounds(100, 200, 100, 20);
        t5 = new JTextField();
        t5.setBounds(240, 200, 200, 20);
        p.add(l5);
        p.add(t5);

        l4 = new JLabel("State");
        l4.setBounds(100, 240, 100, 20);
        t4 = new JTextField();
        t4.setBounds(240, 240, 200, 20);
        p.add(l4);
        p.add(t4);

        l6 = new JLabel("Email");
        l6.setBounds(100, 280, 100, 20);
        t6 = new JTextField();
        t6.setBounds(240, 280, 200, 20);
        p.add(l6);
        p.add(t6);

        l7 = new JLabel("Phone Number");
        l7.setBounds(100, 320, 100, 20);
        t7 = new JTextField();
        t7.setBounds(240, 320, 200, 20);
        p.add(l7);
        p.add(t7);

        // Buttons for Next and Cancel
        b1 = new JButton("Next");
        b1.setBounds(120, 390, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(250, 390, 100, 25);

        // Setting background and foreground colors for buttons
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Adding buttons to the panel
        p.add(b1);
        p.add(b2);

        // Setting layout for the JFrame
        setLayout(new BorderLayout());

        // Adding the panel to the center of the JFrame
        add(p, "Center");

        // Adding an image to the left of the JFrame
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);

        // Adding the image label to the left side of the JFrame
        add(l8, "West");

        // Setting the background color of the whole JFrame
        getContentPane().setBackground(Color.WHITE);

        // Adding ActionListener to the buttons
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Generating a random meter number and setting it to l11 label
        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        l11.setText("" + Math.abs(first));
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            // Retrieving customer information from user input
            String name = t1.getText();
            String meter = l11.getText();
            String address = t3.getText();
            String state = t4.getText();
            String city = t5.getText();
            String email = t6.getText();
            String phone = t7.getText();

            // Queries to insert customer and login information into the database
            String q1 = "insert into customer values('" + name + "','" + meter + "','" + address + "','" + city + "','"
                    + state + "','" + email + "','" + phone + "')";
            String q2 = "insert into login values('" + meter + "', '', '', '', '')";
            try {
                // Establishing a database connection and executing the queries
                DBConnection c1 = new DBConnection();
                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            // Closing the NewCustomer window
            this.setVisible(false);
        }
    }

    // Main method to create and display the NewCustomer frame
    public static void main(String[] args) {
        new NewCustomer().setVisible(true);
    }
}