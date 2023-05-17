import Encryption.DecryptAESKey;
import Encryption.DecryptCsvFile;
import Encryption.VerifySignature;
import Frames.Login;
import Frames.Website;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();

        /* TONE */
        //String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/linux/chromedriver_linux64/chromedriver";
        //String pathButtonWebsites = "/home/tone/IdeaProjects/practice-enterprise/src/buttonWebsites.txt";
        // String pathWebsiteInfo = "/home/tone/IdeaProjects/practice-enterprise/src/websiteInfo.csv";

        //String pathChrome = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\instalation\\chromedriver.exe";
        //String pathButtonWebsites = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";
        //String pathWebsiteInfo = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";

        /* ROBIN */
        //String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe";
        //String pathButtonWebsites = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\buttonWebsites.txt";
        //String pathWebsiteInfo = "C:\\Users\\robin\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv";

        /*TEST HOME*/
        String basePath = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\";

        String pathChrome = basePath + "instalation\\chromedriver.exe";
        String pathButtonWebsites = basePath + "src\\buttonWebsites.txt";
        String pathWebsiteInfo = basePath + "src\\websiteInfo.csv";

        /* ENCRYPTION
        Path decPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\websiteInfo.csv");
        Path encPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\data.csv.enc");
        String[] test = DecryptCsvFile.encryptCsv(decPath, encPath);

        Path encAesPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\src\\aes_key.enc");
        DecryptAESKey.encryptAesKey("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\Encryption-Test\\public.pem", test, encAesPath);*/

        /* test sig not working yet
        Path encAesPath = Path.of("C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_key.enc");
        String mePath = "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\Encryption-Test\\private.pem";
        VerifySignature.createSig(mePath, encAesPath);*/

        Login.loginFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
        //Website.webFrame(pathButtonWebsites, pathWebsiteInfo, list, pathChrome);
    }
}