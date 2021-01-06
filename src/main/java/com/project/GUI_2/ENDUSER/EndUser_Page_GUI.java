package com.project.GUI_2.ENDUSER;

import com.project.GUI_2.CREATE.createRequest_GUI;
import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.SEARCH.searchRequest_GUI;
import com.project.GUI_2.SEARCH.searchRole_GUI;
import com.project.GUI_2.SEARCH.searchUser_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndUser_Page_GUI extends JFrame {
    private JPanel enduserPagePanel;
    private JButton bt_enduser_signout;
    private JTextPane tp_roleowner_requestman;
    private JTextPane tp_enduser_userman;
    private JTextPane tp_enduser_roleman;
    private JButton bt_enduser_searchRequest;
    private JButton bt_enduser_searchRole;
    private JButton bt_enduser_searchUser;
    private JButton bt_enduser_createRequest;

    public EndUser_Page_GUI() {
        add(enduserPagePanel);
        setSize(700, 600);
        setTitle("You are a User!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_enduser_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });

        bt_enduser_searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchUser_GUI();
            }
        });

        bt_enduser_searchRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRequest_GUI();
            }
        });

        bt_enduser_searchRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRole_GUI();
            }
        });

        bt_enduser_createRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new createRequest_GUI();
            }
        });


    }
}
