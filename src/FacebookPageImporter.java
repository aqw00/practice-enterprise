import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FacebookPageImporter {
    public static void importFacebookPage(String url, String email, String password) {
        // Create a new instance of the Chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aqw00\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the Facebook login page
        driver.get(url);

        // Find the email input field and set its value
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);

        // Find the password input field and set its value
        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(password);

        // Submit the login form
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // Wait for the login process to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Navigate to the Facebook page to import
        driver.get("https://www.facebook.com/pages/import");

        // Wait for the page to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Import the page
        WebElement importButton = driver.findElement(By.xpath("//button[contains(text(), 'Import Page')]"));
        importButton.click();

        // Close the browser
        driver.quit();
    }
}
