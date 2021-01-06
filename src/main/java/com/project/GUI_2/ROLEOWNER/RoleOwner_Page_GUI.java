package com.project.GUI_2.ROLEOWNER;

import com.project.GUI_2.CREATE.createRole_GUI;
import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.SEARCH.searchRequest_GUI;
import com.project.GUI_2.SEARCH.searchRole_GUI;
import com.project.GUI_2.SEARCH.searchUser_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleOwner_Page_GUI extends JFrame {
    private JPanel roleOwnerPagePanel;
    private JTextPane tp_roleowner_requestman;
    private JTextPane tp_roleowner_userman;
    private JTextPane tp_roleowner_roleman;
    private JButton bt_roleowner_signout;
    private JButton bt_roleowner_searchRequest;
    private JButton bt_roleowner_searchRole;
    private JButton bt_roleowner_createRole;
    private JButton bt_roleowner_searchUser;

    public RoleOwner_Page_GUI() {
        add(roleOwnerPagePanel);
        setSize(700, 600);

        setTitle("You are Role Owner!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_roleowner_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });

        bt_roleowner_searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchUser_GUI();
            }
        });

        bt_roleowner_searchRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRequest_GUI();
            }
        });

        bt_roleowner_searchRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRole_GUI();
            }
        });

        bt_roleowner_createRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new createRole_GUI();
            }
        });
    }

}

