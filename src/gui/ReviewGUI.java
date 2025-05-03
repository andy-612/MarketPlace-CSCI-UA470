package gui;

import manager.ProfileManager;
import model.Profile;

import javax.swing.*;
import java.awt.*;

public class ReviewGUI extends JFrame {
  public ReviewGUI(String username, ProfileManager profileManager) {
    setTitle("Leave a Review");
    setSize(400, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JTextArea reviewArea = new JTextArea(5, 30);
    JButton submitButton = new JButton("Submit Review");

    submitButton.addActionListener(e -> {
      String review = reviewArea.getText();
      JOptionPane.showMessageDialog(this, "Review submitted: " + review);
      new MarketPlaceGUI(username, profileManager);
      dispose();
    });

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel("Write your review:"), BorderLayout.NORTH);
    panel.add(new JScrollPane(reviewArea), BorderLayout.CENTER);
    panel.add(submitButton, BorderLayout.SOUTH);

    add(panel);
    setVisible(true);
  }
}
