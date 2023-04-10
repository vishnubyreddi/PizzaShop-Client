package panels;

import Images.customerDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentOptionsPanel extends JFrame implements ActionListener {
    private JPanel paymentPanel = new JPanel();
    private JRadioButton creditCardRadioButton;
    private JRadioButton paypalRadioButton;
    private JRadioButton bitcoinRadioButton;
    private JButton ok = new JButton("ok");

    public void PaymentOptions(int price){
        customerDetails customerDetails = new customerDetails();
        setTitle(customerDetails.getCustomerName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(customerDetails.getLogo());
        paymentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel priceTag = new JLabel("Price : "+price);
        creditCardRadioButton = new JRadioButton("Credit Card");
        paypalRadioButton = new JRadioButton("PayPal");
        bitcoinRadioButton = new JRadioButton("Bitcoin");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(creditCardRadioButton);
        buttonGroup.add(paypalRadioButton);
        buttonGroup.add(bitcoinRadioButton);

        gbc.gridx=1;
        gbc.gridy=0;
        paymentPanel.add(priceTag);
        gbc.gridx=0;
        gbc.gridy=1;
        paymentPanel.add(creditCardRadioButton,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        paymentPanel.add(paypalRadioButton,gbc);
        gbc.gridx=2;
        gbc.gridy=1;
        paymentPanel.add(bitcoinRadioButton,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        paymentPanel.add(ok,gbc);

        add(paymentPanel);
        setVisible(true);
    }

    public String getSelectedOption() {
        if (creditCardRadioButton.isSelected()) {
            return "Credit Card";
        } else if (paypalRadioButton.isSelected()) {
            return "PayPal";
        } else if (bitcoinRadioButton.isSelected()) {
            return "Bitcoin";
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}