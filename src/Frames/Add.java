package Frames;

import Reading.ReadTxtFile;
import Reading.WriteCsvFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Add extends JFrame {
    public static void addFrame(ActionEvent e, List<String> csvEditList, JFrame frame, String pathWebsiteInfo, String pathButtonWebsites, String pathChrome)
    {
        JComponent comp1 = (JComponent) e.getSource();
        Window win1 = SwingUtilities.getWindowAncestor(comp1);
        win1.dispose();

        // System.out.println("Button " + item + " clicked.");
        // Create JFrame and set size for AddWebsite
        JFrame form = new JFrame("Add Website");
        form.setSize(300, 200);
        form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        form.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                //close website window
                form.dispose();
                //open app window
                Website.webFrame(pathButtonWebsites, pathWebsiteInfo, csvEditList, pathChrome);
            }
        });


        // Create Jpanel with GridLayout
        JPanel formPanel = new JPanel(new GridLayout(7, 2));

        // make website part
        JLabel lblSite = new JLabel("Website:");
        formPanel.add(lblSite);
        JTextField txtSite = new JTextField();
        formPanel.add(txtSite);

        // make url part
        JLabel lblUrl = new JLabel("URL:");
        formPanel.add(lblUrl);
        JTextField txtUrl = new JTextField();
        formPanel.add(txtUrl);

        // make user part
        JLabel lblUser = new JLabel("Login:");
        formPanel.add(lblUser);
        JTextField txtUser = new JTextField();
        formPanel.add(txtUser);

        // make password part
        JLabel lblPassword = new JLabel("Password:");
        formPanel.add(lblPassword);
        JPasswordField txtPassword = new JPasswordField();
        formPanel.add(txtPassword);
        // make show button for password
        JLabel lblShow = new JLabel("Show Password");
        formPanel.add(lblShow);
        JCheckBox chckPassword = new JCheckBox();
        formPanel.add(chckPassword);
        // event for showbutton password
        chckPassword.addActionListener((ActionEvent f) -> {
            if (chckPassword.isSelected())
            {
                txtPassword.setEchoChar((char)0);
            }
            else
            {
                txtPassword.setEchoChar('\u2022');
            }
        });

        // add warning
        JLabel lblWarning = new JLabel("Fill in all fields!");
        JLabel lblEmpty = new JLabel();
        formPanel.add(lblEmpty);
        formPanel.add(lblWarning);

        lblWarning.setVisible(false);
        lblWarning.setVisible(false);

        // add cancel button
        JButton btnCancel = new JButton("Cancel");
        formPanel.add(btnCancel);
        // cancel button action
        btnCancel.addActionListener((ActionEvent g) -> {
            //close website window
            JComponent comp = (JComponent) g.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();

            //open app window
            Website.webFrame(pathButtonWebsites, pathWebsiteInfo, csvEditList, pathChrome);
        });

        // add confirm button
        JButton btnConfirm = new JButton("Confirm");
        formPanel.add(btnConfirm);
        // confirm button action
        btnConfirm.addActionListener((ActionEvent h) -> {
            String site = txtSite.getText();
            String url = txtUrl.getText();
            String user = txtUser.getText();
            String password = txtPassword.getText();

            if (site.equals("") || url.equals("") || user.equals("") || password.equals(""))
            {
                lblWarning.setVisible(true);
                lblEmpty.setVisible(true);
            }
            else
            {
                lblWarning.setVisible(false);
                lblWarning.setVisible(false);
                // data opslaan in CSV
                csvEditList.add(site);
                csvEditList.add(url);
                csvEditList.add(user);
                csvEditList.add(password);
                WriteCsvFiles.writeCsv(pathWebsiteInfo, csvEditList, true);
                // nieuwe button adden op originele frame
                ReadTxtFile.txtFileHandeling(pathButtonWebsites, false, txtSite.getText());
                //close website window
                JComponent comp = (JComponent) h.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

                //open app window
                Website.webFrame(pathButtonWebsites, pathWebsiteInfo, csvEditList, pathChrome);
            }
        });

        // add panel to frame and make visible
        form.add(formPanel);
        form.setVisible(true);
    }
}
