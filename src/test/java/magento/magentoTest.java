package magento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
        //WebDriver driver = new ChromeDriver();
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

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

        //WebDriver driver = new ChromeDriver();
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(searchTextBox).sendKeys("pants");

        driver.findElement(searchButton).click();

        String notification = driver.findElement(notificationElem).getText();

        Assert.assertEquals(notification, "Search results for: 'pants'");
    }

    @Test
    void verifyHoverAndSelectFirstProduct() throws InterruptedException {
        By searchTextBox = By.cssSelector("input[id='search']");
        By notificationElem = By.cssSelector("span[class='base']");
        By searchButton = By.cssSelector("button[title='Search']");
        By firstProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(1)");
        By firstSizeElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='32']");
        By firstColorElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='Black']");
        By firstAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[1]//button[@title='Add to Cart']");
        By countNumberItemElem = By.cssSelector("span[class='counter-number']");

        //WebDriver driver = new ChromeDriver();
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

        int TIME_OUT_IN_SECONDS = 30;
        WebDriverWait wait;
        wait= new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));

        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().window().maximize();

        driver.findElement(searchTextBox).sendKeys("pants");

        driver.findElement(searchButton).click();

        String notification = driver.findElement(notificationElem).getText();

        Assert.assertEquals(notification, "Search results for: 'pants'");

        Actions mouse = new Actions(driver);

        mouse.moveToElement(driver.findElement(firstProductElem)).perform();

        driver.findElement(firstSizeElem).click();

        driver.findElement(firstColorElem).click();

        driver.findElement(firstAddToCartElem).click();

        String numberProducts = wait.until(ExpectedConditions.visibilityOfElementLocated(countNumberItemElem)).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(countNumberItemElem)).click();

        //System.out.println("numberProducts" + numberProducts);

       //Assert.assertEquals(numberProducts,"1");

        driver.quit();

    }
}
