package com.project.GUI_2.CREATE;

import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.USER.Manager_GUI;
import com.project.dto.RequestEntity;
import com.project.service.UserRequestService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createRequest_GUI extends JFrame{
    private JPanel createRequestpanel;
    private JTextField tf_createRequest_username;
    private JComboBox cb_createRequest_System;
    private JTextArea ta_createRequest_description;
    private JTextField tf_createRequest_roleName;
    private JButton bt_createRequest_cancel;
    private JButton bt_createRequest_create;
    private JButton bt_createRequest_back;
    private JButton bt_createRequest_signout;



    private UserRequestService userRequestService;

    /*
     * This function helps to creating a new Request for assign a Role to User successfully,
     * getting all Infos and setting to Request Table
     *
     * action of button BACK -forwarding to Manager Page
     * action of button CANCEL -forwarding to Welcome Page
     * action of button SIGN OUT - forwarding to Welcome Page
     * action of button CREATE - creating a new Request
     * */
    public createRequest_GUI() {

        add(createRequestpanel);
        setSize(700, 900);
        setTitle("You are creating a new Request!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userRequestService = new UserRequestService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createRequest_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager_GUI();
            }
        });

        bt_createRequest_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeGUI();
            }
        });


        bt_createRequest_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager_GUI();
            }
        });

        bt_createRequest_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestEntity request = new RequestEntity();


                String username = tf_createRequest_username.getText().trim();
                String roleName = tf_createRequest_roleName.getText().trim();
                String description = ta_createRequest_description.getText();
                String system= String.valueOf(cb_createRequest_System.getSelectedItem());


                // checking of all Text fields and confirming
                if (!description.isEmpty() || !roleName.isEmpty() || !username.isEmpty() || !system.isEmpty() ){

                    // if there is no blank field--> setting all Info to Request table

                    request.setRequestDescription(description);
                    request.setRequestRole(roleName);
                    request.setRequestUserId(username);
                    request.setRequestSystem(system);

                    request = userRequestService.save(request);

                    if (request != null) {
                        showMessage("Done", "You have created a new Request", JOptionPane.INFORMATION_MESSAGE);
                        setEmptyInputs();
                        // massage GUI--> successfully creating a new Request
                    }else {
                        showMessage("Error", "You can not request for this User", JOptionPane.WARNING_MESSAGE);
                        // massage GUI--> trying to create not new Request
                    }


                } else showMessage("","",1);
            }
        });

    }



    /*
     * After creating a new User, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {
        tf_createRequest_roleName.setText("");
        ta_createRequest_description.setText("");
        tf_createRequest_username.setText("");


    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createRequestpanel,
                message,
                title,
                messageType);
    }
}







