package gui;

import javax.swing.*;
import manager.ProductManager;

public class UploadProductsGUI extends JFrame {
    private ProductManager productManager;
    private JTextField txtName, txtPrice, txtQuantity;

    public UploadProductsGUI(ProductManager productManager) {
        this.productManager = productManager;
        setTitle("Upload New Product");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 260);
        JPanel contentPane = new JPanel(null);
        setContentPane(contentPane);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 100, 25);
        contentPane.add(lblName);
        txtName = new JTextField();
        txtName.setBounds(120, 20, 220, 25);
        contentPane.add(txtName);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 60, 100, 25);
        contentPane.add(lblPrice);
        txtPrice = new JTextField();
        txtPrice.setBounds(120, 60, 220, 25);
        contentPane.add(txtPrice);

        JLabel lblQty = new JLabel("Quantity:");
        lblQty.setBounds(20, 100, 100, 25);
        contentPane.add(lblQty);
        txtQuantity = new JTextField();
        txtQuantity.setBounds(120, 100, 220, 25);
        contentPane.add(txtQuantity);

        JButton btnUpload = new JButton("Upload Item");
        btnUpload.setBounds(140, 160, 120, 30);
        btnUpload.addActionListener(e -> performUpload());
        contentPane.add(btnUpload);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performUpload() {
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQuantity.getText());
        productManager.addProduct(name, price, qty);
        JOptionPane.showMessageDialog(this, "Product added!");
        dispose();
    }
}
