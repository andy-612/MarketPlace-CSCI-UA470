package gui;

import manager.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class CreateProfileGUI extends JFrame {
    public CreateProfileGUI(ProfileManager profileManager) {
        setTitle("Create Profile");
        setSize(300, 300);
        setLocationRelativeTo(null);

        JTextField usernameField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submit = new JButton("Submit");

        submit.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String password = new String(passwordField.getPassword());
            profileManager.createProfile(username, name, phone, password);
            new BuyerLoginGUI(profileManager);
            dispose();
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(submit);

        setVisible(true);
    }
}
