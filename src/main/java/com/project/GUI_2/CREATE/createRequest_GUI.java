package com.project.GUI_2.CREATE;

import com.project.config.SessionUser;
import com.project.dto.RequestEntity;
import com.project.dto.RoleEntity;
import com.project.dto.StatusEnum;
import com.project.dto.UserEntity;
import com.project.service.UserRequestService;
import com.project.service.RoleService;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class createRequest_GUI extends JFrame {

    private JPanel createRequestpanel;
    private JComboBox cb_createRequest_System;
    private JComboBox cb_createRequest_rolename;
    private JComboBox cb_createRequest_username;
    private JTextArea ta_createRequest_description;
    private JTextField tf_createRequest_username;
    private JTextField tf_createRequest_roleName;
    private JButton bt_createRequest_cancel;
    private JButton bt_createRequest_create;
    private JButton bt_createRequest_back;
    private JButton bt_createRequest_signout;
    private JLabel jl_createRequest_session;


    private UserRequestService userRequestService;
    private RoleService roleService;
    private final UserService userService;


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
        roleService = new RoleService();
        userService = new UserService();

        Map<Long, String> roleMap = new HashMap<>();
        roleMap = fillCombobox(cb_createRequest_System.getSelectedItem().toString().trim());

        jl_createRequest_session.setText("Welcome " + SessionUser.getUsername());

        Map<Long, String> userMap = new HashMap<>();
        userMap = fillUsernameCombobox();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createRequest_back.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bt_createRequest_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bt_createRequest_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestEntity request = new RequestEntity();


                String username = cb_createRequest_username.getSelectedItem().toString().trim();
                String description = ta_createRequest_description.getText().trim();
                String system = String.valueOf(cb_createRequest_System.getSelectedItem());
                String roleName = cb_createRequest_rolename.getSelectedItem().toString().trim();


                // checking of all Text fields and confirming
                if (!description.isEmpty() || !roleName.isEmpty() || !username.isEmpty() || !system.isEmpty()) {

                    // if there is no blank field--> setting all Info to Request table

                    request.setRequestCreateDate(Calendar.getInstance().getTime());
                    request.setRequestDescription(description);
                    request.setRequestSystem(system);
                    request.setRequestStatus(StatusEnum.INACTIVE.name());
                    request.setRequestRole(roleName);
                    request.setRequestUserId(username);

                    request = userRequestService.save(request);

                    if (request != null) {
                        showMessage("Done", "You have created a new Request", JOptionPane.INFORMATION_MESSAGE);
                        setEmptyInputs();
                        // massage GUI--> successfully creating a new Request
                    } else {
                        showMessage("Error", "You can not request for this User", JOptionPane.WARNING_MESSAGE);
                        // massage GUI--> trying to create not new Request
                    }


                } else showMessage("", "", 1);
            }
        });

        cb_createRequest_System.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCombobox(cb_createRequest_System.getSelectedItem().toString().trim());
            }
        });

    }

    private Map<Long, String> fillCombobox(String system) {
        cb_createRequest_rolename.removeAllItems();
        List<RoleEntity> roles = roleService.getAllBySystem(system);
        Map<Long, String> map = new HashMap<>();

        roles.forEach(roleEntity -> {
            cb_createRequest_rolename.addItem(roleEntity.getRoleName());
            map.put(roleEntity.getRoleId(), roleEntity.getRoleName());
        });

        return map;
    }

    private Map<Long, String> fillUsernameCombobox() {
        List<UserEntity> users = userService.getAllUsersByManager(SessionUser.getUid().toString());
        Map<Long, String> map = new HashMap<>();

        users.forEach(userEntity -> {
            cb_createRequest_username.addItem(userEntity.getUsername());
            map.put(userEntity.getUserId(), userEntity.getUsername());
        });

        return map;
    }

    /*
     * After creating a new User, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {

        ta_createRequest_description.setText("");
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createRequestpanel,
                message,
                title,
                messageType);
    }

}







