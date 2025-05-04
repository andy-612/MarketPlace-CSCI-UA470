package gui;

import javax.swing.*;

import data.DataUtil;

import java.awt.*;
import manager.ProfileManager;
import manager.ProductManager;

public class CreateProfileGUI extends JFrame {
    protected ProductManager productManager;

    public CreateProfileGUI(ProfileManager profileManager, ProductManager productManager) {
        this.productManager = productManager;

        setTitle("Create Profile");
        setSize(300, 300);
        setLocationRelativeTo(null);

        JTextField usernameField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        submit.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String password = new String(passwordField.getPassword());
            profileManager.createProfile(username, name, phone, password);
            new BuyerLoginGUI(profileManager, productManager);
            dispose();
        });

        cancel.addActionListener(e->{
            new BuyerLoginGUI(profileManager, productManager);
            dispose();
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel("Name:"));     add(nameField);
        add(new JLabel("Phone:"));    add(phoneField);
        add(new JLabel("Password:")); add(passwordField);
        add(submit);
        add(cancel);

        setVisible(true);
    }
}
