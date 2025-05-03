package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import manager.ProductManager;
import model.Product;
import data.DataUtil;
import java.util.List;

public class ModifyProductsGUI extends JFrame {
    private ProductManager productManager;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNewName, txtNewPrice, txtNewQuantity;

    public ModifyProductsGUI(ProductManager productManager) {
        this.productManager = productManager;
        setTitle("Modify Listings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 480);
        JPanel contentPane = new JPanel(null);
        setContentPane(contentPane);

        String[] cols = {"Product Name","Price","Quantity"};
        model = new DefaultTableModel(fetchData(), cols);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20,20,460,220);
        contentPane.add(scroll);

        txtNewName = new JTextField(); txtNewName.setBounds(130,260,200,25);
        contentPane.add(new JLabel("New Name:")).setBounds(20,260,100,25);
        contentPane.add(txtNewName);
        txtNewPrice = new JTextField(); txtNewPrice.setBounds(130,300,200,25);
        contentPane.add(new JLabel("New Price:")).setBounds(20,300,100,25);
        contentPane.add(txtNewPrice);
        txtNewQuantity = new JTextField(); txtNewQuantity.setBounds(130,340,200,25);
        contentPane.add(new JLabel("New Quantity:")).setBounds(20,340,100,25);
        contentPane.add(txtNewQuantity);

        table.getSelectionModel().addListSelectionListener(e -> {
            int r = table.getSelectedRow();
            txtNewName.setText(model.getValueAt(r,0).toString());
            txtNewPrice.setText(model.getValueAt(r,1).toString());
            txtNewQuantity.setText(model.getValueAt(r,2).toString());
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(360,260,120,30);
        btnUpdate.addActionListener(e -> {
            int r = table.getSelectedRow();
            Product prod = productManager.getProducts().get(r);
            prod.setName(txtNewName.getText());
            prod.setPrice(Double.parseDouble(txtNewPrice.getText()));
            prod.setQuantity(Integer.parseInt(txtNewQuantity.getText()));
            DataUtil.saveProducts(productManager.getProducts());
            model.setValueAt(prod.getName(),r,0);
            model.setValueAt(prod.getPrice(),r,1);
            model.setValueAt(prod.getQuantity(),r,2);
            JOptionPane.showMessageDialog(this,"Product updated successfully!");
        });
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(360,310,120,30);
        btnDelete.addActionListener(e -> {
            int r = table.getSelectedRow();
            Product prod = productManager.getProducts().get(r);
            productManager.getProducts().remove(prod);
            DataUtil.saveProducts(productManager.getProducts());
            model.removeRow(r);
            txtNewName.setText(""); txtNewPrice.setText(""); txtNewQuantity.setText("");
            JOptionPane.showMessageDialog(this,"Product deleted successfully!");
        });
        contentPane.add(btnDelete);

        setVisible(true);
    }

    private Object[][] fetchData(){
        List<Product> list = productManager.getProducts();
        Object[][] arr = new Object[list.size()][3];
        for(int i=0;i<list.size();i++){
            Product p = list.get(i);
            arr[i] = new Object[]{p.getName(),p.getPrice(),p.getQuantity()};
        }
        return arr;
    }
}
