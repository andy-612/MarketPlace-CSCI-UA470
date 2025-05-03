package gui;

import manager.ProductManager;
import manager.ProfileManager;
import model.Profile;

import javax.swing.*;
import java.awt.*;

public class BuyerLoginGUI extends JFrame {
    private ProfileManager profileManager;
    private ProductManager productManager;

    public BuyerLoginGUI(ProfileManager profileManager) {
        this.profileManager = profileManager;
        this.productManager = new ProductManager();

        setTitle("Buyer Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton createButton = new JButton("Create Profile");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (profileManager.profileExists(username)) {
                Profile profile = profileManager.getProfile(username);
                if (profile.getPassword().equals(password)) {
                    new MarketPlaceGUI(username, profileManager, productManager);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Profile not found.");
            }
        });

        createButton.addActionListener(e -> {
            new CreateProfileGUI(profileManager);
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createButton);

        add(panel);
        setVisible(true);
    }
}
