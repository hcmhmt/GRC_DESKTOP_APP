package com.project.gui;

import javax.swing.*;

public class Public extends JFrame{
    private JPanel panel1;
    private JLabel jl_login_username;
    private JTextField tf_login_username;
    private JLabel jl_login_password;
    private JTextField tf_login_password;
    private JButton b_login;
    private JButton b_signup;

    public Public(){
        add(panel1);
        setSize(400, 380);
        setTitle("The YTU Project PUBLIC Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
