package gui;

import model.Product;
import manager.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReviewGUI extends JFrame {
  public ReviewGUI(String username, ProductManager productManager, manager.ProfileManager profileManager) {
    setTitle("Leave a Review");
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JLabel label = new JLabel("Write your review:");
    JTextArea reviewArea = new JTextArea(10, 30);

    JLabel productLabel = new JLabel("Select product:");
    JComboBox<String> productDropdown = new JComboBox<>();
    ArrayList<Product> products = productManager.getProducts();
    for (Product product : products) {
      productDropdown.addItem(product.getName());
    }

    JButton submitButton = new JButton("Submit");
    JButton backButton = new JButton("Back");

    submitButton.addActionListener(e -> {
      String selectedProduct = (String) productDropdown.getSelectedItem();
      String review = reviewArea.getText();

      for (Product p : products) {
        if (p.getName().equals(selectedProduct)) {
          p.addReview(review);
          break;
        }
      }

      data.DataUtil.saveProducts(products);

      JOptionPane.showMessageDialog(this, "Review submitted!");
      new MarketPlaceGUI(username, profileManager, productManager);
      dispose();
    });

    backButton.addActionListener(e -> {
      new MarketPlaceGUI(username, profileManager, productManager);
      dispose();
    });

    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel productPanel = new JPanel();
    productPanel.add(productLabel);
    productPanel.add(productDropdown);
    topPanel.add(productPanel, BorderLayout.NORTH);
    topPanel.add(label, BorderLayout.SOUTH);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(submitButton);
    buttonPanel.add(backButton);

    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(new JScrollPane(reviewArea), BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);
    setVisible(true);
  }
}
