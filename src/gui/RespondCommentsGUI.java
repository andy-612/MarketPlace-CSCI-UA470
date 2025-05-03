package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import manager.ProductManager;
import model.Product;
import data.DataUtil;

public class RespondCommentsGUI extends JFrame {
    private ProductManager productManager;
    private JPanel commentsPanel;

    public RespondCommentsGUI(ProductManager productManager) {
        super("Respond to Comments");
        this.productManager = productManager;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(commentsPanel);
        scroll.setPreferredSize(new Dimension(620,300));
        add(scroll);

        for(Product p: productManager.getProducts()){
            for(String review: p.getReviews()){
                addCommentRow(p, review);
            }
        }

        pack();
        setVisible(true);
    }

    private void addCommentRow(Product p, String review){
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel("<html><b>"+p.getName()+":</b> " + review.replaceAll("\n","<br>")+"</html>");
        row.add(lbl);
        JPanel action = new JPanel();
        JTextField responseField = new JTextField(25);
        JButton btnRespond = new JButton("Respond");
        action.add(responseField); action.add(btnRespond);
        row.add(action);

        btnRespond.addActionListener(e -> {
            String resp = responseField.getText();
            p.addResponse(resp);
            DataUtil.saveProducts(productManager.getProducts());
            row.remove(action);
            JLabel yourResp = new JLabel("<html><b>Your response:</b> " + resp + "</html>");
            row.add(yourResp);
            responseField.setEnabled(false);
            btnRespond.setEnabled(false);
            row.revalidate(); row.repaint();
        });

        commentsPanel.add(row);
    }
}