package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Paytm class represents a window that displays Paytm's website for electricity bill payment.
 * It allows users to make payments using Paytm.
 */
public class Paytm extends JFrame implements ActionListener {
    String meter;
    JButton b1;

    /**
     * Constructs a Paytm object with the specified meter number.
     *
     * @param meter The meter number associated with the user.
     */
    Paytm(String meter) {
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        b1 = new JButton("Back");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500, 20, 120, 25);
        b1.addActionListener(this);
        j.add(b1);

        try {
            j.setPage("https://paytm.com/electricity-bill-payment");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        }

        JScrollPane scrollPane = new JScrollPane(j);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 800);
        setLocation(250, 120);
        setVisible(true);
    }

    /**
     * Handles the actionPerformed event triggered by the back button.
     * Closes the current window and opens the PayBill window.
     *
     * @param ae The ActionEvent object.
     */
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        new PayBill(meter).setVisible(true);
    }

    /**
     * The main method creates an instance of the Paytm class and sets it as visible.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        new Paytm("").setVisible(true);
    }
}