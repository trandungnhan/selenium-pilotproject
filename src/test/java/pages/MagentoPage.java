package pages;

import org.openqa.selenium.By;
import static Common.Browser.*;
import static Common.Browser.scrollIntoView;

public class MagentoPage {

    public static By welcomeMegElem = By.xpath("//div[@class='panel header']/*//span[@class='not-logged-in']");
    By searchTextBox = By.cssSelector("input[id='search']");
    public static By notificationElem = By.cssSelector("span[class='base']");
    By searchButton = By.cssSelector("button[title='Search']");
    String productElem = "ol[class='products list items product-items'] li:nth-of-type(%d)";
    String sizeElem = "//ol[@class='products list items product-items']/li[%d]//div[@option-label='%d']";
    String colorElem = "//ol[@class='products list items product-items']/li[%d]//div[@option-label='%s']";
    String addToCartButton = "//ol[@class='products list items product-items']/li[%d]//button[@title='Add to Cart']";

    public static By showCartElem = By.xpath("//a[@class='action showcart']");
    By countNumberItemElem = By.xpath("//span[@class='counter-number'][contains(text(),*)]");

    public static By checkoutButton = By.cssSelector("button[id='top-cart-btn-checkout']");
    public static String nameOfProduct = "//ol[@class='minicart-items']//li[%d]//strong[@class='product-item-name']";
    public static String viewDetailsProduct = "//ol[@class='minicart-items']//li[%d]//span[@class='toggle']";

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
    By disableSearchButton = By.xpath("//button[@disabled]");
    By inactiveItemsInCart = By.xpath("//div[@class='block items-in-cart']");

    public MagentoPage() {
        getDriver();
        getWait();
    }

    public void searchPants(){
        maxWindows();
        sendText(searchTextBox,"pants");
        waitLoading(disableSearchButton);
        click(searchButton);
    }

    public void addProductToCart(int index, int size, String color) {
        scrollIntoView(By.cssSelector(String.format(productElem,index)));
        hover(By.cssSelector(String.format(productElem,index)));
        click(By.xpath(String.format(sizeElem,index,size)));
        click(By.xpath(String.format(colorElem,index,color)));
        click(By.xpath(String.format(addToCartButton,index)));
    }

    //Todo: need to update getTotalItem to remove check collectedValue
    //Todo: need to update to remove check !waitCounterQtyLoading()
    public String getTotalItem(){

        scrollIntoView(showCartElem);
        waitLoading(counterQtyLoadingElem);
        return getMessage(countNumberItemElem);
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

    //Todo: need to update to remove check !waitCheckoutLoading()
    public void navigateToCheckoutPage(){
        click(showCartElem);
        click(checkoutButton);
        waitLoading(checkoutLoaderElem);
    }

    public void selectFixedMethod(){
        click(fixedMethodRadio);
        click(nextButton);
    }

    public String getTotalPrice(){
        return getMessage(totalPriceElem);
    }

    public String getProductName(int index){
        return getMessage(By.xpath(String.format(nameOfProduct,index)));
    }

    public String getProductSize(){
        return getMessage(sizeOfProduct);
    }

    public String getProductColor(){
        return getMessage(colorOfProduct);
    }

    public String getWelcomeMeg(){
        return getMessage(welcomeMegElem);
    }

    public String getNotification(){
        return getMessage(notificationElem);
    }

    public void selectViewDetailProduct(int index){
        waitLoading(inactiveItemsInCart);
        click(By.xpath(String.format(viewDetailsProduct,index)));
    }

    public void unselectViewDetailProduct(int index){
        click(By.xpath(String.format(viewDetailsProduct,index)));
    }

}
