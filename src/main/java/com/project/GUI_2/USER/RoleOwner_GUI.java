package com.project.GUI_2.USER;

import javax.swing.*;
import java.awt.*;

public class RoleOwner_GUI extends JFrame{

    private JButton roleSearchButton;
    private JButton userSearchButton;
    private JButton riskSearchButton;
    private JButton mySettingsButton;
    private JButton createRoleButton;
    private JButton lauchRiskanalyseButton;
    private JPanel jp_roleOwner;

    public RoleOwner_GUI() throws HeadlessException {
        add(jp_roleOwner);
        setSize(300,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Baslık");
    }
}
