package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

/**
 * The DepositDetails class displays details of deposits in a table.
 */
public class DepositDetails extends JFrame implements ActionListener {

    JTable t1;
    JButton b1, b2;
    JLabel l1, l2;
    Choice c1, c2;
    // Array for column headers
    String[] x = {"Meter Number", "Month", "Units", "Total Bill", "Status"};
    // 2D Array for data
    String[][] y = new String[40][8];
    int i = 0, j = 0;

    /**
     * Constructor for the DepositDetails class.
     */
    DepositDetails() {
        super("Deposit Details");
        setSize(700, 750);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Labels and choices for sorting
        l1 = new JLabel("Sort by Meter Number");
        l1.setBounds(20, 20, 150, 20);
        add(l1);

        c1 = new Choice();

        l2 = new JLabel("Sort By Month");
        l2.setBounds(400, 20, 100, 20);
        add(l2);

        c2 = new Choice();

        // Table for displaying data
        t1 = new JTable(y, x);

        try {
            DBConnection c = new DBConnection();
            String s1 = "select * from bill";
            ResultSet rs = c.s.executeQuery(s1);

            // Populate the table with data from the database
            t1.setModel(DbUtils.resultSetToTableModel(rs));

            String str2 = "select * from customer";
            rs = c.s.executeQuery(str2);
            while (rs.next()) {
                c1.add(rs.getString("meter"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        c1.setBounds(180, 20, 150, 20);
        add(c1);

        c2.setBounds(520, 20, 150, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        add(c2);

        // Buttons for searching and printing
        b1 = new JButton("Search");
        b1.setBounds(20, 70, 80, 20);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Print");
        b2.setBounds(120, 70, 80, 20);
        b2.addActionListener(this);
        add(b2);

        // Scroll pane for the table
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
    }

    /**
     * Action performed when a button is clicked.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            // Search for data based on selected options
            String str = "select * from bill where meter = '" + c1.getSelectedItem() + "' AND month = '" + c2.getSelectedItem() + "'";
            try {
                DBConnection c = new DBConnection();
                ResultSet rs = c.s.executeQuery(str);
                // Set the table model with the search results
                t1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            try {
                // Print the table
                t1.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method to test the DepositDetails class.
     */
    public static void main(String[] args) {
        new DepositDetails().setVisible(true);
    }
}