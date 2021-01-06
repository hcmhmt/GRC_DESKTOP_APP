package com.project.GUI_2.FORM;

import com.project.GUI_2.ENDUSER.EndUser_Page_GUI;
import com.project.GUI_2.MANAGER.Manager_Page_GUI;
import com.project.GUI_2.RISKMANAGER.RiskManager_Page_GUI;
import com.project.GUI_2.ROLEOWNER.RoleOwner_Page_GUI;
import com.project.config.SessionUser;
import com.project.dto.StatusEnum;
import com.project.dto.UserEntity;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signIn_GUI extends JFrame {
    private JPanel signinpanel;
    private JButton bt_signIn_back;
    private JTextField tf_signIn_username;
    private JPasswordField pf_signIn_password;
    private JButton bt_signIn_singin;
    private JButton bt_signIn_sifremiunuttum;

    private UserService userService;
    private SessionUser sessionUser;

    public signIn_GUI() {
        add(signinpanel);
        setSize(600, 400);
        setTitle("Please login to your GRC Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userService = new UserService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_signIn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });

        bt_signIn_sifremiunuttum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ForgotPASSWORD_GUI();
            }
        });

        bt_signIn_singin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tf_signIn_username.getText().trim();
                String password = String.valueOf(pf_signIn_password.getPassword());

                if (!username.isEmpty() && !password.isEmpty()) {
                    UserEntity user = new UserEntity();
                    user.setUserPassword(password);
                    user.setUsername(username);
                    user = userService.canLogin(user);

                    if (user != null && user.getUserIsActive().equals(StatusEnum.ACTIVE.name())) {
                        new SessionUser().setInfo(user.getUserId(), user.getUsername());
                        switch (user.getUserType().toString()) {
                            case "MANAGER":
                                dispose();
                                showMessage("Done!", "Forwarding to Your Manager Page", JOptionPane.INFORMATION_MESSAGE);
                                new Manager_Page_GUI();
                                break;
                            case "ROLE_OWNER":
                                dispose();
                                showMessage("Done!", "Forwarding to Role Owner Page", JOptionPane.INFORMATION_MESSAGE);
                                new RoleOwner_Page_GUI();
                                break;
                            case "RISK_MANAGER":
                                dispose();
                                showMessage("Done!", "Forwarding to Risk Manager Page", JOptionPane.INFORMATION_MESSAGE);
                                new RiskManager_Page_GUI();
                                break;
                            case "END_USER":
                                dispose();
                                showMessage("Done!", "Forwarding to End User Page", JOptionPane.INFORMATION_MESSAGE);
                                new EndUser_Page_GUI();
                                break;
                        }
                    } else {
                        showMessage("Warning", "Invalid Password", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    showMessage("Warning", "Missing Info", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(signinpanel,
                message,
                title,
                messageType);
    }
}
