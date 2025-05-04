package gui;

import javax.swing.*;
import java.awt.*;
import manager.ProfileManager;
import manager.ProductManager;

public class MainGUI extends JFrame {
    private final ProfileManager profileMgr;
    private final ProductManager productMgr;

    public MainGUI() {
        this(new ProfileManager(), new ProductManager());
    }

    public MainGUI(ProfileManager pm, ProductManager pr) {
        super("Marketplace - Main Menu");
        this.profileMgr = pm;
        this.productMgr = pr;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);

        JButton buyerBtn  = new JButton("Buyer");
        JButton sellerBtn = new JButton("Seller");

        buyerBtn.addActionListener(e -> {
            btnBuyer_click();
        });
        sellerBtn.addActionListener(e -> {
            btnSeller_click();
        });

        JPanel p = new JPanel(new GridLayout(2,1,10,10));
        p.add(buyerBtn);
        p.add(sellerBtn);
        add(p);

        setVisible(true);
    }

    public void btnBuyer_click(){
        new BuyerLoginGUI(profileMgr, productMgr);
        dispose();
    }

    public void btnSeller_click(){
        new SellerGUI(profileMgr, productMgr);
        dispose();
    }
}
