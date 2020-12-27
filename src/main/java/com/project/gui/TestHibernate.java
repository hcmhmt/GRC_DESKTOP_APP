package com.project.gui;

import com.project.GUI_2.FORM.WelcomeGUI;
import com.project.config.HibernateUtil;
import org.hibernate.Session;


import javax.swing.*;

public class TestHibernate {

    private static Session session;

    public static void main(String[] args) {
        startSession();

        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new WelcomeGUI();

            }
        });

    }

    private static void startSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }

    public static Session getSession() {
        return session;
    }


}