import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        /* SFTP */
        String host = "192.168.1.69";
        int port = 22;
        String username = "sftpuser";
        String SFTP_password = "W@chtwoord"; // TODO: find a way to hide this
        String remoteFilePath = "/shared/test.csv";
        //String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";

        //Connection.SFTPConnect.connectSFTP(host, port, username ,SFTP_password, remoteFilePath, localFilePath);

        String url = "https://www.facebook.com/";

        String email = Reading.ReadPasswordFile.readFile().get(0)[0];
        String password = Reading.ReadPasswordFile.readFile().get(0)[1];
        String browser = Reading.ReadPasswordFile.readFile().get(0)[2];
        /* TONE
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
         */
        /* ROBIN*/
        // String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe";


        // UI.ApplicationUI.AccessHTMLPage(pathChrome); //start ui html
        //FacebookPageImporter.importFacebookPage(url,email, password, pathChrome); start driver with facebook

        // Create a JFrame and set its size
        JFrame frame = new JFrame("My UI");
        frame.setSize(300, 200);

        // Create a JPanel with a GridLayout and add four buttons to it
        JPanel panel = new JPanel(new GridLayout(5, 5));

        JButton btnFacebook = new JButton("Facebook");
        panel.add(btnFacebook);

        JButton btnGoogle = new JButton("Google");
        panel.add(btnGoogle);

        JButton btnAmazon = new JButton("Amazon");
        panel.add(btnAmazon);

        JButton btnGitHub = new JButton("GitHub");
        panel.add(btnGitHub);

        JButton btnInstagram = new JButton("Instagram");
        panel.add(btnInstagram);

        JButton btnNetflix = new JButton("Netflix");
        panel.add(btnNetflix);

        JButton btnThomasMore = new JButton("Thomas More");
        panel.add(btnThomasMore);

        JButton btnSpotify = new JButton("Spotify");
        panel.add(btnSpotify);

        JButton btnSteam = new JButton("Steam");
        panel.add(btnSteam);

        JButton btnOutlook = new JButton("Outlook");
        panel.add(btnOutlook);

        // Add ActionListeners to the buttons
        btnFacebook.addActionListener(e -> System.out.println("Choice: Facebook!"));
        btnGoogle.addActionListener(e -> System.out.println("Choice: Google!"));
        btnAmazon.addActionListener(e -> System.out.println("Choice: Amazon!"));
        btnGitHub.addActionListener(e -> System.out.println("Choice: GitHub!"));
        btnInstagram.addActionListener(e -> System.out.println("Choice: Instagram!"));
        btnNetflix.addActionListener(e -> System.out.println("Choice: Netflix!"));
        btnThomasMore.addActionListener(e -> System.out.println("Choice: Thomas More!"));
        btnSpotify.addActionListener(e -> System.out.println("Choice: Spotify!"));
        btnSteam.addActionListener(e -> System.out.println("Choice: Steam!"));
        btnOutlook.addActionListener(e -> System.out.println("Choice: Outlook!"));

        // Add the JPanel to the JFrame and make it visible
        frame.add(panel);
        frame.setVisible(true);


    }
}
