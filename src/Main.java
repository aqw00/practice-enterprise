import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        // TODO: make a way to select the website that you want to login
        /* SFTP */
        String host = "192.168.1.69";
        int port = 22;
        String username = "sftpuser";
        String SFTP_password = "W@chtwoord"; // TODO: find a way to hide this
        String remoteFilePath = "/shared/test.csv";
        String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";

        //Connection.SFTPConnect.connectSFTP(host, port, username ,SFTP_password, remoteFilePath, localFilePath);

        String url = "https://www.facebook.com/";

        String email = Reading.ReadPasswordFile.readFile().get(0)[0];
        String password = Reading.ReadPasswordFile.readFile().get(0)[1];
        String browser = Reading.ReadPasswordFile.readFile().get(0)[2];
        /* TONE */
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        /* ROBIN
        String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe";
         */
        System.out.println(email + " " + password + " " + browser);
        System.out.println(Reading.ReadBrowser.chooseBrowser("google"));
        // UI.ApplicationUI.AccessHTMLPage(pathChrome); //start ui html
        //FacebookPageImporter.importFacebookPage(url,email, password, pathChrome); start driver with facebook

        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 5));

        JButton button1 = new JButton("Button 1");
        panel.add(button1);

        JButton button2 = new JButton("Button 2");
        panel.add(button2);

        JButton button3 = new JButton("Button 3");
        panel.add(button3);

        JButton button4 = new JButton("Button 4");
        panel.add(button4);

        JButton button5 = new JButton("Button 3");
        panel.add(button5);

        JButton button6 = new JButton("Button 4");
        panel.add(button6);

        JButton button7 = new JButton("Button 3");
        panel.add(button7);

        JButton button8 = new JButton("Button 4");
        panel.add(button8);

        JButton button9 = new JButton("Button 3");
        panel.add(button9);

        JButton button10 = new JButton("Button 4");
        panel.add(button10);

        // Add ActionListeners to the buttons
        button1.addActionListener(e -> System.out.println("Button 1 clicked!"));
        button2.addActionListener(e -> System.out.println("Button 2 clicked!"));
        button3.addActionListener(e -> System.out.println("Button 3 clicked!"));
        button4.addActionListener(e -> System.out.println("Button 4 clicked!"));
        button5.addActionListener(e -> System.out.println("Button 5 clicked!"));
        button6.addActionListener(e -> System.out.println("Button 6 clicked!"));
        button7.addActionListener(e -> System.out.println("Button 7 clicked!"));
        button8.addActionListener(e -> System.out.println("Button 8 clicked!"));
        button9.addActionListener(e -> System.out.println("Button 9 clicked!"));
        button10.addActionListener(e -> System.out.println("Button 10 clicked!"));

        // Add the JPanel to the JFrame and make it visible
        frame.add(panel);
        frame.setVisible(true);


    }
}
