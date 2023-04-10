import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class user extends JFrame implements ActionListener {

    JPanel login = new JPanel();

    public static void main(String[] args) {
        new user();
    }

    // Create UI components
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    JButton loginButton = new JButton("Login");

    JButton orderButton = new JButton("Order");
    JPanel mainPanel = new JPanel();

    JPanel paymentPanel = new JPanel();
    public user() {

        setTitle("Pizza Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image logo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\91799\\Downloads\\dominos.png");
        setIconImage(logo);

        login.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        login.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        login.add(usernameTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        login.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        login.add(passwordField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        login.add(loginButton, gbc);

        add(login);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginButton)) {
            HashMap<String, String> loginDetails = new HashMap<>();
            loginDetails.put("vishnu", "vishnu@123");
            loginDetails.put("", "");
            if (loginDetails.get(usernameTextField.getText()) != null && loginDetails.get(usernameTextField.getText()).equalsIgnoreCase(passwordField.getText())) {
                try {
                    loginSuccessful();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(e.getSource().equals(orderButton)){
            remove(mainPanel);
            setTitle("Payment");

            paymentPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);


            JLabel price= new JLabel("price");
            gbc.gridx=0;
            gbc.gridy=0;
            paymentPanel.add(price,gbc);
            add(paymentPanel);
            setVisible(true);
        }
    }

    public void loginSuccessful() throws IOException {
        remove(login);
        setTitle("Pizza Order Form");
        // Create UI components
        JLabel toppingsLabel = new JLabel("Toppings:");
        JCheckBox pepperoniCheckBox = new JCheckBox("Pepperoni");
        JCheckBox mushroomCheckBox = new JCheckBox("Mushroom");
        JCheckBox onionCheckBox = new JCheckBox("Onion");

        JLabel sizeLabel = new JLabel("Size:");
        JRadioButton smallRadioButton = new JRadioButton("Small");
        JRadioButton mediumRadioButton = new JRadioButton("Medium");
        JRadioButton largeRadioButton = new JRadioButton("Large");
        ButtonGroup sizeButtonGroup = new ButtonGroup();
        sizeButtonGroup.add(smallRadioButton);
        sizeButtonGroup.add(mediumRadioButton);
        sizeButtonGroup.add(largeRadioButton);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField("1", 5);

        orderButton.addActionListener(this);

        // Add components to the UI
        JPanel toppingsPanel = new JPanel();
        toppingsPanel.add(toppingsLabel);
        toppingsPanel.add(pepperoniCheckBox);
        toppingsPanel.add(mushroomCheckBox);
        toppingsPanel.add(onionCheckBox);

        JPanel sizePanel = new JPanel();
        sizePanel.add(sizeLabel);
        sizePanel.add(smallRadioButton);
        sizePanel.add(mediumRadioButton);
        sizePanel.add(largeRadioButton);

        JPanel quantityPanel = new JPanel();
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(orderButton);

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(toppingsPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(sizePanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(quantityPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(buttonPanel, gbc);

        JPanel profile = new JPanel();

        JLabel username = new JLabel("Welcome " + usernameTextField.getText());
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(username,gbc);
        add(mainPanel);

        setVisible(true);
    }
}