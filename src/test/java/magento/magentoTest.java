package magento;

import Common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MagentoPage;

import static pages.MagentoPage.*;

public class magentoTest  {

    Browser browser;
    MagentoPage magentoPage;

    @BeforeClass
    void openBrowser() {
        browser.launch();
        magentoPage = new MagentoPage();
        magentoPage.open();
    }

    @AfterClass
    void closeBrowser(){
        browser.quit();
    }

    @Test
    void verifyWelcomeMegs() {
        Assert.assertEquals(magentoPage.getWelcomeMeg(),"Default welcome msg!");
    }

    @Test
    void verifySearchPants() {
        magentoPage.searchPants();
        Assert.assertEquals(magentoPage.getNotification(), "Search results for: 'pants'");
    }

    @Test
    void verifyNumberOfProduct() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart(1,32,"Black");
        Assert.assertEquals(magentoPage.getTotalItem(),"1");

        magentoPage.addProductToCart(2,33,"Brown");
        Assert.assertEquals(magentoPage.getTotalItem(),"2");
    }

    @Test
    void verifyInformationOfProduct() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart(1,32,"Black");
        Assert.assertEquals(magentoPage.getTotalItem(),"1");

        magentoPage.addProductToCart(2,33,"Brown");
        Assert.assertEquals(magentoPage.getTotalItem(),"2");

        magentoPage.navigateToCheckoutPage();
        magentoPage.selectViewDetailProduct(1);

        Assert.assertEquals(magentoPage.getProductName(1),"Caesar Warm-Up Pant");
        Assert.assertEquals(magentoPage.getProductSize(),"32");
        Assert.assertEquals(magentoPage.getProductColor(),"Black");
        magentoPage.selectViewDetailProduct(1);

        Thread.sleep(5000);
        magentoPage.selectViewDetailProduct(2);
        Assert.assertEquals(magentoPage.getProductName(2),"Aether Gym Pant");
        Assert.assertEquals(magentoPage.getProductSize(),"33");
        Assert.assertEquals(magentoPage.getProductColor(),"Brown");
        magentoPage.unselectViewDetailProduct(2);

    }

    @Test
    void verifyOrderTotalPrice() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart(1,32,"Black");
        Assert.assertEquals(magentoPage.getTotalItem(),"1");

        magentoPage.addProductToCart(2,33,"Brown");
        Assert.assertEquals(magentoPage.getTotalItem(),"2");

        magentoPage.navigateToCheckoutPage();

        magentoPage.fillShippingAddressInfo("test@gmail.com","Tuan", "Nguyen",
                "On1", "26/2 Pham Van Chieu","Ho Chi Minh","9999",
                "0909090909", "Alaska","United States");

        magentoPage.selectRegion("Alaska");
        magentoPage.selectCountry("United States");

        magentoPage.selectFixedMethod();

        Assert.assertEquals(magentoPage.getTotalPrice(),"$109.00");

    }
}
