import Reading.readTxtFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        /*
        *   String fileName = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\test.csv";
        *   String fileName = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\test.csv";
        *   String fileName = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";
        * */

        /* SFTP */
        String host = "192.168.1.69";
        int port = 22;
        String username = "sftpuser";
        String SFTP_password = "W@chtwoord"; // TODO: find a way to hide this
        String remoteFilePath = "/shared/websiteInfo.csv";
        //String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/websiteInfo.csv";
        String localFilePath = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";
        String url = "";


        /* TONE */
        // String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        //String pathChrome = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\instalation\\chromedriver.exe";
        /* ROBIN */
         String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\intellij\\instalation\\chromedriver.exe";
         String pathWebsites = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\src\\buttonWebsites.txt";

        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);

        // Array of items
        String[] items = {"ADD", "Facebook", "Google", "Amazon", "Github", "Instagram", "Netflix", "Thomas_More", "Outlook"};

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 2));

        for (String item : items) {
            JButton button = new JButton(item); // Create a new JButton with the text of the current item
            button.addActionListener((ActionEvent e) -> {
                // Handle the button click event here
                if(item.equals("ADD"))
                {
                    frame.setVisible(false);
                    System.out.println("Button " + item + " clicked.");
                    // Create JFrame and set size for AddWebsite
                    JFrame form = new JFrame("Add Website");
                    form.setSize(300, 200);
                    // Create Jpanel with GridLayout
                    JPanel formPanel = new JPanel(new GridLayout(6, 2));

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
                        frame.setVisible(true);
                    });

                    // add confirm button
                    JButton btnConfirm = new JButton("Confirm");
                    formPanel.add(btnConfirm);
                    // confirm button action
                    btnConfirm.addActionListener((ActionEvent h) -> {
                        // data opslaan in CSV

                        // nieuwe button adden op originele frame
                        readTxtFile.txtFileHandeling(pathWebsites, false, txtSite.getText());
                        //close website window
                        JComponent comp = (JComponent) h.getSource();
                        Window win = SwingUtilities.getWindowAncestor(comp);
                        win.dispose();

                        //open app window
                        frame.setVisible(true);
                    });

                    // add panel to frame and make visible
                    form.add(formPanel);
                    form.setVisible(true);
                }
                else
                {
                    WebsitePageImporter.importWebsitePage(pathChrome, item);
                }

                // System.out.println("Button " + item + " clicked.");

            });
            panel.add(button); // Add the button to the panel
        }

        // Add the JPanel to the JFrame and make it visible
        frame.add(panel);
        frame.setVisible(true);

    }
}
