package Electricity;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

/**
 * The BillDetails class displays details of the bill for a specific meter number.
 */
public class BillDetails extends JFrame {

    JTable t1;
    String[] x = {"Meter Number", "Month", "Units", "Total Bill", "Status"};
    String[][] y = new String[40][5];

    // Constructor for the BillDetails class
    BillDetails(String meter) {
        super("Bill Details");
        setSize(700, 650);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        t1 = new JTable(y, x);

        try {
            // Create a database connection
            DBConnection c = new DBConnection();
            String s1 = "select * from bill where meter = '" + meter + "'";

            // Execute SQL query to get bill details for the specified meter
            ResultSet rs = c.s.executeQuery(s1);

            // Set the result set to the table
            t1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a scroll pane and add the table to it
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 0, 700, 650);
        add(sp);
    }

    // Main method to test the BillDetails class
    public static void main(String[] args) {
        new BillDetails("").setVisible(true);
    }
}