package Electricity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * The CustomerDetails class displays details of all customers in a table.
 */
public class CustomerDetails extends JFrame implements ActionListener {

    JTable t1;
    JButton b1;
    // Array for column headers
    String[] x = {"Customer Name", "Meter Number", "Address", "City", "State", "Email", "Phone"};
    // 2D Array for data
    String[][] y = new String[40][8];
    int i = 0, j = 0;

    /**
     * Constructor for the CustomerDetails class.
     */
    CustomerDetails() {
        super("Customer Details");
        setSize(1200, 650);
        setLocation(400, 150);

        try {
            // Create a database connection
            DBConnection c1 = new DBConnection();
            String s1 = "select * from customer";
            ResultSet rs = c1.s.executeQuery(s1);

            // Populate the 2D array with customer details
            while (rs.next()) {
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("meter");
                y[i][j++] = rs.getString("address");
                y[i][j++] = rs.getString("city");
                y[i][j++] = rs.getString("state");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("phone");
                i++;
                j = 0;
            }
            // Create the table with data and column headers
            t1 = new JTable(y, x);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Button for printing the table
        b1 = new JButton("Print");
        add(b1, "South");
        // Scroll pane for the table
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        // Add action listener to the print button
        b1.addActionListener(this);
    }

    /**
     * Action performed when the "Print" button is clicked.
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            // Print the table
            t1.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to test the CustomerDetails class.
     */
    public static void main(String[] args) {
        new CustomerDetails().setVisible(true);
    }
}