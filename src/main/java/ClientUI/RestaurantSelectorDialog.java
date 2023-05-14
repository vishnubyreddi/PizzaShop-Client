package ClientUI;

import Images.customerDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.user;

public class RestaurantSelectorDialog extends JFrame implements ActionListener {
    private final JRadioButton dominosRadioButton;
    private final JRadioButton kfcRadioButton;
    private final JButton okButton;
    private final JLabel messageLabel;

    public RestaurantSelectorDialog() {
        setTitle("One Stop Solution for All Food Chains");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/fastfood.jpg"));
        setIconImage(logo);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Select a restaurant:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(titleLabel);

        dominosRadioButton = new JRadioButton("Domino's");
        dominosRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        kfcRadioButton = new JRadioButton("KFC");
        kfcRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dominosRadioButton);
        buttonGroup.add(kfcRadioButton);

        JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radioButtonPanel.add(dominosRadioButton);
        radioButtonPanel.add(Box.createHorizontalStrut(40));
        radioButtonPanel.add(kfcRadioButton);
        radioButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(40));
        panel.add(radioButtonPanel);

        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(messageLabel);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRestaurant = "";
                if (dominosRadioButton.isSelected()) {
                    selectedRestaurant = "Domino's";
                } else if (kfcRadioButton.isSelected()) {
                    selectedRestaurant ="KFC";
                }
                if (!selectedRestaurant.equals("")) {
                    dispose();
                    customerDetails customerDetails = new customerDetails(selectedRestaurant) ;
                    new user(selectedRestaurant);
                } else {
                    messageLabel.setText("Please select a restaurant.");
                }
            }
        });
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(40));
        panel.add(okButton);
    }

    public static void main(String[] args) {
        RestaurantSelectorDialog dialog = new RestaurantSelectorDialog();
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}