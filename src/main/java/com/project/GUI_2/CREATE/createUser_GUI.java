package com.project.GUI_2.CREATE;

import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.USER.Manager_GUI;
import com.project.dto.*;
import com.project.service.UserService;
import com.project.service.UserRoleService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This Page can opened only by Manager.
 * Another User should not create a new User, so this Page is reachable from Manager Side.
 * */

public class createUser_GUI extends JFrame {

    //define  variables

    private JComboBox cb_createUser_usergroup;
    private JTextField tf_createUser_username;
    private JTextField tf_createUser_surname;
    private JTextField tf_createUser_firstname;
    private JTextField tf_createUser_email;
    private JPasswordField pf_createUser_password;
    private JPasswordField pf_createUser_passwordConfirm;
    private JButton bt_createUser_create;
    private JButton bt_createUser_cancel;
    private JTextField tf_createUser_managerID;
    private JButton bt_createUser_back;
    private JButton bt_createUser_signout;
    private JPanel createUserPanel;
    private JComboBox cb_createUser_usertyp;

    private UserService userService;

    /*
     * This function helps to creating a new User successfully, getting all Infos and setting to User Table
     *
     * action of button BACK -forwarding to Manager Page
     * action of button CANCEL -forwarding to Welcome Page
     * action of button SIGN OUT - forwarding to Welcome Page
     * action of button CREATE - creating a new User
     * */
    public createUser_GUI() {
        add(createUserPanel);
        setSize(600, 800);
        setTitle("You are creating a new User!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userService = new UserService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createUser_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager_GUI();
            }
        });

        bt_createUser_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeGUI();
            }
        });


        bt_createUser_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager_GUI();
            }
        });

        bt_createUser_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserEntity user = new UserEntity();
                RoleEntity role = new RoleEntity();

                role.setRoleId(1L);
                role.setRoleName("Public");

                // getting all Info of User
                String username = tf_createUser_username.getText().trim();
                String firstName = tf_createUser_firstname.getText().trim();
                String surname = tf_createUser_surname.getText().trim();
                String password = String.valueOf(pf_createUser_password.getPassword());
                String passwordConfirm = String.valueOf(pf_createUser_passwordConfirm.getPassword());
                String email = tf_createUser_email.getText().trim();
                String group = String.valueOf(cb_createUser_usergroup.getSelectedItem());
                TypeEnum userType = TypeEnum.valueOf(String.valueOf(cb_createUser_usertyp.getSelectedItem()));


                String managerID = tf_createUser_managerID.getText().trim();


                // checking of all Text fields and confirming
                if (!username.isEmpty() || !firstName.isEmpty() || !surname.isEmpty() || !password.isEmpty() || !passwordConfirm.isEmpty() || !email.isEmpty()) {
                    if (password.equals(passwordConfirm)) {

                        // if there is no blank field--> setting all Info to User table
                        user.setUsername(username);
                        user.setUserFirstName(firstName);
                        user.setUserSurname(surname);
                        user.setUserPassword(password);
                        user.setUserEmail(email);
                        user.setUserIsActive(StatusEnum.ACTIVE.name());
                        user.setUserGroup(group);
                        user.setUserType(userType.name());
                        user.setManagerID(managerID);

                        user = userService.save(user);
                        if (user != null) {
                            showMessage("Done", "You have created a new User", JOptionPane.INFORMATION_MESSAGE);
                            setEmptyInputs();
                            // massage GUI--> succesfully creating a new User
                        } else {
                            showMessage("Error", "This User is already exists on our System", JOptionPane.WARNING_MESSAGE);
                            // massage GUI--> trying to create not new User
                        }

                    } else showMessage("", "", 1);
                } else showMessage("", "", 1);
            }
        });

    }


    /*
     * After creating a new User, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {
        tf_createUser_email.setText("");
        tf_createUser_username.setText("");
        pf_createUser_password.setText("");
        tf_createUser_surname.setText("");
        pf_createUser_passwordConfirm.setText("");
        tf_createUser_username.setText("");
        tf_createUser_managerID.setText("");
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createUserPanel,
                message,
                title,
                messageType);
    }
}

