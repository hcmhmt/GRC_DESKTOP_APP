package com.project.gui;

import com.project.dto.UserEntity;
import com.project.dto.RoleEntity;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    private JPanel registerPanelField;
    private JLabel jl_register_username;
    private JTextField tf_register_username;
    private JLabel jl_register_password;
    private JTextField tf_register_password;
    private JButton b_register_signup;
    private JButton b_register_back;
    private JTextField tf_register_fullname;

    private UserService userService;

    public Register() {
        add(registerPanelField);
        setSize(400, 380);
        setTitle("The YTU Project Register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userService = new UserService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        b_register_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage();
            }
        });

        b_register_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* UserEntity user = new UserEntity();
                UserRoleEntity role = new UserRoleEntity();

                role.setRoleId(1L);
                role.setRoleName("Public");

                user.set(tf_register_fullname.getText().trim().isEmpty() ? "" : tf_register_username.getText().trim());
                user.setUsername(tf_register_username.getText().trim().isEmpty() ? "" : tf_register_fullname.getText().trim());
                user.setUserPassword(tf_register_password.getText().trim().isEmpty() ? "" : tf_register_password.getText().trim());
                user.setRoleId(role);
                user = userService.save(user);
                if (user != null) {
                    showMessage("Succesfull", "Successfull message", JOptionPane.INFORMATION_MESSAGE);
                    setEmptyInputs();
                    loginPage();
                }else {
                    showMessage("Warning", "Exist Username", JOptionPane.WARNING_MESSAGE);
                }*/
            }
        });

    }

    private void loginPage() {
        dispose();
        new Login();
    }

    private void setEmptyInputs() {
        tf_register_username.setText("");
        tf_register_fullname.setText("");
        tf_register_password.setText("");
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(registerPanelField,
                message,
                title,
                messageType);
    }

}
