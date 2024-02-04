package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * The CalculateBill class calculates the electricity bill for a customer.
 */
public class CalculateBill extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;
    JTextField t1;
    Choice c1, c2;
    JButton b1, b2;
    JPanel p;

    /**
     * Constructor for the CalculateBill class.
     */
    CalculateBill() {

        p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));

        // Labels for various fields
        l1 = new JLabel("Calculate Electricity Bill");
        l1.setBounds(30, 10, 400, 30);

        l2 = new JLabel("Meter No");
        l2.setBounds(60, 70, 100, 30);

        JLabel l6 = new JLabel("Name");
        l6.setBounds(60, 120, 100, 30);

        JLabel l7 = new JLabel("Address");
        l7.setBounds(60, 170, 100, 30);

        l3 = new JLabel("Units Consumed");
        l3.setBounds(60, 220, 100, 30);

        l5 = new JLabel("Month");
        l5.setBounds(60, 270, 100, 30);

        // Choice for selecting meter number
        c1 = new Choice();
        c1.setBounds(200, 70, 180, 20);
        try {
            DBConnection c = new DBConnection();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("meter"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Labels for displaying customer name and address
        JLabel l11 = new JLabel();
        l11.setBounds(200, 120, 180, 20);
        p.add(l11);

        JLabel l12 = new JLabel();
        l12.setBounds(200, 170, 180, 20);
        p.add(l12);

        // Fetching customer details based on meter number
        try {
            DBConnection c = new DBConnection();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '" + c1.getSelectedItem() + "'");
            while (rs.next()) {
                l11.setText(rs.getString("name"));
                l12.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Updating customer details when meter number is changed
        c1.addItemListener(ae -> {
            try {
                DBConnection c = new DBConnection();
                ResultSet rs = c.s.executeQuery("select * from customer where meter = '" + c1.getSelectedItem() + "'");
                while (rs.next()) {
                    l11.setText(rs.getString("name"));
                    l12.setText(rs.getString("address"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Text field for entering units consumed
        t1 = new JTextField();
        t1.setBounds(200, 220, 180, 20);

        // Choice for selecting the month
        c2 = new Choice();
        c2.setBounds(200, 270, 180, 20);
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

        // Buttons for submitting and canceling
        b1 = new JButton("Submit");
        b1.setBounds(100, 350, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(230, 350, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Image for displaying
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l4 = new JLabel(i3);

        // Setting font and alignment for the main label
        l1.setFont(new Font("Senserif", Font.PLAIN, 26));
        l1.setHorizontalAlignment(JLabel.CENTER);

        // Adding components to the panel
        p.add(l1);
        p.add(l2);
        p.add(l6);
        p.add(l7);
        p.add(c1);
        p.add(l5);
        p.add(c2);
        p.add(l3);
        p.add(t1);
        p.add(b1);
        p.add(b2);

        // Setting layout for the main frame
        setLayout(new BorderLayout(30, 30));

        // Adding panel and image to the frame
        add(p, "Center");
        add(l4, "West");

        // Adding action listeners to buttons
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Setting background color and size of the frame
        getContentPane().setBackground(Color.WHITE);
        setSize(750, 500);
        setLocation(550, 220);
    }

    /**
     * Action performed when a button is clicked.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String meter_no = c1.getSelectedItem();
            String units = t1.getText();
            String month = c2.getSelectedItem();

            // Check if units field is not empty
            if (units.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter units consumed.");
                return;
            }

            int units_consumed = Integer.parseInt(units);

            int total_bill = 0;
            try {
                DBConnection c = new DBConnection();
                ResultSet rs = c.s.executeQuery("select * from tax");
                while (rs.next()) {
                    // Calculating total bill based on tax rates
                    total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    total_bill += Integer.parseInt(rs.getString("meter_rent"));
                    total_bill += Integer.parseInt(rs.getString("service_charge"));
                    total_bill += Integer.parseInt(rs.getString("service_tax"));
                    total_bill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Inserting bill details into the database
            String q = "insert into bill values('" + meter_no + "','" + month + "','" + units + "','" + total_bill + "', 'Not Paid')";

            try {
                DBConnection c1 = new DBConnection();
                c1.s.executeUpdate(q);
                JOptionPane.showMessageDialog(this, "Customer Bill Updated Successfully");
                this.setVisible(false);
            } catch (Exception aee) {
                aee.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating bill. Please try again.");
            }

        } else if (ae.getSource() == b2) {
            // Close the window when Cancel button is clicked
            this.setVisible(false);
        }
    }


    /**
     * Main method to launch the CalculateBill window.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CalculateBill().setVisible(true);
        });
    }
}
