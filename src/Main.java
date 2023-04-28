import Frames.Website;
import Reading.ReadTxtFile;
import Reading.WriteCsvFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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

        List<String> list = new ArrayList<>();

        /* TONE */
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        String pathButtonWebsites = "/home/tone/IdeaProjects/practice-enterprise/src/buttonWebsites.txt";
        String pathWebsiteInfo = "/home/tone/IdeaProjects/practice-enterprise/src/websiteInfo.csv";

        //String pathChrome = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\instalation\\chromedriver.exe";
        //String pathButtonWebsites = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";
        //String pathWebsiteInfo = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";
        /* ROBIN */
        // String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\intellij\\instalation\\chromedriver.exe";
        // String pathButtonWebsites = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";

        Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
    }
}
