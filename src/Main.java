import Frames.Login;
import Frames.Website;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        /* TONE */
        //String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        //String pathButtonWebsites = "/home/tone/IdeaProjects/practice-enterprise/src/buttonWebsites.txt";
        // String pathWebsiteInfo = "/home/tone/IdeaProjects/practice-enterprise/src/websiteInfo.csv";

        String pathChrome = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\instalation\\chromedriver.exe";
        String pathButtonWebsites = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";
        String pathWebsiteInfo = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";

        /* ROBIN */
        //String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe";
        //String pathButtonWebsites = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";
        //String pathWebsiteInfo = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";

        Login.loginFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
        //Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
    }
}