package gui;

import manager.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    public MainGUI() {
        setTitle("Marketplace - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JButton buyerButton = new JButton("Buyer");
        JButton sellerButton = new JButton("Seller");

        buyerButton.addActionListener(e -> {
            ProfileManager sharedManager = new ProfileManager();
            new BuyerLoginGUI(sharedManager);
            dispose();
        });

        sellerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Seller flow coming next!");
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(buyerButton);
        panel.add(sellerButton);

        add(panel);
        setVisible(true);
    }
}
