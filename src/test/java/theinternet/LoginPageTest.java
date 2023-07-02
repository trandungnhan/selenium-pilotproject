package theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest {

    @Test
    void LoginSuccess(){
        //WebDriver driver = new ChromeDriver();
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

        driver.get("https://the-internet.herokuapp.com/login");

        By usernameTextBox = By.id("username");
        By passwordTextBox = By.id("password");
        By submitButton = By.cssSelector("button[type='submit']");
        By messageElem = By.cssSelector("h4[class='subheader']");


        driver.findElement(usernameTextBox).sendKeys("tomsmith");
        driver.findElement(passwordTextBox).sendKeys("SuperSecretPassword!");
        driver.findElement(submitButton).click();

        String successfulMeg = driver.findElement(messageElem).getText();
        Assert.assertEquals(successfulMeg,"Welcome to the Secure Area. When you are done click logout below.");
        driver.quit();

    }




}
