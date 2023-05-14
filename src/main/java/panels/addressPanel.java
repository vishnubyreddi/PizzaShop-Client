package panels;

import Images.customerDetails;
import delegates.delegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

    delegate delegate = new delegate();
    String userName;
    String restaurantName;

    public addressPanel(String restaurantName, int price,String userId) {
        this.userName = userId;
        this.restaurantName = restaurantName;
        HashMap<String, String> addressMap = (HashMap<String, String>) delegate.restCallToServer("/address",userName);
        customerDetails customerDetails = new customerDetails(restaurantName);
        this.price = price;
        this.userId = userId;
        setTitle(customerDetails.getCustomerName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(customerDetails.getLogo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background color to blue and red
        getContentPane().setBackground(new Color(0, 0, 255));


        JPanel panel = new JPanel(new GridLayout(6, 2));
        if(addressMap.containsKey("Name")) {
            nameLabel = new JLabel("Name:");
            nameTextField = new JTextField(addressMap.get("Name"));
            addressLabel = new JLabel("Address:");
            addressTextField = new JTextField(addressMap.get("Address"));
            cityLabel = new JLabel("City:");
            cityTextField = new JTextField(addressMap.get("City"));
            stateLabel = new JLabel("State:");
            stateTextField = new JTextField(addressMap.get("State"));
            zipLabel = new JLabel("Zip Code:");
            zipTextField = new JTextField(addressMap.get("Zip"));
        }
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
        //   delegate.restCallToServer("/test",userName);

        // Do something with the address data, such as save it to a database or use it to calculate shipping costs.

        JOptionPane.showMessageDialog(this, "Address saved.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton)){
            saveAddress();
            setVisible(false);
            PaymentOptionsPanel paymentOptionsPanel = new PaymentOptionsPanel();
            paymentOptionsPanel.PaymentOptions(restaurantName,price,userId);
        }
    }
}