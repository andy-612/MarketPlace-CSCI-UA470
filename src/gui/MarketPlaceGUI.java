package gui;

import model.Product;
import manager.ProductManager;
import manager.ProfileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MarketPlaceGUI extends JFrame {
    private JTable productTable;
    private ProductManager productManager;
    private String username;
    private ProfileManager profileManager;

    public MarketPlaceGUI(String username, ProfileManager profileManager, ProductManager productManager) {
        this.username = username;
        this.profileManager = profileManager;
        this.productManager = productManager;

        setTitle("Marketplace - Welcome, " + username);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columns = { "Name", "Price", "Quantity", "Review" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        updateTable(tableModel);

        JButton buyButton = new JButton("Buy");
        JButton returnButton = new JButton("Return Product");
        JButton reviewButton = new JButton("Leave Review");
        JButton modifyButton = new JButton("Modify Profile");

        buyButton.addActionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row != -1) {
                String productName = productTable.getValueAt(row, 0).toString();
                double price = Double.parseDouble(productTable.getValueAt(row, 1).toString());
                int quantity = Integer.parseInt(productTable.getValueAt(row, 2).toString());

                if (quantity > 0) {
                    Product product = new Product(productName, price, quantity);
                    boolean success = productManager.reduceQuantity(productName);
                    if (success) {
                        profileManager.addPurchaseToProfile(username, product);
                        JOptionPane.showMessageDialog(this, "Purchase successful.");
                        updateTable((DefaultTableModel) productTable.getModel());
                    } else {
                        JOptionPane.showMessageDialog(this, "Purchase failed.");
                    }
                }
            }
        });

        returnButton.addActionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row != -1) {
                String productName = productTable.getValueAt(row, 0).toString();
                Product returnProduct = new Product(productName, 0.0, 1);
                boolean success = profileManager.removePurchaseFromProfile(username, returnProduct);
                if (success) {
                    productManager.increaseQuantity(productName);
                    JOptionPane.showMessageDialog(this, "Return successful.");
                    updateTable((DefaultTableModel) productTable.getModel());
                } else {
                    JOptionPane.showMessageDialog(this, "You cannot return more than you've purchased.");
                }
            }
        });

        reviewButton.addActionListener(e -> {
            new ReviewGUI(username, productManager, profileManager);
            dispose();
        });

        modifyButton.addActionListener(e -> {
            new MyProfileGUI(username, profileManager, productManager);
            dispose();
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(buyButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(modifyButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateTable(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Product> products = productManager.getProducts();
        for (Product p : products) {
            model.addRow(new Object[] { p.getName(), p.getPrice(), p.getQuantity(), p.getLatestReview() });
        }
    }
}
