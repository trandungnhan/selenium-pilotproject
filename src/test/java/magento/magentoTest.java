package magento;

import Common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MagentoPage;

import static pages.MagentoPage.*;
import static pages.MagentoPage.checkoutButton;

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

        String welcomeMeg = browser.getMessage(welcomeMegElem);
        Assert.assertEquals(welcomeMeg,"Default welcome msg!");
    }

    @Test
    void verifySearchPants() {

        magentoPage.searchPants();

        String notification = browser.getMessage(notificationElem);
        Assert.assertEquals(notification, "Search results for: 'pants'");
    }

    @Test
    void verifyNumberOfProduct() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart1();
        String numberOfItems = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems,"1");

        magentoPage.addProductToCart2();
        String numberOfItems2 = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems2,"2");
    }

    @Test
    void verifyInformationOfProduct() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart1();
        String numberOfItems = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems,"1");

        magentoPage.addProductToCart2();
        String numberOfItems2 = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems2,"2");

        magentoPage.navigateToCheckoutPage();

        browser.click(viewDetailsProduct1);
        Assert.assertEquals(browser.getMessage(nameOfProduct1),"Caesar Warm-Up Pant");
        Assert.assertEquals(browser.getMessage(sizeOfProduct),"32");
        Assert.assertEquals(browser.getMessage(colorOfProduct),"Black");
        browser.click(viewDetailsProduct1);

        browser.click(viewDetailsProduct2);
        Assert.assertEquals(browser.getMessage(nameOfProduct2),"Aether Gym Pant");
        Assert.assertEquals(browser.getMessage(sizeOfProduct),"33");
        Assert.assertEquals(browser.getMessage(colorOfProduct),"Brown");

    }

    @Test
    void verifyOrderTotalPrice() throws InterruptedException {

        magentoPage.searchPants();

        magentoPage.addProductToCart1();
        String numberOfItems = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems,"1");

        magentoPage.addProductToCart2();
        String numberOfItems2 = magentoPage.getTotalItem();
        Assert.assertEquals(numberOfItems2,"2");

        magentoPage.navigateToCheckoutPage();

        magentoPage.fillShippingAddressInfo("test@gmail.com","Tuan", "Nguyen",
                "On1", "26/2 Pham Van Chieu","Ho Chi Minh","9999",
                "0909090909", "Alaska","United States");

        magentoPage.selectRegion("Alaska");
        magentoPage.selectCountry("United States");

        browser.click(fixedMethodRadio);
        browser.click(nextButton);

        String totalPrice = browser.getMessage(totalPriceElem);
        Assert.assertEquals(totalPrice,"$109.00");

    }
}
