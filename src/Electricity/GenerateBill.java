package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * The GenerateBill class represents a window to generate electricity bills for a specific meter and month.
 */
public class GenerateBill extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextArea t1;
    JButton b1;
    Choice c2;
    JPanel p1;
    String meter;

    // Constructor for the GenerateBill class
    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500, 900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        l2 = new JLabel(meter);
        c2 = new Choice();

        // Adding months to the Choice component
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

        t1 = new JTextArea(50, 15);
        t1.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(l2);
        p1.add(c2);
        add(p1, "North");

        add(jsp, "Center");
        add(b1, "South");

        b1.addActionListener(this);

        setLocation(750, 100);
    }


    // Action performed when the "Generate Bill" button is clicked
    public void actionPerformed(ActionEvent ae){
        try{
            DBConnection c = new DBConnection();

            String month = c2.getSelectedItem();
            t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2021\n\n\n");

            // Fetching customer details
            ResultSet rs = c.s.executeQuery("select * from customer where meter="+meter);

            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            // Fetching meter information
            rs = c.s.executeQuery("select * from meter_info where meter_number = " + meter);

            if(rs.next()){
                t1.append("\n    Meter Location:"+rs.getString("meter_location"));
                t1.append("\n    Meter Type:      "+rs.getString("meter_type"));
                t1.append("\n    Phase Code:    "+rs.getString("phase_code"));
                t1.append("\n    Bill Type:         "+rs.getString("bill_type"));
                t1.append("\n    Days:               "+rs.getString("days"));
                t1.append("\n");
            }

            // Fetching tax details
            rs = c.s.executeQuery("select * from tax");

            if(rs.next()){
                t1.append("---------------------------------------------------------------");
                t1.append("\n\n");
                t1.append("\n Cost per Unit:             Rs "+rs.getString("cost_per_unit"));
                t1.append("\n Meter Rent:                Rs "+rs.getString("meter_rent"));
                t1.append("\n Service Charge:            Rs "+rs.getString("service_charge"));
                t1.append("\n Service Tax:               Rs "+rs.getString("service_tax"));
                t1.append("\n Fixed Tax:                 Rs "+rs.getString("fixed_tax"));
                t1.append("\n");

            }

            // Fetching bill details for the selected meter and month
            rs = c.s.executeQuery("select * from bill where meter="+meter+" AND month = '"+c2.getSelectedItem()+"'");

            if(rs.next()){
                t1.append("\n    Current Month :\t"+rs.getString("month"));
                t1.append("\n    Units Consumed:\t"+rs.getString("units"));
                t1.append("\n    Total Charges :\t"+rs.getString("total_bill"));
                t1.append("\n---------------------------------------------------------------");
                t1.append("\n    TOTAL PAYABLE :\t"+rs.getString("total_bill"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Main method to test the GenerateBill class
    public static void main(String[] args) {
        // Creating an instance of GenerateBill and making it visible
        new GenerateBill("").setVisible(true);
    }
}