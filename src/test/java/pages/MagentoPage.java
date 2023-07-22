package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Common.Browser.*;
import static Common.Browser.scrollIntoView;

public class MagentoPage {

    public static By welcomeMegElem = By.xpath("//div[@class='panel header']/*//span[@class='not-logged-in']");
    By searchTextBox = By.cssSelector("input[id='search']");
    public static By notificationElem = By.cssSelector("span[class='base']");
    By searchButton = By.cssSelector("button[title='Search']");
    By firstProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(1)");
    By secondProductElem = By.cssSelector("ol[class='products list items product-items'] li:nth-of-type(2)");


    By firstSizeElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='32']");
    By secondSizeElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='33']");

    By firstColorElem = By.xpath("//ol[@class='products list items product-items']/li[1]//div[@option-label='Black']");
    By secondColorElem = By.xpath("//ol[@class='products list items product-items']/li[2]//div[@option-label='Brown']");


    By firstAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[1]//button[@title='Add to Cart']");
    By secondAddToCartElem = By.xpath("//ol[@class='products list items product-items']/li[2]//button[@title='Add to Cart']");

    public static By showCartElem = By.xpath("//a[@class='action showcart']");
    By countNumberItemElem = By.xpath("//span[@class='counter-number'][contains(text(),*)]");

    public static By checkoutButton = By.cssSelector("button[id='top-cart-btn-checkout']");
    public static By nameOfProduct1 = By.xpath("//ol[@class='minicart-items']//li[1]//strong[@class='product-item-name']");
    public static By nameOfProduct2 = By.xpath("//ol[@class='minicart-items']//li[2]//strong[@class='product-item-name']");

    public static By viewDetailsProduct1 = By.xpath("//ol[@class='minicart-items']//li[1]//span[@class='toggle']");
    public static By viewDetailsProduct2 = By.xpath("//ol[@class='minicart-items']//li[2]//span[@class='toggle']");
    public static By sizeOfProduct = By.xpath("//div[@class='product options active']//dd[@class='values'][1]");
    public static By colorOfProduct = By.xpath("//div[@class='product options active']//dd[@class='values'][2]");

    public static By emailTextBox = By.xpath("//div[@class='field required']//input[@id='customer-email']");
    public static By firstnameTextBox = By.xpath("//input[@name='firstname']");
    public static By lastnameTextBox = By.xpath("//input[@name='lastname']");
    public static By companyTextBox = By.xpath("//input[@name='company']");
    public static By streetAddressTextBox = By.xpath("//input[@name='street[0]']");
    public static By cityTextBox = By.xpath("//input[@name='city']");
    public static By postcodeTextBox = By.xpath("//input[@name='postcode']");
    public static By phoneNumberTextBox = By.xpath("//input[@name='telephone']");
    By regionSelection = By.xpath("//select[@name='region_id']");
    By countrySelection = By.xpath("//select[@name='country_id']");
    public static By fixedMethodRadio = By.xpath("//input[@value='flatrate_flatrate']");
    public static By nextButton = By.xpath("//span[contains(text(),'Next')]");
    public static By totalPriceElem = By.xpath("//tr[@class='totals sub']//span[@class='price']");

    By counterQtyLoadingElem = By.xpath("//span[@class='counter qty _block-content-loading']");
    By checkoutLoaderElem = By.xpath("//div[@class='loading-mask']");

    public MagentoPage() {
        getDriver();
        getWait();
    }

    public void searchPants(){
        maxWindows();
        //sendText(searchTextBox,"pants"+ Keys.ENTER);
        sendText(searchTextBox,"pants");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).click();
        //click(searchButton);
    }

    public void addProductToCart1() {
        scrollIntoView(firstProductElem);
        hover(firstProductElem);
        click(firstSizeElem);
        click(firstColorElem);
        click(firstAddToCartElem);
    }

    public void addProductToCart2() {
        scrollIntoView(secondProductElem);
        hover(secondProductElem);
        click(secondSizeElem);
        click(secondColorElem);
        click(secondAddToCartElem);
    }

    public String getTotalItem() throws InterruptedException {

        scrollIntoView(showCartElem);
        //Thread.sleep(10000);
        if (!waitCounterQtyLoading()){
            System.out.println("Counter quantity loading is not completed");
        }

        String numberOfItems = "0";
        String collectedValue = getMessage(countNumberItemElem);
        if (!collectedValue.equals("0")) {
            numberOfItems = collectedValue;
            ;
            System.out.println("numberOfItems: " + numberOfItems);
        } else {
            System.out.println("Cannot find total items in cart");
            System.out.println("collectedValue: " + collectedValue);
        }
        return numberOfItems;
    }

    public void fillShippingAddressInfo(String email, String firstname, String lastname,
                                        String company, String street, String city, String postcode,
                                        String phoneNumber, String region, String country){
        sendText(emailTextBox,email);
        sendText(firstnameTextBox,firstname);
        sendText(lastnameTextBox,lastname);
        sendText(companyTextBox,company);
        sendText(streetAddressTextBox,street);
        sendText(cityTextBox,city);
        sendText(postcodeTextBox,postcode);
        sendText(phoneNumberTextBox,phoneNumber);
        selectRegion(region);
        selectCountry(country);
    }

    public void selectRegion(String region){
        selectList(regionSelection).selectByVisibleText(region);
    }

    public void selectCountry(String country){
        selectList(countrySelection).selectByVisibleText(country);
    }

    public void open(){
        visit("https://magento.softwaretestingboard.com/");
    }

    public Boolean waitCounterQtyLoading(){
        return waitLoading(counterQtyLoadingElem);
    }

    public Boolean waitCheckoutLoading(){
        return waitLoading(checkoutLoaderElem);
    }

    public void navigateToCheckoutPage(){
        click(showCartElem);
        click(checkoutButton);
        if(!waitCheckoutLoading()){
            System.out.println("Checkout page isn't loaded completely");
        }
    }
}
