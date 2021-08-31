package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class NewOpportunityModalPageTest extends BaseTest {

    @Test
    @Description("Submit incomplete information about the opportunity." +
            "Post information about opportunities")
    public void newIncompleteOpportunityModalPageTest() throws InterruptedException {
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        newOpportunityModalPage
                .openNewOpportunityModalPage()
                .waitForOpportunityModalPageLoaded()
                .createNewIncompleteOpportunity("9/21/2021", "Как успеть запрыгнуть в последний вагон",
                        "20", "15200", "Давайте жить дружно! - Let live in peace!")
                .saveOpportunity();
        Assert.assertEquals(newOpportunityModalPage.getErrorMassage(), "We hit a snag.");
    }

    @Test
    @Description("Post information about opportunities")
    public void newCompleteOpportunityModalPageTest() throws InterruptedException {
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        newOpportunityModalPage
                .openNewOpportunityModalPage()
                .waitForOpportunityModalPageLoaded()
                .createNewCompleteOpportunity("9/21/2021", "Как успеть запрыгнуть в последний вагон",
                        "Qualification", "Privet trabla", "35", "New Business", "11200",
                        "External Referral", "You will learn how to earn several times more",
                        "Давайте жить дружно! - Let live in peace!")
                .saveOpportunity();
        Assert.assertEquals(newOpportunityModalPage.getOpportunityName("Как"), "Как успеть запрыгнуть в последний вагон");
        Assert.assertEquals(newOpportunityModalPage.getOpportunityStage("Qual"), "Qualification");
        Assert.assertEquals(newOpportunityModalPage.getCloseDate("9/21"), "9/21/2021");
        Assert.assertEquals(newOpportunityModalPage.getOpportunityType("New"), "New Business");
        Assert.assertEquals(newOpportunityModalPage.getOpportunityAmount("11"), "$11,200.00");
        Assert.assertEquals(newOpportunityModalPage.getOpportunityLeadSource("Referral"), "External Referral");
        Assert.assertEquals(newOpportunityModalPage.getOpportunityNextStep("дружно"), "Давайте жить дружно! - Let live in peace!");
    }
}