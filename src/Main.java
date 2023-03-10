import UI.ApplicationUI;

public class Main {
    public static void main(String[] args) {

        // TODO: make a way to select the website that you want to login
        /* SFTP */
        String host = "192.168.1.69";
        int port = 22;
        String username = "sftpuser";
        String SFTP_password = "W@chtwoord";
        String remoteFilePath = "/shared/test.csv";
        String localFilePath = "/home/tone/IdeaProjects/practice-enterprise/src/test.csv";

        //SFTPConnect.connectSFTP(host, port, username ,SFTP_password, remoteFilePath, localFilePath);

        String url = "https://www.facebook.com/";
        String email = ReadPasswordFile.readFile().get(0)[0];
        String password = ReadPasswordFile.readFile().get(0)[1];
        /* TONE */
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        /* ROBIN
        String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe";
         */
        System.out.println(email + " " + password);
        ApplicationUI.AccessHTMLPage(pathChrome);
        //FacebookPageImporter.importFacebookPage(url,email, password, pathChrome);


    }
}
