package Frames;

import Reading.ReadTxtFile;
import Reading.WriteCsvFiles;
import WebsiteImporter.WebsitePageImporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static Frames.Add.addFrame;

public class Website extends JFrame {
    public static void webFrame (String pathButtonWebsites, String pathWebsiteInfo ,List<String> list, String pathChrome)
    {
        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);

        // Array of items
        List<String> items = ReadTxtFile.txtFileHandeling(pathButtonWebsites, true, "");

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 2));

        for (String item : items) {
            JButton button = new JButton(item); // Create a new JButton with the text of the current item
            button.addActionListener((ActionEvent e) -> {
                // Handle the button click event here
                if(item.equals("ADD"))
                {
                    addFrame(e, list, frame, pathWebsiteInfo, pathButtonWebsites);
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
