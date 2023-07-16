package magento;

import org.openqa.selenium.*;
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

        WebDriver driver = new ChromeDriver();
//        WebDriver driver;
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless=new");
//        driver = new ChromeDriver(chromeOptions);

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
        By firstProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(1)");
        By firstSizeElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='32']");
        By firstColorElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='Black']");
        By firstAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[1]//button[@title='Add to Cart']");
        By showCartElem = By.xpath("//a[@class='action showcart']");
        By countNumberItemElem = By.xpath("//span[@class='counter-number'][contains(text(),*)]");

        //WebDriver driver = new ChromeDriver();

        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int TIME_OUT_IN_SECONDS = 50;
        WebDriverWait wait;
        wait= new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));

        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().window().maximize();

        driver.findElement(searchTextBox).sendKeys("pants" + Keys.ENTER);

        String notification = driver.findElement(notificationElem).getText();

        Assert.assertEquals(notification, "Search results for: 'pants'");

        Actions mouse = new Actions(driver);

        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(firstProductElem));

        mouse.moveToElement(driver.findElement(firstProductElem)).perform();

        Thread.sleep(5000);

        driver.findElement(firstSizeElem).click();
        driver.findElement(firstColorElem).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAddToCartElem)).click();

        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(showCartElem));

        String numberOfItems = getTotalItem(wait,countNumberItemElem);

        Assert.assertEquals(numberOfItems,"1");

        driver.quit();

    }

    public String getTotalItem(WebDriverWait wait, By locator) throws InterruptedException {

        Thread.sleep(10000);
        String numberOfItems = "0";
        String collectedValue = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        if (!collectedValue.equals("0")){
            numberOfItems = collectedValue;;
            System.out.println("numberOfItems: " + numberOfItems);
        }else {
            System.out.println("Cannot find total items in cart");
            System.out.println("collectedValue: " + collectedValue);
        }
        return numberOfItems;
    }
}
