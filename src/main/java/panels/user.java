package panels;

import Encryption.PasswordEncryption;
import Images.customerDetails;
import delegates.FeignDelegate;
import delegates.delegate;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class user extends JFrame implements ActionListener {

    @Autowired
    private FeignDelegate feignDelegate;

    JPanel login = new JPanel();


    // Create UI components
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    private JTextField usernameTextField;
    private JPasswordField passwordField = new JPasswordField(20);

    JButton loginButton = new JButton("Login");

    JButton orderButton = new JButton("Order");
    JPanel mainPanel = new JPanel();

    JPanel paymentPanel = new JPanel();

    JButton register = new JButton("new User");

    JLabel email = new JLabel("Email");
    JTextField emailTextField = new JTextField(20);
    JLabel password = new JLabel("Password");
    JPasswordField registerPasswordField = new JPasswordField(20);
    JLabel passwordConform = new JLabel("Conform Password");
    JPasswordField ConformPasswordField = new JPasswordField(20);
    JButton registerButton = new JButton("Register");

    JButton backFromRegister = new JButton("back");

    JLabel toppingsLabel = new JLabel("Toppings:");
    JCheckBox pepperoniCheckBox = new JCheckBox("Pepperoni");
    JCheckBox mushroomCheckBox = new JCheckBox("Mushroom");
    JCheckBox onionCheckBox = new JCheckBox("Onion");

    JLabel sizeLabel = new JLabel("Size:");
    JRadioButton smallRadioButton = new JRadioButton("Small");
    JRadioButton mediumRadioButton = new JRadioButton("Medium");
    JRadioButton largeRadioButton = new JRadioButton("Large");

    JPanel registerPanel = new JPanel();

    JLabel quantityLabel = new JLabel("Quantity:");
    JTextField quantityTextField = new JTextField("1", 5);

    String[] deliveryOptions = {"DINE IN", "DELIVERY"};
    JComboBox<String> typeOfDelivery = new JComboBox<>(deliveryOptions);
    String restaurentName = "";

    public user(String restaurentName) {
        this.restaurentName = restaurentName;
        login();
    }

    public void login() {
        customerDetails customerDetails = new customerDetails(restaurentName);
        setTitle(customerDetails.getCustomerName());
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(customerDetails.getLogo());

        login.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);


        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);


// Create show/hide password toggle button
        JToggleButton showPasswordButton = new JToggleButton(customerDetails.getEyeIcon());
        showPasswordButton.setPreferredSize(new Dimension(15, 15));

// Add action listener to toggle button
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the password field's echo character between '*' and null
                if (showPasswordButton.isSelected()) {
                    passwordField.setEchoChar('\u0000');
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

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
        gbc.gridx = 2;
        gbc.gridy = 1;
        login.add(showPasswordButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.decode("#4CAF50"));
        loginButton.setPreferredSize(new Dimension(120, 30));
        login.add(loginButton, gbc);

        register.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 5;
        login.add(register, gbc);
        add(login);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginButton)) {
            delegate delegate = new delegate();
            PasswordEncryption passwordEncryption = new PasswordEncryption();
            HashMap<String,String> userAuth = (HashMap<String, String>) delegate.restCallToServer("/login",usernameTextField.getText());
            String clientEncryptedPassword = passwordEncryption.encrypt(passwordField.getText(),userAuth.get("salt"));
            if (userAuth.get("encryptedPassword").equalsIgnoreCase(clientEncryptedPassword)) {

                try {
                    loginSuccessful();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Inavlid user name or password.");
            }
        } else if (e.getSource().equals(orderButton)) {
            remove(mainPanel);
            setTitle("Payment");

            paymentPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            int price = 100;
            if (mushroomCheckBox.isSelected()) {
                price = price + 50;
            }
            if (onionCheckBox.isSelected()) {
                price = price + 30;
            }
            if (pepperoniCheckBox.isSelected()) {
                price = price + 60;
            }
            if (mediumRadioButton.isSelected()) {
                price = price + 50;
            } else if (largeRadioButton.isSelected()) {
                price = price + 70;
            }
            int quant = Integer.parseInt(quantityTextField.getText());
            price = price * quant;
            setVisible(false);
            new addressPanel(restaurentName,price, usernameTextField.getText());

        } else if (e.getSource().equals(register)) {
            newUser();
        } else if (e.getSource().equals(registerButton)) {
            if (registerPasswordField.getText().equalsIgnoreCase(ConformPasswordField.getText())) {

            } else {

            }
        } else if (e.getSource().equals(backFromRegister)) {
            remove(registerPanel);
            login();
        }
    }


    public void loginSuccessful() throws IOException {
        remove(login);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        delegate delegate = new delegate();
        HashMap<String, ArrayList<String>> foodItems = delegate.getFoodItems(restaurentName);
        int panelsIterator = 1;
        setTitle(restaurentName);
        for (String panel :
                foodItems.keySet()) {
            JPanel customPanel = new JPanel();
            JLabel customLabel = new JLabel();
            customLabel.setText(panel + " : ");
            customPanel.add(customLabel);
            for (int i = 0; i < foodItems.get(panel).size(); i++) {
                JCheckBox itemCheckBox = new JCheckBox(foodItems.get(panel).get(i));
                customPanel.add(itemCheckBox);
            }
            gbc.gridx = 0;
            gbc.gridy = panelsIterator;
            mainPanel.add(customPanel, gbc);
            panelsIterator++;
        }

        orderButton.addActionListener(this);


        JPanel quantityPanel = new JPanel();
        quantityPanel.setBorder(BorderFactory.createTitledBorder("Enter quantity"));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(orderButton);


        gbc.gridx = 0;
        gbc.gridy = panelsIterator;
        mainPanel.add(quantityPanel, gbc);
        panelsIterator++;
        gbc.gridx = 0;
        gbc.gridy = panelsIterator;
        mainPanel.add(buttonPanel, gbc);

        JPanel profile = new JPanel();

        JLabel username = new JLabel("Welcome " + usernameTextField.getText());
        username.setFont(new Font("Arial", Font.BOLD, 18));
        username.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(username, gbc);
        ImageIcon profilePic =  new ImageIcon(getClass().getResource("/images/vishnu.jpg"));
        JLabel profileLabel = new JLabel(profilePic);
        gbc.gridx = 1;
        gbc.gridy = 0;
        profileLabel.setPreferredSize(new Dimension(20,20));
        mainPanel.add(profileLabel,gbc);
        add(mainPanel);

        setVisible(true);
    }

    public void newUser() {
        remove(login);
        registerButton.addActionListener(this);
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        registerPanel.add(emailTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        registerPanel.add(registerPasswordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(passwordConform, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        registerPanel.add(ConformPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        registerPanel.add(registerButton, gbc);

        backFromRegister.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 6;
        registerPanel.add(backFromRegister, gbc);

        add(registerPanel);
        setVisible(true);
    }
}