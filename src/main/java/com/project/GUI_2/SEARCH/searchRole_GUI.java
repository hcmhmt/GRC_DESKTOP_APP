package com.project.GUI_2.SEARCH;

import com.project.dto.RoleEntity;
import com.project.service.RoleService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchRole_GUI extends JFrame {
    private JPanel searchRolePanel;
    private JTextPane tp_roleowner_roleman;
    private JTextField tf_searchRole_rolename;
    private JButton bt_searchRole_delete;
    private JButton bt_searchRole_search;
    private JButton bt_searchRole_showallRoles;
    private JTable search_role_table;
    private JScrollPane js_panel_table;
    private RoleService roleService;


    public searchRole_GUI() {
        add(searchRolePanel);
        setSize(900, 700);
        setTitle("You are searching User!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        roleService = new RoleService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[][] rec = {};
        String[] header = {"Role Id", "Role name", "Role Desc", "Role Owner", "Role System"};
        search_role_table = new JTable(rec, header);
        search_role_table.setBounds(10, 10, 500, 80);
        js_panel_table.getViewport().add(search_role_table);

        bt_searchRole_showallRoles.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<RoleEntity> rList = roleService.getAll();
                if (rList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Role!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(rList);
            }
        });

        bt_searchRole_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<RoleEntity> rList = roleService.getOneRole(tf_searchRole_rolename.getText().toString().trim());
                if (rList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Role!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(rList);

            }
        });

        bt_searchRole_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(search_role_table.getSelectedRow() != -1){
                    Long id = Long.parseLong(search_role_table.getValueAt(search_role_table.getSelectedRow(), 0).toString());
                    if(roleService.delete(id)){
                        List<RoleEntity> rList = new ArrayList<>();

                        for (int i = 0 ; i < search_role_table.getRowCount() ; i++){
                            RoleEntity currentRole = new RoleEntity();
                            Long idOfRow = Long.parseLong(search_role_table.getValueAt(i, 0).toString());
                            if(id.equals(idOfRow))
                                continue;
                            currentRole.setRoleId(idOfRow);
                            currentRole.setRoleName(search_role_table.getValueAt(i, 1).toString());
                            currentRole.setDescription(search_role_table.getValueAt(i, 2).toString());
                            currentRole.setRoleOwnerID(search_role_table.getValueAt(i, 3).toString());
                            currentRole.setSystem(search_role_table.getValueAt(i, 4).toString());

                            rList.add(currentRole);
                        }

                        fillTable(rList);
                        showMessage("Info", "Role deleted successfully", JOptionPane.INFORMATION_MESSAGE);

                    }else{
                        showMessage("Warning", "An error occured!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    showMessage("Warning", "Please select a role!", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

    }

    private void fillTable(List<RoleEntity> rList) {
        Object[][] rec = new Object[rList.size()][5];
        String[] header = {"Role Id", "Role name", "Role Desc", "Role Owner", "Role System"};

        for (int i = 0; i < rList.size(); i++) {
            rec[i][0] = rList.get(i).getRoleId().toString();
            rec[i][1] = rList.get(i).getRoleName();
            rec[i][2] = rList.get(i).getDescription();
            rec[i][3] = rList.get(i).getRoleOwnerID();
            rec[i][4] = rList.get(i).getSystem();
        }

        search_role_table = new JTable(rec, header);
        search_role_table.setBounds(10, 10, 500, 80);
        js_panel_table.getViewport().add(search_role_table);
    }

    private void fillEmpty() {
        String[][] rec = {};
        String[] header = {"Role Id", "Role name", "Role Desc", "Role Owner", "Role System"};
        search_role_table = new JTable(rec, header);
        search_role_table.setBounds(10, 10, 500, 80);
        js_panel_table.getViewport().add(search_role_table);
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(searchRolePanel,
                message,
                title,
                messageType);
    }
}

