package com.project.GUI_2.SEARCH;

import com.project.dto.RiskEntity;
import com.project.dto.RoleEntity;
import com.project.service.RoleRiskService;
import com.project.service.RoleService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchRisk_GUI extends JFrame{
    private JPanel searchRiskGUI;
    private JTextPane tp_roleowner_userman;
    private JTextField tf_searchRisk_riskID;
    private JButton bt_searchRisk_search;
    private JButton bt_searchRisk_delete;
    private JButton bt_searchRisk_show;
    private JScrollPane js_panel_table;
    private JTable search_risk_table;

    private RoleRiskService riskService;
    private RoleService roleService;

    public searchRisk_GUI() {
        add(searchRiskGUI);
        setSize(900, 700);
        setTitle("You are searching a Request!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        riskService = new RoleRiskService();
        roleService = new RoleService();
        fillEmpty();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_searchRisk_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RiskEntity> rList = riskService.getAll();
                if (rList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Risk!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(rList);
            }
        });

        bt_searchRisk_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RiskEntity> rList = riskService.getSearchedRisk(tf_searchRisk_riskID.getText().toString().trim());
                if (rList == null) {
                    fillEmpty();
                    showMessage("Warning", "There is no Risk!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    fillTable(rList);
            }
        });

        bt_searchRisk_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(search_risk_table.getSelectedRow() != -1){
                    Long id = Long.parseLong(search_risk_table.getValueAt(search_risk_table.getSelectedRow(), 0).toString());

                    if(riskService.delete(id, roleService.getOneRolebyRiskId(id).getRoleId())){
                        List<RiskEntity> rList = new ArrayList<>();

                        for (int i = 0 ; i < search_risk_table.getRowCount() ; i++){
                            RiskEntity currentRisk = new RiskEntity();
                            Long idOfRow = Long.parseLong(search_risk_table.getValueAt(i, 0).toString());
                            if(id.equals(idOfRow))
                                continue;
                            currentRisk.setRiskId(idOfRow);
                            currentRisk.setRiskName(search_risk_table.getValueAt(i, 1).toString());
                            currentRisk.setRiskDescription(search_risk_table.getValueAt(i, 2).toString());
                            currentRisk.setRiskOwnerID(search_risk_table.getValueAt(i, 3).toString());

                            rList.add(currentRisk);
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

    private void fillTable(List<RiskEntity> rList) {
        Object[][] rec = new Object[rList.size()][5];
        String[] header = {"Risk Id", "Risk name", "Risk Desc", "Risk Owner"};

        for (int i = 0; i < rList.size(); i++) {
            rec[i][0] = rList.get(i).getRiskId().toString();
            rec[i][1] = rList.get(i).getRiskName();
            rec[i][2] = rList.get(i).getRiskDescription();
            rec[i][3] = rList.get(i).getRiskOwnerID();
        }

        search_risk_table = new JTable(rec, header);
        search_risk_table.setBounds(10, 10, 500, 80);
        js_panel_table.getViewport().add(search_risk_table);
    }

    private void fillEmpty() {
        String[][] rec = {};
        String[] header = {"Risk Id", "Risk name", "Risk Desc", "Risk Owner"};
        search_risk_table = new JTable(rec, header);
        search_risk_table.setBounds(10, 10, 500, 80);
        js_panel_table.getViewport().add(search_risk_table);
    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(searchRiskGUI,
                message,
                title,
                messageType);
    }
    
}
