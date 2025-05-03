import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RespondCommentsGUI extends JFrame {
    private JPanel commentsPanel;

    public RespondCommentsGUI() {
        super("Respond to Comments");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel content = new JPanel(new BorderLayout(10,10));
        setContentPane(content);
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(commentsPanel);
        scroll.setPreferredSize(new Dimension(620, 300));
        content.add(scroll);
        for (String commentText : fetchComments()) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
            JLabel lbl = new JLabel("<html><b>Comment:</b> " + commentText + "</html>");
            row.add(lbl);
            JPanel action = new JPanel();
            JTextField responseField = new JTextField(25);
            JButton btnRespond = new JButton("Respond");
            action.add(responseField);
            action.add(btnRespond);
            row.add(action);
            btnRespond.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String resp = responseField.getText();
                    row.remove(action);
                    JLabel yourResp = new JLabel(
                        "<html><b>Your response:</b> " + resp + "</html>"
                    );
                    row.add(yourResp);
                    responseField.setEnabled(false);
                    btnRespond.setEnabled(false);
                    row.revalidate();
                }
            });
            commentsPanel.add(row);
        }
        pack();
    }

    private List<String> fetchComments() {
        return Arrays.asList(
            "Great product, but the packaging was damaged.",
            "How do I reset this device?",
            "I found a bug in the UI.",
            "Fast shipping—thank you!",
            "Do you ship internationally?",
            "Can I get a bulk discount?");
    }

}
