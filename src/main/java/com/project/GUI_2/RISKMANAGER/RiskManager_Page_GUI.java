package com.project.GUI_2.RISKMANAGER;


import com.project.GUI_2.CREATE.createRisk_GUI;
import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.FORM.signIn_GUI;
import com.project.GUI_2.SEARCH.riskSearch_GUI;
import com.project.GUI_2.SEARCH.roleSearch_GUI;
import com.project.GUI_2.SEARCH.userSearch_GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskManager_Page_GUI extends JFrame{
    private JPanel riskManagerPagePanel;
    private JButton bt_riskmanager_signout;
    private JButton bt_riskmanager_back;
    private JTextPane tp_roleowner_requestman;
    private JTextPane tp_roleowner_userman;
    private JTextPane tp_roleowner_roleman;
    private JButton bt_riskmanager_createRisk;
    private JButton bt_riskmanager_searchRole;
    private JButton bt_riskmanager_searchRisk;
    private JButton bt_riskmanager_searchUser;

    public RiskManager_Page_GUI(){
        add(riskManagerPagePanel);


        setSize(700,600);

        setTitle("You are Risk Manager!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_riskmanager_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new signIn_GUI();
            }
        });

        bt_riskmanager_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });


        bt_riskmanager_searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new userSearch_GUI();
            }
        });

        bt_riskmanager_searchRisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new riskSearch_GUI();
            }
        });

        bt_riskmanager_searchRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new roleSearch_GUI();
            }
        });


        bt_riskmanager_createRisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new createRisk_GUI();
            }
        });


    }
}
