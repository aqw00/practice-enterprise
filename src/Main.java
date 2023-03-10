public class Main {
    public static void main(String[] args) {



        String email = ReadPasswordFile.readFile().get(0)[0];
        String password = ReadPasswordFile.readFile().get(0)[1];



        /*
            TONE:
                - "/home/tone/IdeaProjects/practice-enterprise/instalation/chromedriver_linux64/chromedriver"
            ROBIN:
                - "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe"
         */

        FacebookPageImporter.importFacebookPage("https://www.facebook.com/",email, password,
                "C:\\Users\\robin\\OneDrive\\Documenten\\Thomas More\\Semester2\\Practice Enterprise\\practice-enterprise\\chromedriver_win32\\chromedriver.exe");


    }
}
