package gui;

import model.Product;
import model.Profile;
import manager.ProductManager;
import manager.ProfileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MarketPlaceGUI extends JFrame {
    private JTable productTable;
    private ProductManager productManager = new ProductManager();
    private String username;
    private ProfileManager profileManager;

    public MarketPlaceGUI(String username, ProfileManager profileManager) {
        this.username = username;
        this.profileManager = profileManager;

        setTitle("Marketplace - Welcome, " + username);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Mock data
        productManager.addProduct("Laptop", 999.99, 10);
        productManager.addProduct("Phone", 499.99, 5);

        String[] columns = { "Name", "Price", "Quantity" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        updateTable(tableModel);

        JButton buyButton = new JButton("Buy");
        JButton returnButton = new JButton("Return Product");
        JButton reviewButton = new JButton("Leave Review");
        JButton modifyButton = new JButton("Modify Profile");

        buyButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                String productName = productTable.getValueAt(selectedRow, 0).toString();

                boolean success = productManager.reduceQuantity(productName);
                if (success) {
                    Product purchasedProduct = null;
                    for (Product p : productManager.getProducts()) {
                        if (p.getName().equals(productName)) {
                            purchasedProduct = new Product(p.getName(), p.getPrice(), 1);
                            break;
                        }
                    }
                    if (purchasedProduct != null) {
                        profileManager.addPurchaseToProfile(username, purchasedProduct);
                    }
                    JOptionPane.showMessageDialog(this, "Purchase successful!");
                    updateTable((DefaultTableModel) productTable.getModel());
                } else {
                    JOptionPane.showMessageDialog(this, "Out of stock!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product first.");
            }
        });

        returnButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Return functionality coming soon."));
        reviewButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Review functionality coming soon."));
        modifyButton.addActionListener(e -> {
            new MyProfileGUI(username, profileManager);
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
            model.addRow(new Object[] { p.getName(), p.getPrice(), p.getQuantity() });
        }
    }
}
