package com.project.GUI_2.FORM;
import com.project.GUI_2.FORM.signUP_FORM_GUI;
import com.project.GUI_2.FORM.signIn_GUI;
import com.project.GUI_2.CREATE.createUser_GUI;
import com.project.GUI_2.MANAGER.Manager_Page_GUI;
import com.project.GUI_2.ROLEOWNER.RoleOwner_Page_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI extends JFrame {
    private JPanel welcomePanel;
    private JButton signINButton;
    private JButton signUpButton;


    public WelcomeGUI(){
        add(welcomePanel);
        setSize(200,200);
        setTitle("Welcome your GRC Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new signUP_FORM_GUI();
            }
        });



        signINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new signIn_GUI();
            }
        });


    }

}
