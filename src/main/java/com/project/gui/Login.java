package com.project.gui;

import com.project.dto.UserEntity;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panelField;
    private JTextField tf_login_username;
    private JTextField tf_login_password;
    private JButton b_login;
    private JLabel jl_login_username;
    private JLabel jl_login_password;
    private JButton b_signup;

    private UserService userService;

    public Login() {
        add(panelField);
        setSize(400, 380);
        setTitle("The YTU Project Login Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        userService = new UserService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        b_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = tf_login_username.getText().trim();
                String password = tf_login_password.getText().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    UserEntity user = new UserEntity();
                    user.setUserPassword(password);
                    user.setUsername(username);
                    user = userService.canLogin(user);

                    if (user != null) {
                        showMessage("Başarılı", "Yönlendiriliyorsunuz", JOptionPane.INFORMATION_MESSAGE);
                        int role = user.getRoleId().getRoleId().intValue();
                        switch (role) {
                            case 1:
                                openPublic();
                                break;
                            case 2:
                                openPersonal();
                                break;
                            default:

                        }
                    } else {
                        showMessage("Dikkat", "Bilgilerinizi Kontrol Ediniz!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    showMessage("Dikkat", "Boş bırakmayınız!", JOptionPane.WARNING_MESSAGE);
                }


            }
        });

        b_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register();
            }
        });

    }

    private void openPublic() {
        dispose();
        new Public();
    }

    private void openPersonal() {
        dispose();
        new Personal();
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(panelField,
                message,
                title,
                messageType);
    }

}
