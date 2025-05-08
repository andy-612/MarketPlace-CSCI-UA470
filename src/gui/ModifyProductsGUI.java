package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import manager.ProductManager;
import model.Product;
import java.util.List;

public class ModifyProductsGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNewName, txtNewPrice, txtNewQuantity;

    public ModifyProductsGUI() {
        setTitle("Modify Listings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 480);
        JPanel contentPane = new JPanel(null);
        setContentPane(contentPane);

        String[] cols = { "Product Name", "Price", "Quantity" };
        model = new DefaultTableModel(fetchData(), cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 460, 220);
        contentPane.add(scroll);

        txtNewName = new JTextField();
        txtNewName.setBounds(130, 260, 200, 25);
        contentPane.add(new JLabel("New Name:")).setBounds(20, 260, 100, 25);
        contentPane.add(txtNewName);
        txtNewPrice = new JTextField();
        txtNewPrice.setBounds(130, 300, 200, 25);
        contentPane.add(new JLabel("New Price:")).setBounds(20, 300, 100, 25);
        contentPane.add(txtNewPrice);
        txtNewQuantity = new JTextField();
        txtNewQuantity.setBounds(130, 340, 200, 25);
        contentPane.add(new JLabel("New Quantity:")).setBounds(20, 340, 100, 25);
        contentPane.add(txtNewQuantity);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtNewName.setText(model.getValueAt(r, 0).toString());
                    txtNewPrice.setText(model.getValueAt(r, 1).toString());
                    txtNewQuantity.setText(model.getValueAt(r, 2).toString());
                } else {
                    txtNewName.setText("");
                    txtNewPrice.setText("");
                    txtNewQuantity.setText("");
                }
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(360, 260, 120, 30);
        btnUpdate.addActionListener(e -> {
            btnUpdate_click();
        });
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(360, 310, 120, 30);
        btnDelete.addActionListener(e -> {
            btnDelete_click();
        });
        contentPane.add(btnDelete);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Object[][] fetchData() {
        ProductManager productManager = new ProductManager();
        List<Product> list = productManager.getProducts();
        Object[][] arr = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            arr[i] = new Object[] { p.getName(), p.getPrice(), p.getQuantity() };
        }
        return arr;
    }

    public void btnUpdate_click() {
        int r = table.getSelectedRow();

        ProductManager productManager = new ProductManager();
        Product prod = productManager.getProducts().get(r);
        prod.setName(txtNewName.getText());
        prod.setPrice(Double.parseDouble(txtNewPrice.getText()));
        prod.setQuantity(Integer.parseInt(txtNewQuantity.getText()));
        productManager.saveProducts(productManager.getProducts());
        model.setValueAt(prod.getName(), r, 0);
        model.setValueAt(prod.getPrice(), r, 1);
        model.setValueAt(prod.getQuantity(), r, 2);
        JOptionPane.showMessageDialog(this, "Product updated successfully!");
    }

    public void btnDelete_click() {
        int r = table.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
            return;
        }

        ProductManager productManager = new ProductManager();
        productManager.removeProduct(r);

        model.removeRow(r);

        txtNewName.setText("");
        txtNewPrice.setText("");
        txtNewQuantity.setText("");

        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
    }
}
