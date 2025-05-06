package gui;

import javax.swing.*;

public class SellerGUI extends JFrame {

    public SellerGUI() {
        super("Seller Dashboard");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnUpload = new JButton("Upload New Products");
        btnUpload.setBounds(159, 35, 200, 50);
        btnUpload.addActionListener(e -> openUploadProducts());
        contentPane.add(btnUpload);

        JButton btnModify = new JButton("Modify Products");
        btnModify.setBounds(159, 95, 200, 50);
        btnModify.addActionListener(e -> modifyProducts());
        contentPane.add(btnModify);

        JButton btnView = new JButton("View Sales Statistics");
        btnView.setBounds(159, 155, 200, 50);
        btnView.addActionListener(e -> viewSalesStatistics());
        contentPane.add(btnView);

        JButton btnRespond = new JButton("Respond to Comments");
        btnRespond.setBounds(159, 215, 200, 50);
        btnRespond.addActionListener(e -> respondComments());
        contentPane.add(btnRespond);

        JButton btnExit = new JButton("Return to Main Menu");
        btnExit.setBounds(159, 275, 200, 50);
        btnExit.addActionListener(e -> {
            exit_click();
        });
        add(btnExit);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void exit_click() {
        new MainGUI();
        dispose();
    }

    public void openUploadProducts() {
        new UploadProductsGUI().setVisible(true);
    }

    public void modifyProducts() {
        new ModifyProductsGUI().setVisible(true);
    }

    public void viewSalesStatistics() {
        new ViewSalesStatisticsGUI().setVisible(true);
    }

    public void respondComments() {
        new RespondCommentsGUI().setVisible(true);
    }
}
