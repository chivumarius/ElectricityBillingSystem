package Electricity;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 * The UpdateInformation class allows updating customer information.
 */
public class UpdateInformation extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5;
    JLabel l11, l12;
    JButton b1, b2;
    String meter;

    /**
     * Constructor to initialize the UpdateInformation window.
     *
     * @param meter Meter number for the customer
     */
    UpdateInformation(String meter) {
        this.meter = meter;

        setBounds(500, 220, 1050, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Update Customer Information");
        title.setBounds(110, 0, 400, 30);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);

        // Labels for various customer details
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 70, 100, 20);
        add(l1);

        l11 = new JLabel();
        l11.setBounds(230, 70, 200, 20);
        add(l11);

        JLabel l2 = new JLabel("Meter Number");
        l2.setBounds(30, 110, 100, 20);
        add(l2);

        l12 = new JLabel();
        l12.setBounds(230, 110, 200, 20);
        add(l12);

        // ... (Other components initialization)

        // Text fields for updating customer information
        t1 = new JTextField();
        t1.setBounds(230, 150, 200, 20);
        add(t1);

        t2 = new JTextField();
        t2.setBounds(230, 190, 200, 20);
        add(t2);

        t3 = new JTextField();
        t3.setBounds(230, 230, 200, 20);
        add(t3);

        t4 = new JTextField();
        t4.setBounds(230, 270, 200, 20);
        add(t4);

        t5 = new JTextField();
        t5.setBounds(230, 310, 200, 20);
        add(t5);

        // Buttons for update and go back
        b1 = new JButton("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(70, 360, 100, 25);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(230, 360, 100, 25);
        b2.addActionListener(this);
        add(b2);

        try {
            // Fetching customer details from the database based on the meter number
            DBConnection c = new DBConnection();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '" + meter + "'");
            while (rs.next()) {
                l11.setText(rs.getString(1));
                l12.setText(rs.getString(2));
                t1.setText(rs.getString(3));
                t2.setText(rs.getString(4));
                t3.setText(rs.getString(5));
                t4.setText(rs.getString(6));
                t5.setText(rs.getString(7));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Image for aesthetic purposes
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8 = new JLabel(i3);
        l8.setBounds(550, 50, 400, 300);
        add(l8);
    }

    /**
     * Action event handler for button clicks.
     *
     * @param ae ActionEvent object
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            // Updating customer information in the database
            String s3 = t1.getText();
            String s4 = t2.getText();
            String s5 = t3.getText();
            String s6 = t4.getText();
            String s7 = t5.getText();

            try {
                DBConnection c = new DBConnection();
                c.s.executeUpdate("update customer set address = '" + s3 + "', city = '" + s4 + "', state = '" + s5 + "', email = '" + s6 + "', phone = '" + s7 + "' where meter = '" + meter + "'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (ae.getSource() == b2) {
            // Closing the current window and going back
            this.setVisible(false);
        }
    }

    /**
     * Main method to launch the UpdateInformation window.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new UpdateInformation("").setVisible(true);
    }
}