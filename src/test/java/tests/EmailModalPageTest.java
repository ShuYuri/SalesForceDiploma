package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class EmailModalPageTest extends BaseTest {

    @Test
    @Description("Trying to send a letter without a recipient")
    public void newEmailModalPageWithoutRecipientTest() throws InterruptedException{
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        newEmailModalPage
                .waitForHomePageLoaded()
                .openNewEmailModalPage()
                .creatAndSendNewEmailWithoutRecipient("Counter offer for mutual cooperation")
                .sendEmail();
        Assert.assertEquals(newEmailModalPage.getErrorText("Add"), "Add a recipient to send an email.");
    }

    @Test
    @Description("Trying to send a letter without a subject")
    public void newEmailModalPageWithoutSubjectTest() throws InterruptedException{
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        newEmailModalPage
                .waitForHomePageLoaded()
                .openNewEmailModalPage()
                .creatAndSendNewEmailWithoutSubject("shumans@mailinator.com")
                .sendEmail();
        Assert.assertEquals(newEmailModalPage.getErrorText("To send"), "To send this email, add a subject or body content.");
    }
}