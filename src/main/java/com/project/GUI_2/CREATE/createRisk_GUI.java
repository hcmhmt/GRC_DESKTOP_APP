package com.project.GUI_2.CREATE;

import com.project.dto.*;
import com.project.service.RoleRiskService;
import com.project.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/*
 * This Page can opened only by Risk Owner.
 * Another User should not create a new Risk, so this Page is reachable from Risk Manager Side.
 * */
public class createRisk_GUI extends JFrame {

    private JPanel createRiskPanel;
    private JTextField tf_createRisk_riskname;
    private JTextArea ta_createRisk_description;
    private JButton bt_createRisk_cancel;
    private JButton bt_createRisk_create;
    private JButton bt_createRisk_back;
    private JComboBox cb_createRisk_risklevel;
    private JComboBox cb_createRisk_riskowner;


    private RoleRiskService roleRiskService;
    private UserService userService;


    /*
     * This function helps to creating a new Risk successfully, getting all Infos and setting to Risk Table
     *
     * action of button BACK -dispose
     * action of button CANCEL -dispose
     * action of button CREATE - creating a new Risk
     * */
    public createRisk_GUI() {
        add(createRiskPanel);
        setSize(700, 900);
        setTitle("You are creating a new Risk!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        roleRiskService = new RoleRiskService();
        userService = new UserService();

        List<UserEntity> userList = userService.getAllUsersByUserType(TypeEnum.RISK_MANAGER.name());

        userList.forEach(userEntity -> {
            cb_createRisk_riskowner.addItem(userEntity.getUsername());
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createRisk_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bt_createRisk_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bt_createRisk_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RiskEntity risk = new RiskEntity();

                String riskName = tf_createRisk_riskname.getText().trim();
                String description = ta_createRisk_description.getText();
                LevelEnum risklevel = LevelEnum.valueOf(String.valueOf(cb_createRisk_risklevel.getSelectedItem()).replace(' ', '_'));
                String riskManager = String.valueOf(cb_createRisk_riskowner.getSelectedItem());

                // checking of all Text fields and confirming
                if (!riskName.isEmpty() || !description.isEmpty()) {

                    // if there is no blank field--> setting all Info to Role table
                    risk.setRiskName(riskName);
                    risk.setRiskDescription(description);
                    risk.setRiskOwnerID(riskManager);
                    risk.setRisklevel(risklevel.name());

                    risk = roleRiskService.save(risk);

                    if (risk != null) {
                        showMessage("Done", "You have created a new Risk", JOptionPane.INFORMATION_MESSAGE);
                        setEmptyInputs();
                        // massage GUI--> succesfully creating a new Risk
                    } else {
                        showMessage("Error", "This Risk already exists on our System", JOptionPane.WARNING_MESSAGE);
                        // massage GUI--> trying to create not new Risk
                    }


                } else showMessage("", "", 1);
            }
        });

    }


    private void setEmptyInputs() {
        tf_createRisk_riskname.setText("");
        ta_createRisk_description.setText("");
        // tf_createRisk_riskOwner.setText("");

    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createRiskPanel,
                message,
                title,
                messageType);
    }
}
