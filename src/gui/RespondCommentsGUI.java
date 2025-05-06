package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import manager.ProductManager;
import model.Product;

public class RespondCommentsGUI extends JFrame {
    private JPanel commentsPanel;

    public RespondCommentsGUI() {
        super("Respond to Comments");
        setSize(new Dimension(450, 600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));

        boolean flag = false;

        ProductManager productManager = new ProductManager();
        ArrayList<Product> products = productManager.getProducts();
        for(Product p: products){
            for(String review: p.getReviews()){
                addCommentRow(p, review, products);
                flag = true;
            }
        }

        if(flag == false){
            JPanel temp = new JPanel();
            temp.add(new JLabel("<html><center>Nothing is here</center></html>"));
            commentsPanel.add(temp);
        }


        JButton btnExit = new JButton("Return to Main Menu");
        btnExit.addActionListener(e -> {
            dispose();
        });
        JPanel temp = new JPanel();
        temp.add(btnExit);
        commentsPanel.add(temp);

        JScrollPane scroll = new JScrollPane(commentsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCommentRow(Product p, String review, ArrayList<Product> products){
        String temp = review.replaceAll("Buyer:", p.getName() + " buyer: ");
        temp = temp.replaceAll("\n", "<p>");
        JLabel lbl = new JLabel("<html><body style = 'width:200px'>" + temp + "</body></html>");
        commentsPanel.add(lbl);
        JPanel action = new JPanel();
        JTextField responseField = new JTextField(25);
        JButton btnRespond = new JButton("Respond");
        action.add(responseField); action.add(btnRespond);
        commentsPanel.add(action);

        btnRespond.addActionListener(e -> {
            String resp = responseField.getText();
            p.addResponse(resp, review);
            ProductManager productManager = new ProductManager();
            productManager.saveProducts(products);
            JOptionPane.showMessageDialog(this, "Comment Added!");
            dispose();
        });

    }
}