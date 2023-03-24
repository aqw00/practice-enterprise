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
        String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";

        //Connection.SFTPConnect.connectSFTP(host, port, username ,SFTP_password, remoteFilePath, localFilePath);

        String url = "";

        String email = Reading.ReadPasswordFile.readFile().get(0)[0];
        String password = Reading.ReadPasswordFile.readFile().get(0)[1];
        String browser = Reading.ReadPasswordFile.readFile().get(0)[2];
        /* TONE*/
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";

        /* ROBIN*/
        // String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe";


        // UI.ApplicationUI.AccessHTMLPage(pathChrome); //start ui html
        //FacebookPageImporter.importFacebookPage(url,email, password, pathChrome);

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

        JButton btnOutlook = new JButton("Outlook");
        panel.add(btnOutlook);

        // Add ActionListeners to the buttons
        btnFacebook.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://www.facebook.com/",email, password, pathChrome, "email", "pass", "login"));
        btnGoogle.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://accounts.google.com/ServiceLogin?hl=NL",email, password, pathChrome, "identifier", "Passwd", "login"));
        btnAmazon.addActionListener(e -> System.out.println("Choice: Amazon!"));
        btnGitHub.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://github.com/login",email, password, pathChrome, "login", "password", "login"));
        btnInstagram.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://www.instagram.com/",email, password, pathChrome, "username", "password", "login"));
        btnNetflix.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://www.netflix.com/nl/login",email, password, pathChrome, "userLoginId", "password", "login"));
        btnThomasMore.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://thomasmore365.sharepoint.com/sites/s.eict.denayer/SitePages/Home.aspx",email, password, pathChrome, "loginfmt", "passwd", "login"));
        btnOutlook.addActionListener(e -> FacebookPageImporter.importFacebookPage("https://outlook.live.com/owa/?nlp=1",email, password, pathChrome, "loginfmt", "passwd", "login"));

        // Add the JPanel to the JFrame and make it visible
        frame.add(panel);
        frame.setVisible(true);


    }
}
