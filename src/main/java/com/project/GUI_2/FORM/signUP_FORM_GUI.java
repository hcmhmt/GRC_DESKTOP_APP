package com.project.GUI_2.FORM;

import com.project.dto.*;

import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*
 * This Page can opened by all Users.
 * There is no restriction
 * */

public class signUP_FORM_GUI extends JFrame{

    //define  variables

    private JComboBox cb_signUp_usergroup;
    private JTextField tf_signUp_username;
    private JTextField tf_signUp_surname;
    private JTextField tf_signUp_name;
    private JTextField tf_signUp_email;
    private JPasswordField tf_signUp_password;
    private JPasswordField tf_signUp_passwordconfirm;
    private JButton b_signUp_submit;
    private JButton b_signUp_cancel;
    private JPanel signUpPanel;
    private JComboBox cb_signUp_userType;
    private JTextField tf_signUp_manager;
    private JLabel lb_signUp_manager;
    private JButton bt_SignUp_signOut;

    private UserService userService;

    /*
     * This function helps to creating a new User successfully, getting all Infos and setting to User Table
     *
     * action of button BACK -forwarding to Manager Page
     * action of button CANCEL -forwarding to Welcome Page
     * action of button SIGN OUT - forwarding to Welcome Page
     * action of button SUBMIT - creating a new User
     * */

    public signUP_FORM_GUI() {
        add(signUpPanel);
        setSize(900, 700);
        setTitle("Please Sign Up!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userService = new UserService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        b_signUp_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeGUI();
            }
        });

        b_signUp_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserEntity user = new UserEntity();

                // getting all Info of User
                String username = tf_signUp_username.getText().trim();
                String surname = tf_signUp_surname.getText().trim();
                String firstName = tf_signUp_name.getText().trim();
                String email = tf_signUp_email.getText().trim();
                String password = String.valueOf(tf_signUp_password.getPassword());
                String passwordConfirm = String.valueOf(tf_signUp_passwordconfirm.getPassword());
                String group = String.valueOf(cb_signUp_usergroup.getSelectedItem());
                TypeEnum userType= TypeEnum.valueOf(String.valueOf(cb_signUp_userType.getSelectedItem()).replace(' ','_'));

                String managerID = tf_signUp_manager.getText().trim();

                // checking of all Text fields and confirming
                if (!username.isEmpty() || !firstName.isEmpty() || !surname.isEmpty() || !password.isEmpty() || !passwordConfirm.isEmpty() || !email.isEmpty() ){
                    if(password.equals(passwordConfirm)){

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
                            showMessage("Done", "The User was created", JOptionPane.INFORMATION_MESSAGE);
                            setEmptyInputs();
                        }else {
                            showMessage("Error", "This User is already exists on our System", JOptionPane.WARNING_MESSAGE);
                        }

                    }else showMessage("","",1);
                } else showMessage("","",1);
            }
        });

    }



    /*
     * After submitted, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {
        tf_signUp_email.setText("");
        tf_signUp_name.setText("");
        tf_signUp_password.setText("");
        tf_signUp_surname.setText("");
        tf_signUp_passwordconfirm.setText("");
        tf_signUp_username.setText("");
        tf_signUp_manager.setText("");
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(signUpPanel,
                message,
                title,
                messageType);
    }
}
