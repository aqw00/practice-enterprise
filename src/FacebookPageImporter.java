import Reading.ReadingSiteData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FacebookPageImporter {
    public static void importFacebookPage( String pathChromedriver, String siteName)
    {
        String url = "", email = "", password = "", usernamePosition = "", passwordPosition = "", buttonPosition = "";
        /*
                GET DATA FROM FILE
         */
        for (int i = 0; i < (ReadingSiteData.readFile().size()); i++) {
            if(ReadingSiteData.readFile().get(i)[0].equals(siteName))
            {
                url = ReadingSiteData.readFile().get(i)[1];
                email = ReadingSiteData.readFile().get(i)[2];
                password = ReadingSiteData.readFile().get(i)[3];
                usernamePosition = ReadingSiteData.readFile().get(i)[4];
                passwordPosition = ReadingSiteData.readFile().get(i)[5];
                buttonPosition = ReadingSiteData.readFile().get(i)[6];
            }
            else
            {
                //System.out.println(ReadingSiteData.readFile().get(i)[0]);
            }


        }


        // Create a new instance of the Chrome driver
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();

        // Navigate to the Facebook login page
        driver.get(url);

        // Find the email input field and set its value
        WebElement emailInput = driver.findElement(By.name(usernamePosition));
        emailInput.sendKeys(email);

        // Find the password input field and set its value
        WebElement passwordInput = driver.findElement(By.name(passwordPosition));
        passwordInput.sendKeys(password);

        // Submit the login form
        WebElement loginButton = driver.findElement(By.name(buttonPosition));
        //TODO: loginButton.submit();

        // Wait for the login process to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        //driver.quit();
    }
}
