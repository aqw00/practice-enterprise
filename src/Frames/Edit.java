package Frames;

import Reading.ReadTxtFile;
import Reading.ReadCsvFiles;
import Reading.WriteCsvFiles;
import static Reading.DelLineCsvFile.deleteLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class Edit extends JFrame {
    public static void editFrame(ActionEvent e, List<String> items, List<String> list, JFrame frame, String pathWebsiteInfo, String pathButtonWebsites, String pathChrome)
    {
        list.remove("ADD");
        list.remove("CLOSE");
        list.remove("EDIT");
        String[] array = items.toArray(new String[items.size()]);

        String email = "", password = "", url = "";

        JComponent comp1 = (JComponent) e.getSource();
        Window win1 = SwingUtilities.getWindowAncestor(comp1);
        win1.dispose();

        // System.out.println("Button " + item + " clicked.");
        // Create JFrame and set size for AddWebsite
        JFrame editFrame = new JFrame("Edit Website");
        editFrame.setSize(300, 200);
        editFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        editFrame.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                //close website window
                editFrame.dispose();
                //open app window
                Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
            }
        });

        // Create Jpanel with GridLayout
        JPanel editPanel = new JPanel(new GridLayout(7, 2));

        // make dropdown menu
        JLabel lblcbx = new JLabel("Choose website:");
        editPanel.add(lblcbx);
        JComboBox cbxSites = new JComboBox(array);
        cbxSites.setSelectedIndex(8);
        editPanel.add(cbxSites);

        for (int i = 0; i < (ReadCsvFiles.readFile(pathWebsiteInfo).size()); i++) {
            if (ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[0].equals(cbxSites.getSelectedItem())) {
                url = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[1];
                email = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[2];
                password = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[3];
            }
        }

        // make website part
        JLabel lblSite = new JLabel("Website:");
        editPanel.add(lblSite);
        JLabel lblSiteAuto = new JLabel();
        editPanel.add(lblSiteAuto);

        // make user part
        JLabel lblUser = new JLabel("Login:");
        editPanel.add(lblUser);
        JTextField txtUser = new JTextField();
        editPanel.add(txtUser);

        // make password part
        JLabel lblPassword = new JLabel("Password:");
        editPanel.add(lblPassword);
        JPasswordField txtPassword = new JPasswordField();
        editPanel.add(txtPassword);
        // make show button for password
        JLabel lblShow = new JLabel("Show Password");
        editPanel.add(lblShow);
        JCheckBox chckPassword = new JCheckBox();
        editPanel.add(chckPassword);
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
        editPanel.add(lblEmpty);
        editPanel.add(lblWarning);

        lblWarning.setVisible(false);
        lblWarning.setVisible(false);

        // add cancel button
        JButton btnCancel = new JButton("Cancel");
        editPanel.add(btnCancel);
        // cancel button action
        btnCancel.addActionListener((ActionEvent g) -> {
            //close website window
            JComponent comp = (JComponent) g.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();

            //open app window
            Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
        });

        // add save button
        JButton btnSave = new JButton("Save");
        editPanel.add(btnSave);
        // confirm button action
        String finalUrl = url;
        btnSave.addActionListener((ActionEvent h) -> {
            String Ssite = cbxSites.getSelectedItem().toString();
            String Surl = finalUrl;
            String Suser = txtUser.getText();
            String Spassword = txtPassword.getText();
            list.stream().toList();

            if (Suser.equals("") || Spassword.equals(""))
            {
                lblWarning.setVisible(true);
                lblEmpty.setVisible(true);
            }
            else
            {
                lblWarning.setVisible(false);
                lblWarning.setVisible(false);
                // data opslaan in CSV
                list.add(Ssite);
                list.add(Surl);
                list.add(Suser);
                list.add(Spassword);
                //verwijder originele lijn uit websiteInfo.csv en buttonWebsites.txt
                deleteLine(pathWebsiteInfo, pathButtonWebsites, Ssite);
                //add veranderde lijn opnieuw
                WriteCsvFiles.writeCsv(pathWebsiteInfo, list);
                // nieuwe button adden op originele frame
                ReadTxtFile.txtFileHandeling(pathButtonWebsites, false, Ssite);
                //close website window
                JComponent comp = (JComponent) h.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

                //open app window
                Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
            }
        });

        cbxSites.addActionListener((ActionEvent h) -> {

            String url2 = "", email2 = "", password2 = "";
            for (int i = 0; i < (ReadCsvFiles.readFile(pathWebsiteInfo).size()); i++) {
                if (ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[0].equals(cbxSites.getSelectedItem())) {
                    url2 = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[1];
                    email2 = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[2];
                    password2 = ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[3];
                }
            }

            lblSiteAuto.setText(cbxSites.getSelectedItem().toString());

            txtUser.setText(email2);
            txtPassword.setText(password2);
        });

        // add panel to frame and make visible
        editFrame.add(editPanel);
        editFrame.setVisible(true);
    }
}
