package WebsiteImporter;

import Reading.ReadCsvFiles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebsitePageImporter
{
    public static void importWebsitePage(String pathChromedriver, String siteName, String csvFile)
    {
        String url = "", email = "", password = "", usernamePosition = null, passwordPosition = null, buttonPosition = null;

        for (int i = 0; i < (ReadCsvFiles.readFile(csvFile).size()); i++)
        {
            if (ReadCsvFiles.readFile(csvFile).get(i)[0].equals(siteName))
            {
                url = ReadCsvFiles.readFile(csvFile).get(i)[1];
                email = ReadCsvFiles.readFile(csvFile).get(i)[2];
                password = ReadCsvFiles.readFile(csvFile).get(i)[3];
                usernamePosition = ReadCsvFiles.readFile(csvFile).get(i)[4];
                passwordPosition = ReadCsvFiles.readFile(csvFile).get(i)[5];
                buttonPosition = ReadCsvFiles.readFile(csvFile).get(i)[6];
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
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}