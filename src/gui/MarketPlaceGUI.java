package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import manager.ProfileManager;
import manager.ProductManager;
import model.Product;

public class MarketPlaceGUI extends JFrame {
    private final ProfileManager profileManager;
    private final ProductManager productManager;
    private final String username;
    private JTable productTable;

    public MarketPlaceGUI(String username, ProfileManager pm, ProductManager pr) {
        super("Marketplace - Welcome, " + username);
        this.username       = username;
        this.profileManager = pm;
        this.productManager = pr;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MainGUI(profileManager, productManager);
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null);
        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        String[] columns = { "Name", "Price", "Quantity", "Review" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        updateTable(tableModel);

        JButton buyButton    = new JButton("Buy");
        JButton returnButton = new JButton("Return");
        JButton reviewButton = new JButton("Leave Review");
        JButton modifyButton = new JButton("Modify Profile");
        JButton logoutButton = new JButton("Logout to Main Menu");

        buyButton.addActionListener(e -> {
            int r = productTable.getSelectedRow();
            if (r >= 0) {
                String name = productTable.getValueAt(r, 0).toString();
                if (productManager.reduceQuantity(name)) {
                    profileManager.addPurchaseToProfile(username, new Product(name,
                        Double.parseDouble(productTable.getValueAt(r,1).toString()), 1));
                    JOptionPane.showMessageDialog(this, "Purchase successful.");
                    updateTable((DefaultTableModel)productTable.getModel());
                }
            }
        });

        returnButton.addActionListener(e -> {
            int r = productTable.getSelectedRow();
            if (r >= 0) {
                String name = productTable.getValueAt(r, 0).toString();
                if (profileManager.removePurchaseFromProfile(username, new Product(name,0,1))) {
                    productManager.increaseQuantity(name);
                    JOptionPane.showMessageDialog(this, "Return successful.");
                    updateTable((DefaultTableModel)productTable.getModel());
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

        logoutButton.addActionListener(e -> {
            new MainGUI(profileManager, productManager);
            dispose();
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 0));
        buttonPanel.add(buyButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(logoutButton);

        setLayout(new BorderLayout(10,10));
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Product p : productManager.getProducts()) {
            model.addRow(new Object[]{
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getLatestReview()
            });
        }
    }
}
