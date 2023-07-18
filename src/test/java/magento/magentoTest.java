package magento;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

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

       // WebDriver driver = new ChromeDriver();
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
        By firstProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(1)");
        By secondProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(2)");


        By firstSizeElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='32']");
        By secondSizeElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='33']");

        By firstColorElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='Black']");
        By secondColorElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='Brown']");


        By firstAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[1]//button[@title='Add to Cart']");
        By secondAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[2]//button[@title='Add to Cart']");

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

        addProductToCart(driver, wait, js, firstProductElem, firstSizeElem, firstColorElem, firstAddToCartElem );

        String numberOfItems = getTotalItem(driver, wait, js, showCartElem ,countNumberItemElem);

        Assert.assertEquals(numberOfItems,"1");

        addProductToCart(driver, wait, js, secondProductElem, secondSizeElem, secondColorElem, secondAddToCartElem );

        String numberOfItems2 = getTotalItem(driver, wait, js, showCartElem ,countNumberItemElem);

        Assert.assertEquals(numberOfItems2,"2");

        driver.quit();
    }

    @Test
    void verifyInformationOfProduct() throws InterruptedException {
        By searchTextBox = By.cssSelector("input[id='search']");
        By notificationElem = By.cssSelector("span[class='base']");
        By firstProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(1)");
        By secondProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(2)");


        By firstSizeElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='32']");
        By secondSizeElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='33']");

        By firstColorElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='Black']");
        By secondColorElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='Brown']");


        By firstAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[1]//button[@title='Add to Cart']");
        By secondAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[2]//button[@title='Add to Cart']");

        By showCartElem = By.xpath("//a[@class='action showcart']");
        By countNumberItemElem = By.xpath("//span[@class='counter-number'][contains(text(),*)]");
        By checkoutButton = By.cssSelector("button[id='top-cart-btn-checkout']");
        By nameOfProduct1 = By.xpath("//ol[@class='minicart-items']//li[1]//strong[@class='product-item-name']");
        By nameOfProduct2 = By.xpath("//ol[@class='minicart-items']//li[2]//strong[@class='product-item-name']");

        By viewDetailsProduct1 = By.xpath("//ol[@class='minicart-items']//li[1]//span[@class='toggle']");
        By viewDetailsProduct2 = By.xpath("//ol[@class='minicart-items']//li[2]//span[@class='toggle']");
        By sizeOfProduct = By.xpath("//div[@class='product options active']//dd[@class='values'][1]");
        By colorOfProduct = By.xpath("//div[@class='product options active']//dd[@class='values'][2]");

        WebDriver driver = new ChromeDriver();

/*        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);*/

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int TIME_OUT_IN_SECONDS = 50;
        WebDriverWait wait;
        wait= new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));

        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().window().maximize();

        driver.findElement(searchTextBox).sendKeys("pants" + Keys.ENTER);

        String notification = driver.findElement(notificationElem).getText();

        Assert.assertEquals(notification, "Search results for: 'pants'");

        addProductToCart(driver, wait, js, firstProductElem, firstSizeElem, firstColorElem, firstAddToCartElem );

        String numberOfItems = getTotalItem(driver, wait, js, showCartElem ,countNumberItemElem);

        Assert.assertEquals(numberOfItems,"1");

        addProductToCart(driver, wait, js, secondProductElem, secondSizeElem, secondColorElem, secondAddToCartElem );

        String numberOfItems2 = getTotalItem(driver, wait, js, showCartElem ,countNumberItemElem);

        Assert.assertEquals(numberOfItems2,"2");

        driver.findElement(showCartElem).click();

        driver.findElement(checkoutButton).click();

        Thread.sleep(10000);

        driver.findElement(viewDetailsProduct1).click();

        Assert.assertEquals(driver.findElement(nameOfProduct1).getText(),"Caesar Warm-Up Pant");
        Assert.assertEquals(driver.findElement(sizeOfProduct).getText(),"32");
        Assert.assertEquals(driver.findElement(colorOfProduct).getText(),"Black");

        driver.findElement(viewDetailsProduct1).click();

        driver.findElement(viewDetailsProduct2).click();
        Assert.assertEquals(driver.findElement(nameOfProduct2).getText(),"Aether Gym Pant");
        Assert.assertEquals(driver.findElement(sizeOfProduct).getText(),"33");
        Assert.assertEquals(driver.findElement(colorOfProduct).getText(),"Brown");

        driver.quit();
    }

    public String getTotalItem(WebDriver driver, WebDriverWait wait,JavascriptExecutor js,By cartButton, By cartValue ) throws InterruptedException {

        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(cartButton));
        Thread.sleep(10000);

        String numberOfItems = "0";
        String collectedValue = wait.until(ExpectedConditions.visibilityOfElementLocated(cartValue)).getText();

        if (!collectedValue.equals("0")){
            numberOfItems = collectedValue;;
            System.out.println("numberOfItems: " + numberOfItems);
        }else {
            System.out.println("Cannot find total items in cart");
            System.out.println("collectedValue: " + collectedValue);
        }
        return numberOfItems;
    }

    public void addProductToCart(WebDriver driver, WebDriverWait wait,JavascriptExecutor js, By product, By size, By color, By addToCartButton) throws InterruptedException {

        Actions mouse = new Actions(driver);

        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(product));

        mouse.moveToElement(driver.findElement(product)).perform();

        Thread.sleep(5000);

        driver.findElement(size).click();
        driver.findElement(color).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).click();

    }
}
