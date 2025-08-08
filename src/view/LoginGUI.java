package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private LoginController loginController;

    private JPanel panel;
    private JLabel lbLogin, lbPass, checkUser;
    private JTextField loginEntry;
    private JPasswordField passEntry;
    private JButton btnLogin, btnCancel;

    public void setLoginController(LoginController loginController){
        this.loginController = loginController;
    }

    public LoginGUI(){
        settings();
        createPanels();

        setVisible(false);
    }

    private void settings(){
        setTitle("Login");
        setSize(200,150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
    }

    private void createPanels(){

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        lbLogin = new JLabel("Login");
        lbPass = new JLabel("Password");
        loginEntry = new JTextField();
        passEntry = new JPasswordField();
        passEntry.setEchoChar('*');
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        panel.add(lbLogin, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 80;
        gbc.ipady = 10;
        panel.add(loginEntry, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        panel.add(lbPass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 80;
        gbc.ipady = 10;
        panel.add(passEntry, gbc);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.clickLoginBtn();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 10;
        gbc.ipady = 2;
        panel.add(btnLogin, gbc);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.clickCancelBtn();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 10;
        gbc.ipady = 2;
        panel.add(btnCancel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 10;
        gbc.ipady = 2;
        gbc.gridwidth = 2;
        checkUser = new JLabel();
        panel.add(checkUser, gbc);
        add(panel);
    }

    public void setCheckUser(String str){
        checkUser.setText(str);
    }

    public void setLoginField(String str){
        loginEntry.setText(str);
    }
    public void setPassEntry(Strung str){
        passEntry.setText(str);
    }
    public String getLogin(){
        return loginEntry.getText();
    }

    public String getPassword(){
        return passEntry.getText();
    }

}
