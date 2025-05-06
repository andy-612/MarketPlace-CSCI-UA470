package gui;

import manager.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class MyProfileGUI extends JFrame {
    public MyProfileGUI(String username) {
        setTitle("My Profile - " + username);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ProfileManager profileManager = new ProfileManager();

        model.Profile profile = profileManager.getProfile(username);
        JTextField nameField = new JTextField(profile.getName());
        JTextField phoneField = new JTextField(profile.getPhoneNumber());

        JButton saveButton = new JButton("Save Changes");
        JButton deleteButton = new JButton("Delete Profile");
        JButton backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            onSaveClick(profileManager, username, nameField, phoneField);
        });

        deleteButton.addActionListener(e -> {
            onDeleteClick(profileManager, username);
        });

        backButton.addActionListener(e -> {
            onBackClick(username);
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

    public void onSaveClick(ProfileManager profileManager, String username, JTextField nameField,
            JTextField phoneField) {
        profileManager.updateProfile(username, nameField.getText(), phoneField.getText());
        JOptionPane.showMessageDialog(this, "Changes saved.");
    }

    public void onDeleteClick(ProfileManager profileManager, String username) {
        profileManager.deleteProfile(username);
        new BuyerLoginGUI();
        dispose();
    }

    public void onBackClick(String username) {
        new MarketPlaceGUI(username);
        dispose();
    }

}
