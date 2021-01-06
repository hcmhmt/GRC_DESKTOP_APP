package com.project.GUI_2.MANAGER;

import com.project.GUI_2.CREATE.createRequest_GUI;
import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.FORM.signUP_FORM_GUI;
import com.project.GUI_2.SEARCH.searchRequest_GUI;
import com.project.GUI_2.SEARCH.searchRole_GUI;
import com.project.GUI_2.SEARCH.searchUser_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * TO DO
 *
 * 1- My Settings butonuna tiklaninca kullanicin sayfasina gidilsin??
 * 2- My roles butotnuna tikladiginda kullanicinin tum rolleri gorunsun??
 * */


public class Manager_Page_GUI extends JFrame {
    private JTextPane tp_manager_userman;
    private JTextPane tp_manager_roleman;
    private JTextPane tp_manager_requestman;
    private JPanel managerPagePanel;
    private JButton bt_manager_signout;
    private JPanel managerPagePanel2;
    private JButton bt_manager_searchRequest;
    private JButton bt_manager_createRequest;
    private JButton bt_manager_searchRole;
    private JButton bt_manager_createUser;
    private JButton bt_manager_searchUser;

    public Manager_Page_GUI() {
        add(managerPagePanel);
        setSize(700, 600);
        setTitle("You are Manager!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_manager_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });

        bt_manager_searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchUser_GUI();
            }
        });

        bt_manager_searchRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRequest_GUI();
            }
        });

        bt_manager_searchRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchRole_GUI();
            }
        });


        bt_manager_createRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new createRequest_GUI();
            }
        });

        bt_manager_createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signUP_FORM_GUI();
            }
        });
    }

}
