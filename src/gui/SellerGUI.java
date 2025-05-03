package gui;

import javax.swing.*;
import java.awt.*;

public class SellerGUI extends JFrame {
    public SellerGUI() {
        setTitle("Seller Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton uploadProductBtn = new JButton("Upload Product");
        JButton modifyListingsBtn = new JButton("Modify Listings");
        JButton viewStatsBtn = new JButton("View Sales Statistics");
        JButton respondCommentsBtn = new JButton("Respond to Comments");

        uploadProductBtn.addActionListener(e -> new UploadProductGUI());
        modifyListingsBtn.addActionListener(e -> new ModifyListingsGUI());
        viewStatsBtn.addActionListener(e -> new ViewSalesStatisticsGUI());
        respondCommentsBtn.addActionListener(e -> new RespondToCommentsGUI());

        setLayout(new GridLayout(4, 1));
        add(uploadProductBtn);
        add(modifyListingsBtn);
        add(viewStatsBtn);
        add(respondCommentsBtn);

        setVisible(true);
    }
}
