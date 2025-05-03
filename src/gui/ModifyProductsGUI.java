import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifyProductsGUI extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNewName;
    private JTextField txtNewPrice;
    private JTextField txtNewQuantity;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JScrollPane scroll;
    private JLabel lblNewName;
    private JLabel lblNewPrice;
    private JLabel lblNewQty;

    public ModifyProductsGUI() {
        setTitle("Modify Listings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 480);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        String[] cols = {"Product Name", "Price", "Quantity"};
        model = new DefaultTableModel(fetchData(), cols);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int r = table.getSelectedRow();
                txtNewName.setText(model.getValueAt(r, 0).toString());
                txtNewPrice.setText(model.getValueAt(r, 1).toString());
                txtNewQuantity.setText(model.getValueAt(r, 2).toString());
            }
        });

        scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 460, 220);
        contentPane.add(scroll);

        lblNewName = new JLabel("New Name:");
        lblNewName.setBounds(20, 260, 100, 25);
        contentPane.add(lblNewName);

        txtNewName = new JTextField();
        txtNewName.setBounds(130, 260, 200, 25);
        contentPane.add(txtNewName);

        lblNewPrice = new JLabel("New Price:");
        lblNewPrice.setBounds(20, 300, 100, 25);
        contentPane.add(lblNewPrice);

        txtNewPrice = new JTextField();
        txtNewPrice.setBounds(130, 300, 200, 25);
        contentPane.add(txtNewPrice);

        lblNewQty = new JLabel("New Quantity:");
        lblNewQty.setBounds(20, 340, 100, 25);
        contentPane.add(lblNewQty);

        txtNewQuantity = new JTextField();
        txtNewQuantity.setBounds(130, 340, 200, 25);
        contentPane.add(txtNewQuantity);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(360, 260, 120, 30);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                Product prod = Product.listProducts().get(r);
                prod.setName(txtNewName.getText());
                prod.setPrice(txtNewPrice.getText());
                prod.setQuantity(txtNewQuantity.getText());
                model.setValueAt(prod.getName(),     r, 0);
                model.setValueAt(prod.getPrice(),    r, 1);
                model.setValueAt(prod.getQuantity(), r, 2);
                JOptionPane.showMessageDialog(ModifyProductsGUI.this,
                    "Product updated successfully!");
            }
        });
        contentPane.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(360, 310, 120, 30);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                Product prod = Product.listProducts().get(r);
                Product.removeProduct(prod);
                model.removeRow(r);
                txtNewName.setText("");
                txtNewPrice.setText("");
                txtNewQuantity.setText("");
                JOptionPane.showMessageDialog(ModifyProductsGUI.this,
                    "Product deleted successfully!");
            }
        });
        contentPane.add(btnDelete);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Object[][] fetchData(){
        List<Product> list = Product.listProducts();
        Object[][] arr = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++){
            Product p = list.get(i);
            arr[i] = new Object[]{ p.getName(), p.getPrice(), p.getQuantity() };
        }
        return arr;
    }
    
}
