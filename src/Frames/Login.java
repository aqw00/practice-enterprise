package Frames;

import Reading.ReadTxtFile;
import Reading.WriteCsvFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Login extends JFrame
{
    public static void loginFrame(String pathButtonWebsites, String pathWebsiteInfo ,List<String> csvEditList, String pathChrome)
    {
        // Create JFrame and set size for AddWebsite
        JFrame loginframe = new JFrame("LocalPass Login");
        loginframe.setSize(350, 180);
        loginframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create Jpanel with GridLayout
        JPanel loginPanel = new JPanel(new GridLayout(5, 2));

        // make user part
        JLabel lblUser = new JLabel("Login:");
        loginPanel.add(lblUser);
        JTextField txtUser = new JTextField();
        loginPanel.add(txtUser);

        // make password part
        JLabel lblPassword = new JLabel("Password:");
        loginPanel.add(lblPassword);
        JPasswordField txtPassword = new JPasswordField();
        loginPanel.add(txtPassword);
        // make show button for password
        JLabel lblShow = new JLabel("Show Password");
        loginPanel.add(lblShow);
        JCheckBox chckPassword = new JCheckBox();
        loginPanel.add(chckPassword);
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
        JLabel lblWrong = new JLabel("User or password are wrong!");
        JLabel lblEmpty = new JLabel("Please fill in all fields!");
        loginPanel.add(lblEmpty);
        loginPanel.add(lblWrong);

        lblWrong.setVisible(false);
        lblEmpty.setVisible(false);

        // add login button
        JButton btnLogin = new JButton("Login");
        loginPanel.add(btnLogin);
        // confirm button action
        btnLogin.addActionListener((ActionEvent h) -> {
            String user = txtUser.getText();
            String password = txtPassword.getText();

            if (user.equals("") || password.equals(""))
            {
                lblEmpty.setVisible(true);
                lblWrong.setVisible(false);
            }
            else if (1==2 /*als user of password fout is*/)
            {
                lblEmpty.setVisible(false);
                lblWrong.setVisible(true);
            }
            else if (1==1 /*als user en password juist zijn*/)
            {
                //close this window
                JComponent comp = (JComponent) h.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

                //open website window
                Website.webFrame(pathButtonWebsites, pathWebsiteInfo, csvEditList, pathChrome);
            }
        });

        // add panel to frame and make visible
        loginframe.add(loginPanel);
        loginframe.setVisible(true);
    }
}