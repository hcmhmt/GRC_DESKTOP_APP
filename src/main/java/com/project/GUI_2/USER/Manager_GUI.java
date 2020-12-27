package com.project.GUI_2.USER;

import com.project.GUI_2.FORM.WelcomeGUI;

import com.project.GUI_2.FORM.signIn_GUI;
import com.project.GUI_2.SEARCH.riskSearch_GUI;
import com.project.GUI_2.SEARCH.roleSearch_GUI;
import com.project.GUI_2.SEARCH.requestSearch_GUI;
import com.project.GUI_2.SEARCH.userSearch_GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager_GUI extends JFrame{
    private JButton bt_manager_requestman;
    private JButton bt_Manager_mysettings;
    private JButton bt_Manager_myroles;
    private JButton bt_manager_riskman;
    private JButton bt_manager_userman;
    private JButton bt_manager_roleman;
    private JButton bt_manager_signout;
    private JButton bt_manager_back;
    private JPanel managerPanel;


    /*
    * TO DO
    *
    * 1- My Settings butonuna tiklaninca kullanicin sayfasina gidilsin??
    * 2- My roles butotnuna tikladiginda kullanicinin tum rolleri gorunsun??
    * */

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public Manager_GUI() {
            add(managerPanel);
            setSize(1000, 1200);
            setTitle("You are Manager!");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setVisible(true);


            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            bt_manager_back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new signIn_GUI();
                }
            });

            bt_manager_signout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new WelcomeGUI();
                }
            });

            bt_manager_riskman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new riskSearch_GUI();
            }
        });

        bt_manager_requestman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new requestSearch_GUI();
            }
        });

        bt_manager_roleman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new roleSearch_GUI();
            }
        });

        bt_manager_userman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new userSearch_GUI();
            }
        });


        bt_Manager_myroles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO
            }
        });

        bt_Manager_mysettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO
            }
        });


    }
}


