import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellerGUI extends JFrame {
	JPanel contentPane;
	JButton btnUploadProducts;
	JButton btnModifyProducts;
	JButton btnViewSales;
	JButton btnRespondComments;
	JButton btnExit;
	public SellerGUI() {
		super("Seller Dashboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUploadProducts = new JButton("Upload New Products");
		btnUploadProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUploadProducts();
			}
		});
		btnUploadProducts.setBounds(159,  35, 200, 50);
		contentPane.add(btnUploadProducts);

		btnModifyProducts = new JButton("Modify Products");
		btnModifyProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyProducts();
			}
		});
		btnModifyProducts.setBounds(159,  95, 200, 50);
		contentPane.add(btnModifyProducts);

		btnViewSales = new JButton("View Sales Statistics");
		btnViewSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSalesStatistics();
			}
		});
		btnViewSales.setBounds(159, 155, 200, 50);
		contentPane.add(btnViewSales);
		
		btnRespondComments = new JButton("Respond to Comments");
		btnRespondComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respondComments();
			}
		});
		btnRespondComments.setBounds(159, 215, 200, 50);
		contentPane.add(btnRespondComments);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		btnExit.setBounds(159, 275, 200, 50);
		contentPane.add(btnExit);
	}
	
    private void openUploadProducts() {
        UploadProductsGUI upg = new UploadProductsGUI();
        upg.setVisible(true);
    }
	private void viewSalesStatistics() {
        ViewSalesStatisticsGUI vssg = new ViewSalesStatisticsGUI();
        vssg.setVisible(true);
    }
	private void modifyProducts() {
        ModifyProductsGUI mpg = new ModifyProductsGUI();
        mpg.setVisible(true);
    }
	private void respondComments() {
        RespondCommentsGUI rcg = new RespondCommentsGUI();
        rcg.setVisible(true);
    }
}
