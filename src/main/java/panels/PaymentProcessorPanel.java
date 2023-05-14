package panels;

import Images.customerDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PaymentProcessorPanel extends JFrame implements ActionListener {
    private JPanel paymentPanel = new JPanel();
    private JLabel paymentLabel;
    private JButton payButton = new JButton("Pay");

    private String paymentOption;
    private int price;

    private String userName;
    public PaymentProcessorPanel(String paymentOption, int price,String userName) {
        this.paymentOption = paymentOption;
        this.price = price;
        this.userName = userName;

        customerDetails customerDetails = new customerDetails();
        setTitle(customerDetails.getCustomerName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(customerDetails.getLogo());
        paymentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        paymentLabel = new JLabel("You have selected " + paymentOption + " as your payment option. The price is " + price);

        gbc.gridx=0;
        gbc.gridy=0;
        paymentPanel.add(paymentLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        payButton.addActionListener(this);
        paymentPanel.add(payButton, gbc);

        add(paymentPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(payButton)){
            // Simulate payment process
            boolean paymentSuccessful = simulatePayment();

            if(paymentSuccessful){
                JOptionPane.showMessageDialog(this, "Payment successful. Thank you for your purchase!");
                setVisible(false);
                Random random = new Random();
                int randomNumber = random.nextInt(10000);
                JOptionPane.showMessageDialog(this, "Your token number is : "+ randomNumber);
                setVisible(false);
                new thankYou(userName);
            } else{
                JOptionPane.showMessageDialog(this, "Payment failed. Please try again later.");
            }
        }
    }

    private boolean simulatePayment(){
        // This method simulates the payment process
        // In a real application, this would be replaced with actual payment processing code
        // We will assume that the payment is successful 50% of the time
        return Math.random() < 0.5;
    }
}