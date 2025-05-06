package gui;
import javax.swing.*;
import java.awt.*;
import manager.ProfileManager;
import model.Profile;
import manager.ProductManager;

public class CreateProfileGUI extends JFrame {

    public CreateProfileGUI() {
        setTitle("Create Profile");
        setSize(300, 300);
        setLocationRelativeTo(null);

        JTextField usernameField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        JLabel lblusername = new JLabel("Username");
        JLabel lblname = new JLabel("Name");
        JLabel lblphone = new JLabel("Phone");
        JLabel lblpassword = new JLabel("Password");

        submit.addActionListener(e -> {
            btnSubmit_click(usernameField, nameField, phoneField, passwordField);
        });

        cancel.addActionListener(e->{
            btnCancel_click();
        });

        setLayout(new GridLayout(5, 2));
        add(lblusername); add(usernameField);
        add(lblname);     add(nameField);
        add(lblphone);    add(phoneField);
        add(lblpassword); add(passwordField);
        add(submit);
        add(cancel);

        setVisible(true);
    }

    public void btnSubmit_click(JTextField usernameField, JTextField nameField, JTextField phoneField, JPasswordField passwordField){
        String username = usernameField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());

        ProfileManager profileManager = new ProfileManager();
        profileManager.createProfile(username, name, phone, password);
        new BuyerLoginGUI();
        dispose();
    }

    public void btnCancel_click(){
        new BuyerLoginGUI();
        dispose();
    }
}
