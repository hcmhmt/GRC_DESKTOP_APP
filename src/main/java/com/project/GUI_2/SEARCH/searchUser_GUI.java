package com.project.GUI_2.SEARCH;

import com.project.dto.UserEntity;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchUser_GUI extends JFrame {

    private JPanel searchUser_GUI;
    private JTextPane tp_manager_userman;
    private JTextField tf_searchUser_username;
    private JButton bt_searchUser_search;
    private JButton bt_searchUser_delete;
    private JButton bt_searchUser_showAllUsers;
    private JScrollPane js_userSearch_table;
    private JTable search_user_table;

    private UserService userService;


    public searchUser_GUI() {
        setSize(900, 700);
        setTitle("You are searching User!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        userService = new UserService();
        add(searchUser_GUI);
        fillEmpty();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_searchUser_showAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserEntity> uList = userService.getAll();
                if (uList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Role!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(uList);
            }
        });

        bt_searchUser_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserEntity> uList = userService.searchUsers(tf_searchUser_username.getText().toString().trim());
                if (uList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Role!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(uList);
            }
        });

        bt_searchUser_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (search_user_table.getSelectedRow() != -1) {
                    Long id = Long.parseLong(search_user_table.getValueAt(search_user_table.getSelectedRow(), 0).toString());
                    if (userService.delete(id)) {
                        List<UserEntity> uList = new ArrayList<>();

                        for (int i = 0; i < search_user_table.getRowCount(); i++) {
                            UserEntity currentUser = new UserEntity();
                            Long idOfRow = Long.parseLong(search_user_table.getValueAt(i, 0).toString());
                            if (id.equals(idOfRow))
                                continue;
                            currentUser.setUserId(idOfRow);
                            currentUser.setUsername(search_user_table.getValueAt(i, 1).toString());
                            currentUser.setUserSurname(search_user_table.getValueAt(i, 2).toString());
                            currentUser.setUserEmail(search_user_table.getValueAt(i, 3).toString());
                            currentUser.setUserGroup(search_user_table.getValueAt(i, 4).toString());

                            uList.add(currentUser);
                        }

                        fillTable(uList);
                        showMessage("Info", "Role deleted successfully", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        showMessage("Warning", "An error occured!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    showMessage("Warning", "Please select a role!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

    private void fillTable(List<UserEntity> uList) {
        Object[][] rec = new Object[uList.size()][5];
        String[] header = {"User Id", "User name", "User Desc", "User Owner", "User System"};

        for (int i = 0; i < uList.size(); i++) {
            rec[i][0] = uList.get(i).getUserId().toString();
            rec[i][1] = uList.get(i).getUsername();
            rec[i][2] = uList.get(i).getUserSurname();
            rec[i][3] = uList.get(i).getUserEmail();
            rec[i][4] = uList.get(i).getUserGroup();
        }

        search_user_table = new JTable(rec, header);
        search_user_table.setBounds(10, 10, 500, 80);
        js_userSearch_table.getViewport().add(search_user_table);
    }

    private void fillEmpty() {
        String[][] rec = {};
        String[] header = {"User Id", "User name", "User Desc", "User Owner", "User System"};

        search_user_table = new JTable(rec, header);
        search_user_table.setBounds(10, 10, 500, 80);
        js_userSearch_table.getViewport().add(search_user_table);
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(searchUser_GUI,
                message,
                title,
                messageType);
    }

}
