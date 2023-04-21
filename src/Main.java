import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {

        /* SFTP */
        String host = "192.168.1.69";
        int port = 22;
        String username = "sftpuser";
        String SFTP_password = "W@chtwoord"; // TODO: find a way to hide this
        String remoteFilePath = "/shared/test.csv";
        String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";
        //String localFilePath = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\test.csv";
        String url = "";


        /* TONE */
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        /* ROBIN */
        //String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\intellij\\instalation\\chromedriver.exe";


        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);

        // Array of items
        String[] items = {"ADD", "Facebook", "Google", "Amazon", "Github", "Instagram", "Netflix", "Thomas_More", "Outlook"};

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 5));

        for (String item : items) {
            JButton button = new JButton(item); // Create a new JButton with the text of the current item
            button.addActionListener((ActionEvent e) -> {
                // Handle the button click event here
                if(item.equals("ADD"))
                {
                    System.out.println("Button " + item + " clicked.");
                }
                else
                {
                    FacebookPageImporter.importFacebookPage(pathChrome, item);
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
