import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class ViewSalesStatisticsGUI extends JFrame {
    private JTable table;
    public ViewSalesStatisticsGUI() {
        super("Sales Statistics");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] columns = {
            "Product Name",
            "Number Sold",
            "Revenue"
        };
        Object[][] data = fetchData();
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
        setSize(400, 200);}

    private Object[][] fetchData(){
        Collection<Product> list = Product.listProducts();
        Object[][] rows = new Object[list.size()][3];
        int i = 0;
        for (Product p : list){
            rows[i++] = new Object[]{
                p.getName(),
                p.getUnitsSold(),
                p.getRevenue()
            };
        }
        return rows;
    }

}
