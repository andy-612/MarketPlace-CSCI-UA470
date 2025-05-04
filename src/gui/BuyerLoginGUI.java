package gui;

import javax.swing.*;
import java.awt.*;
import model.Profile;
import manager.ProfileManager;
import manager.ProductManager;

public class BuyerLoginGUI extends JFrame {
    protected ProfileManager profileManager;
    protected ProductManager productManager;

    public BuyerLoginGUI(ProfileManager profileManager, ProductManager productManager) {
        this.profileManager = profileManager;
        this.productManager = productManager;

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
        JButton backButton   = new JButton("Back to Main Menu");
        loginButton.addActionListener(e -> {
            btnLogin_click(usernameField, passwordField);
        });

        createButton.addActionListener(e -> {
            btnCreate_click();
        });
        backButton.addActionListener(e -> {
            btnBack_click();
        });
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createButton);
        panel.add(backButton);
        add(panel);
        setVisible(true);
    }

    public void btnLogin_click(JTextField usernameField, JPasswordField passwordField){
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
    }

    public void btnCreate_click(){
        new CreateProfileGUI(profileManager, productManager);
        dispose();
    }

    public void btnBack_click(){
        new MainGUI(profileManager, productManager);
        dispose();
    }


}