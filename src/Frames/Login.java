package Frames;

import Connection.SFTPConnect;
import Encryption.DecryptAESKey;
import Encryption.DecryptCsvFile;
import Encryption.VerifySignature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Login extends JFrame
{
    public static void loginFrame(String pathButtonWebsites, String pathWebsiteInfo ,List<String> csvEditList, String pathChrome)
    {
        // Home path
        String basePath = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\";

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

            try {
                String encPass = DecryptCsvFile.encryptPass(password);

                if (user.equals("") || password.equals(""))
                {
                    lblEmpty.setVisible(true);
                    lblWrong.setVisible(false);
                }
                else if (encPass.equals("s2uxhWyKCXUXUs5UFA4bBQ==") && user.equals("sftpuser"))
                {
                    //close this window
                    JComponent comp = (JComponent) h.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();

                    /* SFTP */
                    String host = "192.168.1.69";
                    int port = 22;

                    String[] remoteFilePaths = {"/shared/data.csv.enc", "/shared/aes_key.enc.sig", "/shared/aes_key.enc", "/shared/aes_iv.txt", "/shared/public.pem", "/shared/buttonWebsites.txt"};
                    String[] localFilePaths = {"C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\data.csv.enc", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc.sig", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_iv.txt", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\public.pem", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt"};

                    // TODO: if connection fails do not open the website frame instead open de login frame with error msg if possible ??
                    SFTPConnect.connectSFTP(host, port, user, password, remoteFilePaths, localFilePaths);

                    /*Encryption*/
                    VerifySignature.verifySig(basePath + "Encryption-Test\\public.pem", basePath + "sharedFolder\\aes_key.enc.sig", basePath +"sharedFolder\\aes_key.enc");
                    byte[] decAes_key = DecryptAESKey.decryptAesKey(basePath +"Encryption-Test\\private.pem", basePath + "sharedFolder\\aes_key.enc");
                    DecryptCsvFile.decryptCsv(basePath + "sharedFolder\\aes_iv.txt", basePath +"sharedFolder\\data.csv.enc", decAes_key, basePath + "src\\websiteInfo.csv");

                    // Location Csv file
                    //String csvFile = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";
                    //String csvFile = "/home/tone/IdeaProjects/practice-enterprise/src/websiteInfo.csv";
                    String csvFile = basePath + "src\\websiteInfo.csv";

                    //open website window
                    Website.webFrame(pathButtonWebsites, pathWebsiteInfo, csvEditList, pathChrome, csvFile, password);
                }
                else
                {
                    lblEmpty.setVisible(false);
                    lblWrong.setVisible(true);
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        });

        // add panel to frame and make visible
        loginframe.add(loginPanel);
        loginframe.setVisible(true);
    }
}