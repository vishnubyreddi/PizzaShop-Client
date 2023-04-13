package panels;

import Images.customerDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class thankYou extends JFrame {

    private JPanel mainPanel;
    private JLabel thankYouLabel;

    public thankYou(String name) {
        // Set up the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add the "Thank You" message to a label
        thankYouLabel = new JLabel("Thank you "+name);
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thankYouLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the label to the main panel
        mainPanel.add(thankYouLabel, BorderLayout.CENTER);

        // Set up the JFrame
        customerDetails customerDetails = new customerDetails();
        setTitle(customerDetails.getCustomerName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(customerDetails.getLogo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new user();
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

    }
}