package com.project.GUI_2.SEARCH;

import com.project.service.UserRequestService;


import javax.swing.*;

public class searchRequest_GUI extends JFrame{
    private JTextPane tp_manager_requestman;
    private JTextField tf_searchRequest_search;
    private JButton bt_searchRequest_search;
    private JButton deleteButbt_searchRequest_delete;
    private JButton bt_searchRequest_show;
    private JTable table_searchRequest_show;
    private JPanel searchRequestPanel;
    private UserRequestService requestService;

    public searchRequest_GUI() {
        add(searchRequestPanel);
        setSize(900, 700);
        setTitle("You are searching a Request!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        requestService = new UserRequestService();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
