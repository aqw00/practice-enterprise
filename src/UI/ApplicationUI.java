package UI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class ApplicationUI {


    public static void AccessHTMLPage(String path)
    {

            // Set the path of the ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", path);

            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            // Navigate to the HTML page
            driver.get("file:///home/tone/IdeaProjects/practice-enterprise/src/UI/webUI.html");

            // Close the browser
            //driver.quit();

    }

}
