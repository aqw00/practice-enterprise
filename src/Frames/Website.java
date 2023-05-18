package Frames;

import Connection.SFTPConnect;
import Encryption.DecryptAESKey;
import Encryption.DecryptCsvFile;
import Encryption.VerifySignature;
import Reading.ReadTxtFile;
import WebsiteImporter.WebsitePageImporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static Frames.Add.addFrame;
import static Frames.Edit.editFrame;

public class Website extends JFrame
{
    public static void webFrame(String pathButtonWebsites, String pathWebsiteInfo ,List<String> list, String pathChrome, String csvFile, String sftpEncPass)
    {
        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Array of items
        List<String> items = ReadTxtFile.txtFileHandeling(pathButtonWebsites, true, "");

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 2));

        for (String item : items) {
            JButton button = new JButton(item); // Create a new JButton with the text of the current item

            // set color of button
            if(item.equals("ADD"))
            {
                button.setBackground(Color.GREEN);
            } else if (item.equals("CLOSE"))
            {
                button.setBackground(Color.RED);
            } else if (item.equals("EDIT")) {
                button.setBackground(Color.YELLOW);
            }

            button.addActionListener((ActionEvent e) -> {
                // Handle the button click event here
                if(item.equals("CLOSE"))
                {
                    // encrypt csv file
                    try {
                        Path decPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv");
                        Path encPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\data.csv.enc");
                        String[] ivAndAes = DecryptCsvFile.encryptCsv(decPath, encPath);

                        Path encAesPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc");
                        DecryptAESKey.encryptAesKey("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\public.pem", ivAndAes, encAesPath);

                        String mePath = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\Encryption-Test\\private.pem";
                        String sigPath = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc.sig";
                        VerifySignature.createSig(mePath, encAesPath, sigPath);
                    }
                    catch (Exception i)
                    {
                        System.out.println(i);
                    }

                    // send encrypted files back
                    String host = "192.168.1.69";
                    int port = 22;

                    // decrypting password
                    //String sftpPass = DecryptCsvFile.decryptString(sftpEncPass);

                    String[] remoteFilePaths = {"/shared/data.csv.enc", "/shared/aes_key.enc.sig", "/shared/aes_key.enc", "/shared/aes_iv.txt"};
                    String[] localFilePaths = {"C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\data.csv.enc", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc.sig", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc", "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_iv.txt"};
                    SFTPConnect.uploadFiles(host, port, "sftpuser",sftpEncPass, remoteFilePaths, localFilePaths);

                    // close window
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();

                    // remove files in sharedFolder
                    File folder = new File("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder");
                    File[] listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println("File " + listOfFiles[i].getName());
                            listOfFiles[i].delete();
                        }
                        else
                        {
                            return;
                        }
                    }

                    new File("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv").delete();

                    // end program
                    System.exit(0);

                    // TODO: delete files that are transferred with sftp (sharedFolder)
                }
                else if(item.equals("ADD"))
                {
                    addFrame(e, list, frame, pathWebsiteInfo, pathButtonWebsites, pathChrome, csvFile, sftpEncPass);
                }
                else if(item.equals("EDIT"))
                {
                    editFrame(e, items, list, frame, pathWebsiteInfo, pathButtonWebsites, pathChrome, csvFile, sftpEncPass);
                }
                else
                {
                    WebsitePageImporter.importWebsitePage(pathChrome, item, csvFile);
                }
            });
            panel.add(button); // Add the button to the panel
        }

        // Add the JPanel to the JFrame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }
}