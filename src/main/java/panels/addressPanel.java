package panels;

import Images.customerDetails;
import delegates.delegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addressPanel extends JFrame implements ActionListener {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel stateLabel;
    private JTextField stateTextField;
    private JLabel zipLabel;
    private JTextField zipTextField;

    private JButton saveButton;

    private int price;

    private String userId;
    public addressPanel(int price,String userId) {
        customerDetails customerDetails = new customerDetails();
        this.price = price;
        this.userId = userId;
        setTitle(customerDetails.getCustomerName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(customerDetails.getLogo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField(20);
        cityLabel = new JLabel("City:");
        cityTextField = new JTextField(20);
        stateLabel = new JLabel("State:");
        stateTextField = new JTextField(20);
        zipLabel = new JLabel("Zip Code:");
        zipTextField = new JTextField(20);

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(cityLabel);
        panel.add(cityTextField);
        panel.add(stateLabel);
        panel.add(stateTextField);
        panel.add(zipLabel);
        panel.add(zipTextField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        panel.add(new JLabel());
        panel.add(saveButton);

        add(panel);
        setVisible(true);
    }

    private void saveAddress() {
        delegates.delegate delegate = new delegate();
        delegate.restCallToServer("/test");

        // Do something with the address data, such as save it to a database or use it to calculate shipping costs.

        JOptionPane.showMessageDialog(this, "Address saved.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton)){
            saveAddress();
            setVisible(false);
            PaymentOptionsPanel paymentOptionsPanel = new PaymentOptionsPanel();
            paymentOptionsPanel.PaymentOptions(price,userId);
        }
    }
}