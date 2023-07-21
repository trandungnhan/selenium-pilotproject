package magento;

import Common.Browser;
import org.testng.Assert;
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

    @Test
    void verifyWelcomeMegs() {

        String welcomeMeg = browser.getMessage(welcomeMegElem);
        Assert.assertEquals(welcomeMeg,"Default welcome msg!");
        browser.quit();
    }

    @Test
    void verifySearchPants() {

        magentoPage.searchPants();

        String notification = browser.getMessage(notificationElem);
        Assert.assertEquals(notification, "Search results for: 'pants'");
        browser.quit();
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

        browser.quit();
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
        browser.click(showCartElem);
        browser.click(checkoutButton);

        //Todo : waiting for loading
        Thread.sleep(10000);
        browser.click(viewDetailsProduct1);
        Assert.assertEquals(browser.getMessage(nameOfProduct1),"Caesar Warm-Up Pant");
        Assert.assertEquals(browser.getMessage(sizeOfProduct),"32");
        Assert.assertEquals(browser.getMessage(colorOfProduct),"Black");
        browser.click(viewDetailsProduct1);

        browser.click(viewDetailsProduct2);
        Assert.assertEquals(browser.getMessage(nameOfProduct2),"Aether Gym Pant");
        Assert.assertEquals(browser.getMessage(sizeOfProduct),"33");
        Assert.assertEquals(browser.getMessage(colorOfProduct),"Brown");

        browser.quit();
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
        browser.click(showCartElem);
        browser.click(checkoutButton);

        //Todo: Waiting for loading
        Thread.sleep(20000);
        browser.sendText(emailTextBox,"test@gmail.com");
        browser.sendText(firstnameTextBox,"Tuan");
        browser.sendText(lastnameTextBox,"Nguyen");
        browser.sendText(companyTextBox,"On1");
        browser.sendText(streetAddressTextBox,"26/2 Pham Van Chieu");
        browser.sendText(cityTextBox,"Ho Chi Minh");
        browser.sendText(postcodeTextBox,"9999");
        browser.sendText(phoneNumberTextBox,"0909090909");

        magentoPage.selectRegion("Alaska");
        magentoPage.selectCountry("United States");

        browser.click(fixedMethodRadio);
        browser.click(nextButton);

        String totalPrice = browser.getMessage(totalPriceElem);
        Assert.assertEquals(totalPrice,"$109.00");

        browser.quit();
    }





}
