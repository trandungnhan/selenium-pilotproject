package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Common.Browser.*;

public class MagentoPage {

    public static By welcomeMegElem = By.xpath("//div[@class='panel header']/*//span[@class='not-logged-in']");

    public MagentoPage() {
        getDriver();
        getWait();
    }



}
