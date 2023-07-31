package Common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Browser {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static int TIME_OUT_IN_SECONDS = 50;

    public static void launch(Boolean headless){
        if (headless){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new");
            driver = new ChromeDriver(chromeOptions);
        } else{
            driver = new ChromeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
    }

    public static void visit(String url){
        driver.get(url);
    }

    public static void quit(){
        driver.quit();
    }

    public static void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
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

    public static void waitLoading(By element){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public static void captureScreenShot(String fileName) {
        TakesScreenshot scrShot = ((TakesScreenshot) Browser.getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(String.format("target/%s.png", fileName));
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}