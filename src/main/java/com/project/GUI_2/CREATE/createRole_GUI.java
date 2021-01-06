package com.project.GUI_2.CREATE;

import com.project.GUI_2.ROLEOWNER.RoleOwner_Page_GUI;
import com.project.dto.RiskEntity;
import com.project.dto.RoleEntity;
import com.project.dto.UserEntity;
import com.project.service.RiskService;
import com.project.service.RoleService;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/*
 * This Page can opened only by Role Owner.
 * Another User should not create a new Role, so this Page is reachable from Role Owner Side.
 * */

public class createRole_GUI extends JFrame {

    private JTextField tf_createRole_rolename;
    private JButton bt_createRole_cancel;
    private JComboBox cb_createRole_System;
    private JComboBox cb_createRole_RiskLevel;
    private JTextArea ta_createRole_description;
    private JTextField tf_createRole_roleOwner;
    private JButton bt_createRole_create;
    private JButton bt_createRole_back;
    private JButton bt_createRole_signout;
    private JPanel createRolePanel;
    private JComboBox cb_createRole_users;

    private RoleService roleService;
    private RiskService riskService;
    private UserService userService;

/*

TO DO

* 1-Role Owner secmek icin TF yaptim fakat, User tablosundan Usertype = Role Owner olanlari getirebilir miyiz??Combobox seklinde
* 2-
* */

    /*
     * This function helps to creating a new Role successfully, getting all Infos and setting to Role Table
     *
     * action of button BACK -forwarding to Manager Page
     * action of button CANCEL -forwarding to Welcome Page
     * action of button SIGN OUT - forwarding to Welcome Page
     * action of button CREATE - creating a new Role
     * */
    public createRole_GUI() {
        add(createRolePanel);
        setSize(700, 900);
        setTitle("You are creating a new Role!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        roleService = new RoleService();
        riskService = new RiskService();
        userService = new UserService();

        Map<Long, String> riskMap = new HashMap<>();
        Map<Long, String> userMap = new HashMap<>();

        riskService.getAll().forEach(riskEntity -> {
            cb_createRole_RiskLevel.addItem(riskEntity.getRiskName());
            riskMap.put(riskEntity.getRiskId(), riskEntity.getRiskName());
        });

        userService.getAll().forEach(userEntity -> {
            cb_createRole_users.addItem(userEntity.getUsername());
            userMap.put(userEntity.getUserId(), userEntity.getUsername());
        });


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createRole_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        bt_createRole_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RoleOwner_Page_GUI();
            }
        });

        bt_createRole_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleEntity role = new RoleEntity();
                RiskEntity risk = new RiskEntity();
                UserEntity user = new UserEntity();

                String roleName = tf_createRole_rolename.getText().trim();
                String description = ta_createRole_description.getText();
                String roleOwner = cb_createRole_users.getSelectedItem().toString().trim();
                String riskName = cb_createRole_RiskLevel.getSelectedItem().toString();
                String system = String.valueOf(cb_createRole_System.getSelectedItem());

                // checking of all Text fields and confirming
                if (!roleName.isEmpty() || !roleOwner.isEmpty()) {

                    for (Map.Entry<Long, String> entry : riskMap.entrySet()) {
                        if (entry.getValue().equals(riskName)) {
                            risk.setRiskId(entry.getKey());
                            risk.setRiskName(entry.getValue());
                            break;
                        }
                    }
                    for (Map.Entry<Long, String> entry : userMap.entrySet()) {
                        if (entry.getValue().equals(roleOwner)) {
                            user.setUserId(entry.getKey());
                            user.setUsername(entry.getValue());
                            break;
                        }
                    }

                    // if there is no blank field--> setting all Info to Role table
                    role.setRoleName(roleName);
                    role.setDescription(description);
                    role.setRoleOwnerID(roleOwner);
                    role.setRisk(risk.getRiskId());
                    role.setSystem(system);

                    role = roleService.save(role, user);

                    if (role != null) {
                        showMessage("Done", "You have created a new Role", JOptionPane.INFORMATION_MESSAGE);
                        setEmptyInputs();
                        // massage GUI--> succesfully creating a new User
                    } else {
                        showMessage("Error", "This Role is already exists on our System", JOptionPane.WARNING_MESSAGE);
                        // massage GUI--> trying to create not new User
                    }


                } else showMessage("", "", 1);
            }
        });

    }


    /*
     * After creating a new User, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {
        tf_createRole_rolename.setText("");
        ta_createRole_description.setText("");

    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createRolePanel,
                message,
                title,
                messageType);
    }
}




