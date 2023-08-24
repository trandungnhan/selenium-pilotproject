package magento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class magentoTest {

    @Test
    void verifyWelcomeMegs() {
        By welcomeMegElem = By.xpath("//div[@class='panel header']/*//span[@class='not-logged-in']");

        int TIME_OUT_IN_SECONDS = 30;
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait;
        wait= new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));

        driver.get("https://magento.softwaretestingboard.com/");

        String welcomeMeg = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMegElem)).getText();

        Assert.assertEquals(welcomeMeg,"Default welcome msg!");

        driver.quit();
    }

    @Test
    void verifySearchPants() {
        By searchTextBox = By.cssSelector("input[id='search']");
        By notificationElem = By.cssSelector("span[class='base']");
        By searchButton = By.cssSelector("button[title='Search']");

        WebDriver driver = new ChromeDriver();

        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(searchTextBox).sendKeys("pants");

        driver.findElement(searchButton).click();

        String notification = driver.findElement(notificationElem).getText();

        Assert.assertEquals(notification, "Search results for: 'pants'");
    }

}
