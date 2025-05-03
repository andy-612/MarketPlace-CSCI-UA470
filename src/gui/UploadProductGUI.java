package gui;

import manager.ProductManager;

import javax.swing.*;
import java.awt.*;

public class UploadProductGUI extends JFrame {
    public UploadProductGUI() {
        setTitle("Upload New Product");
        setSize(300, 250);
        setLocationRelativeTo(null);

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JButton uploadBtn = new JButton("Upload");

        ProductManager pm = new ProductManager();
        uploadBtn.addActionListener(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int qty = Integer.parseInt(quantityField.getText());
            pm.addProduct(name, price, qty);
            JOptionPane.showMessageDialog(this, "Product uploaded.");
        });

        setLayout(new GridLayout(4, 2));
        add(new JLabel("Product Name:")); add(nameField);
        add(new JLabel("Price:")); add(priceField);
        add(new JLabel("Quantity:")); add(quantityField);
        add(uploadBtn);

        setVisible(true);
    }
}
