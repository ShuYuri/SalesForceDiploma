package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class NewAccountModalPageTest extends BaseTest {

    @Test
    @Description("FILL IN THE ACCOUNT INFORMATION")
    public void newAccountModalPageTest() throws InterruptedException{
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        newAccountModalPage
                .openNewAccountPage()
                .waitForPageLoaded()
                .createInformationIntoAccountNewPage("TestAccount-SalesForce", "www.twt.by", "Partner",
                        "Website for buying / selling cars", "+375 29 800 88 00",
                        "Chemicals", "26", "5th Avenue, New York, 36",
                        "Hello Wold)", "Minsk", "Belarus", "London", "GB",
                        "Xto ego znaet", "Panama", "8899/7766", "France")
                .clickSaveButton();
        Assert.assertEquals(newAccountModalPage.getNewAccountName("TestAccount"),"TestAccount-SalesForce");
        Assert.assertEquals(newAccountModalPage.getNewAccountPhone("800 88 00"), "+375 29 800 88 00");
        Assert.assertEquals(newAccountModalPage.getNewAccountAddress("5th"), "5th Avenue, New York, 36");
        Assert.assertEquals(newAccountModalPage.getNewAccountAddress2("Xto ego znaet"), "Minsk, Belarus Xto ego znaet");
        Assert.assertEquals(newAccountModalPage.getNewAccountState("Pan"), "Panama");
        Assert.assertEquals(newAccountModalPage.getNewAccountWebsite("twt"), "www.twt.by");
    }
}