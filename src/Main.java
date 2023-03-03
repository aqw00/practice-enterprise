public class Main {
    public static void main(String[] args) {

        // TODO: make a way to select the website that you want to login
        String url = "https://www.facebook.com/";
        String email = ReadPasswordFile.readFile().get(0)[0];
        String password = ReadPasswordFile.readFile().get(0)[1];
        /* TONE */
        String pathChrome = "/home/tone/IdeaProjects/practice-enterprise/instalation/chromedriver_linux64/chromedriver";
        /* ROBIN
        String pathChrome = "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe";
         */

        FacebookPageImporter.importFacebookPage(url,email, password, pathChrome);


    }
}
