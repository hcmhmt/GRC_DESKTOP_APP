package com.project.GUI_2.CREATE;

import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.GUI_2.USER.RiskManager_GUI;
import com.project.service.RoleRiskService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * This Page can opened only by Risk Owner.
 * Another User should not create a new Role, so this Page is reachable from Risk Owner Side.
 * */
public class createRisk_GUI extends JFrame {
    private JPanel createRiskPanel;
    private JTextField tf_createRisk_riskname;
    private JComboBox cb_createRisk_RiskLevel;
    private JTextArea ta_createRisk_description;
    private JTextField tf_createRisk_riskOwner;
    private JButton bt_createRisk_cancel;
    private JButton bt_createRisk_create;
    private JButton bt_createRisk_back;
    private JButton bt_createRisk_signout;
    private JTextField tf_createRisk_rolename;


    private RoleRiskService roleRiskService;


    /*
     * This function helps to creating a new Risk successfully, getting all Infos and setting to Risk Table
     *
     * action of button BACK -forwarding to Manager Page
     * action of button CANCEL -forwarding to Welcome Page
     * action of button SIGN OUT - forwarding to Welcome Page
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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bt_createRisk_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RiskManager_GUI();
            }
        });

        bt_createRisk_signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeGUI();
            }
        });


        bt_createRisk_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RiskManager_GUI();
            }
        });

            /*bt_createRisk_create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RiskEntity risk = new RiskEntity();


                    String riskName = tf_createRisk_riskname.getText().trim();
                    String roleName = tf_createRisk_rolename.getText().trim();
                    String description = ta_createRisk_description.getText();
                    String riskOwnerID = tf_createRisk_riskOwner.getText().trim();
                  //  LevelEnum risklevel = String.valueOf(cb_createRisk_RiskLevel.getSelectedItem());
                    LevelEnum risklevel= LevelEnum.valueOf(String.valueOf(cb_createRisk_RiskLevel.getSelectedItem()));



                    // checking of all Text fields and confirming
                    if (!riskName.isEmpty() || !description.isEmpty() || !riskOwnerID.isEmpty()){

                        // if there is no blank field--> setting all Info to Role table
                        risk.setRiskName(riskName);
                        risk.setRiskDescription(description);
                        risk.setRiskOwnerID(riskOwnerID);
                        risk.setRisklevel(risklevel);
                        //risk.setRolename(roleName);


                        risk = roleRiskService.save(risk);

                        if (risk != null) {
                            showMessage("Done", "You have created a new Risk", JOptionPane.INFORMATION_MESSAGE);
                            setEmptyInputs();
                            // massage GUI--> succesfully creating a new User
                        }else {
                            showMessage("Error", "This Risk is already exists on our System", JOptionPane.WARNING_MESSAGE);
                            // massage GUI--> trying to create not new User
                        }


                    } else showMessage("","",1);
                }
            });
            */
    }


    /*
     * After creating a new User, setting as blank for all Text Fields
     * */
    private void setEmptyInputs() {
        tf_createRisk_riskname.setText("");
        ta_createRisk_description.setText("");
        tf_createRisk_riskOwner.setText("");

    }

    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(createRiskPanel,
                message,
                title,
                messageType);
    }
}
