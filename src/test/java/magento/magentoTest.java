package magento;

import Common.Browser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.MagentoPage;

/*
Issue 1: Sometime search is not enable after text is inputted in search text box.
Failed: searchPants()

* */

public class magentoTest  {

    Browser browser;
    MagentoPage magentoPage;

    @BeforeMethod
    void openBrowser() {
        browser.launch(false);
        magentoPage = new MagentoPage();
        magentoPage.open();
    }

    @AfterMethod
    void captureResultAndCloseBrowser(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            Browser.captureScreenShot(testResult.getMethod().getMethodName());
        } else{
            browser.quit();
        }
        //browser.quit();
    }

    @Test(priority = 1)
    void verifyWelcomeMegs() {
        Assert.assertEquals(magentoPage.getWelcomeMeg(),"Default welcome msg!");
    }

    @Test(priority = 2)
    void verifySearchPants() {
        magentoPage.searchPants();
        Assert.assertEquals(magentoPage.getNotification(), "Search results for: 'pants'");
    }

    @Test(priority = 3)
    void verifyNumberOfProduct(){

        magentoPage.searchPants();

        magentoPage.addProductToCart(1,32,"Black");
        Assert.assertEquals(magentoPage.getTotalItem(),"1");

        magentoPage.addProductToCart(2,33,"Brown");
        Assert.assertEquals(magentoPage.getTotalItem(),"2");
    }

    @Test(priority = 4)
    void verifyInformationOfProduct() {

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

        magentoPage.selectViewDetailProduct(2);
        Assert.assertEquals(magentoPage.getProductName(2),"Aether Gym Pant");
        Assert.assertEquals(magentoPage.getProductSize(),"33");
        Assert.assertEquals(magentoPage.getProductColor(),"Brown");
        magentoPage.unselectViewDetailProduct(2);

    }

    @Test(priority = 5)
    void verifyOrderTotalPrice(){

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
