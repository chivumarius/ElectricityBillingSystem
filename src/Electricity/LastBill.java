package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * The LastBill class represents a window to display the last electricity bills for a specific meter.
 */
public class LastBill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1, t2;
    JButton b1;
    JPanel p1;

    // Constructor for the LastBill class
    LastBill() {
        setSize(500, 900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        // Text area for user input
        t2 = new JTextArea();

        // Text area for displaying the bill details
        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(t2);
        add(p1, "North");

        add(jsp, "Center");
        add(b1, "South");

        b1.addActionListener(this);

        // Setting the initial location of the window
        setLocation(350, 40);
    }

    // Action performed when the "Generate Bill" button is clicked
    public void actionPerformed(ActionEvent ae) {
        try {
            DBConnection c = new DBConnection();
            String meterNumber = t2.getSelectedText();

            // Fetching customer details based on the meter number
            ResultSet customerResultSet = c.s.executeQuery("select * from customer where meter=" + meterNumber);

            if (customerResultSet.next()) {
                displayCustomerDetails(customerResultSet);
            }

            t1.append("Details of the Last Bills\n\n\n");

            // Fetching bill details based on the meter number
            ResultSet billResultSet = c.s.executeQuery("select * from bill where meter=" + meterNumber);

            while (billResultSet.next()) {
                displayBillDetails(billResultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays customer details in the text area.
     * @param rs ResultSet containing customer details
     * @throws SQLException If a database access error occurs
     */
    private void displayCustomerDetails(ResultSet rs) throws SQLException {
        t1.append("\n    Customer Name:" + rs.getString("name"));
        t1.append("\n    Meter Number:  " + rs.getString("meter"));
        t1.append("\n    Address:            " + rs.getString("address"));
        t1.append("\n    State:                 " + rs.getString("state"));
        t1.append("\n    City:                   " + rs.getString("city"));
        t1.append("\n    Email:                " + rs.getString("email"));
        t1.append("\n    Phone Number  " + rs.getString("phone"));
        t1.append("\n-------------------------------------------------------------");
        t1.append("\n");
    }

    /**
     * Displays bill details in the text area.
     * @param rs ResultSet containing bill details
     * @throws SQLException If a database access error occurs
     */
    private void displayBillDetails(ResultSet rs) throws SQLException {
        t1.append("       " + rs.getString("month") + "           " + rs.getString("amount") + "\n");
    }

    // Main method to test the LastBill class
    public static void main(String[] args) {
        // Creating an instance of LastBill and making it visible
        new LastBill().setVisible(true);
    }
}