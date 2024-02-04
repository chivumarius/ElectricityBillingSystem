package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    JButton b1;
    JLabel l1;
    Font f, f1, f2;
    TextArea t1;
    String s;

    // Constructor
    public About() {

        // Setting layout to null
        setLayout(null);

        // Creating and adding an "Exit" button
        JButton b1 = new JButton("Exit");
        add(b1);
        b1.setBounds(180, 430, 120, 20);
        b1.addActionListener(this);

        // Setting font for the entire frame
        Font f = new Font("RALEWAY", Font.BOLD, 18);
        setFont(f);

        // Setting up the information string
        s = """


             The Electricity Billing System is a software-based application developed in Java programming language.\s

             The project aims at serving the department of electricity by computerizing the billing system.\s

             It mainly focuses on the calculation of Units consumed during the specified time and the money to be paid to electricity offices.\s

             This computerized system will make the overall billing system easy, accessible, comfortable, and effective for consumers.

            """;

        // Creating and adding a TextArea to display information
        TextArea t1 = new TextArea(s, 10, 40, Scrollbar.VERTICAL);
        t1.setEditable(false);
        t1.setBounds(20, 100, 450, 300);
        add(t1);

        // Setting font for the TextArea
        Font f1 = new Font("RALEWAY", Font.BOLD, 16);
        t1.setFont(f1);

        // Creating and adding a label for the title
        JLabel l1 = new JLabel(" About the MainScreen");
        add(l1);
        l1.setBounds(170, 10, 180, 80);
        l1.setForeground(Color.red);

        // Setting font for the title label
        Font f2 = new Font("RALEWAY", Font.BOLD, 20);
        l1.setFont(f2);

        // Setting bounds and making the frame visible
        setBounds(700, 220, 500, 550);
        setLayout(null);
        setVisible(true);
    }

    // ActionListener implementation for the Exit button
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    // Main method to create an instance of the About class
    public static void main(String[] args) {
        new About().setVisible(true);
    }
}