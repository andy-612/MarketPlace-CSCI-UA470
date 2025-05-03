package gui;

import manager.ProductManager;
import manager.ProfileManager;
import model.Profile;

import javax.swing.*;
import java.awt.*;

public class MyProfileGUI extends JFrame {
    public MyProfileGUI(String username, ProfileManager profileManager, ProductManager productManager) {
        setTitle("My Profile - " + username);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model.Profile profile = profileManager.getProfile(username);
        JTextField nameField = new JTextField(profile.getName());
        JTextField phoneField = new JTextField(profile.getPhoneNumber());

        JButton saveButton = new JButton("Save Changes");
        JButton deleteButton = new JButton("Delete Profile");
        JButton backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            profileManager.updateProfile(username, nameField.getText(), phoneField.getText());
            JOptionPane.showMessageDialog(this, "Changes saved.");
        });

        deleteButton.addActionListener(e -> {
            profileManager.deleteProfile(username);
            new BuyerLoginGUI(profileManager);
            dispose();
        });

        backButton.addActionListener(e -> {
            new MarketPlaceGUI(username, profileManager, productManager);
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(saveButton);
        panel.add(deleteButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

}
