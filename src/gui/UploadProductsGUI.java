import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadProductsGUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JButton btnUpload;
    private JLabel lblName;
    private JLabel lblPrice;
    private JLabel lblQuantity;

    public UploadProductsGUI() {
        setTitle("Upload New Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 260);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 100, 25);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 220, 25);
        contentPane.add(txtName);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 60, 100, 25);
        contentPane.add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(120, 60, 220, 25);
        contentPane.add(txtPrice);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(20, 100, 100, 25);
        contentPane.add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(120, 100, 220, 25);
        contentPane.add(txtQuantity);

        btnUpload = new JButton("Upload Item");
        btnUpload.setBounds(140, 160, 120, 30);
        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performUpload();
            }
        });
        contentPane.add(btnUpload);
    }

    private void performUpload() {
        String name  = txtName.getText();
        String price = txtPrice.getText();
        String qty   = txtQuantity.getText();   
        Product p = new Product(name, price, qty);
        Product.addProduct(p);
        JOptionPane.showMessageDialog(this, "Product added!");
        dispose();
    }
    
}
