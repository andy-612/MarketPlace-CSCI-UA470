package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import manager.ProductManager;
import model.Product;
import java.awt.*;
import java.util.Collection;

public class ViewSalesStatisticsGUI extends JFrame {
    private ProductManager productManager;

    public ViewSalesStatisticsGUI(ProductManager productManager) {
        super("Sales Statistics");
        this.productManager = productManager;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,200);

        String[] columns = {"Product Name","Number Sold","Revenue"};
        Object[][] data = fetchData();
        JTable table = new JTable(new DefaultTableModel(data, columns));
        add(new JScrollPane(table), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Object[][] fetchData(){
        Collection<Product> list = productManager.getProducts();
        Object[][] rows = new Object[list.size()][3];
        int i=0;
        for(Product p: list){
            rows[i++] = new Object[]{p.getName(), p.getUnitsSold(), p.getRevenue()};
        }
        return rows;
    }
}
