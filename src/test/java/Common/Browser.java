package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static int TIME_OUT_IN_SECONDS = 50;

    public static void launch(){
        //driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
    }

    public static void visit(String url){
        driver.get(url);
    }

    public static void quit(){
        driver.quit();
    }

    public static void click(By element) {
        //driver.findElement(element).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
    }

    public static void maxWindows(){
        driver.manage().window().maximize();
    }

    public static void sendText(By element, String text){
        driver.findElement(element).sendKeys(text);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static WebDriverWait getWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
    }

    public static String getMessage(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    public static void hover(By element){
        Actions mouse = new Actions(driver);
        mouse.moveToElement(driver.findElement(element)).perform();
    }

    public static void scrollIntoView(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    public static Select selectList(By element){
        return new Select(driver.findElement(element));
    }

    public static Boolean waitLoading(By element){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

}