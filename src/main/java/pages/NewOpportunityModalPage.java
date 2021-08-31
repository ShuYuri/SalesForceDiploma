package pages;

import elements.Button;
import elements.DropDown;
import elements.Input;
import elements.TextArea;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class NewOpportunityModalPage extends BasePage {

    public NewOpportunityModalPage(WebDriver driver) {
        super(driver);
    }

    private static final By ERROR_MASSAGE = By.xpath("//*[@class = 'container']//h2[contains(., 'We hit a snag.')]");
    private static final String COMMON_ELEMENT = "//*[@id='brandBand_2']";
    private static final String GENERAL_LOCATOR_FOR_MANY_FIELDS = COMMON_ELEMENT + "//descendant::slot[contains(@name, 'outputField')]/descendant::slot[contains(.,'%s')]";

    @FindBy(xpath = "//*[contains(text(),'Additional Information')]")
    WebElement taskAdditionalInformation;

    @Step("Open new opportunity modal page")
    public NewOpportunityModalPage openNewOpportunityModalPage() {
        log.info("Open new Opportunity modal page" + NEW_OPPORTUNITIES_PAGE);
        super.openPage(NEW_OPPORTUNITIES_PAGE);
        return this;
    }

    @Step("Indicate incorrect information on the opportunity form: {closeDate}, {opportunityName}, " +
            "{probability}, {amount}, {nextStep}")
    public NewOpportunityModalPage createNewIncompleteOpportunity(String closeDate, String opportunityName, String probability,
                                                                  String amount, String nextStep) {
        log.info(String.format("Select close date: close date is %s", closeDate));
        new Input(driver, "Close Date").specifyOpportunity(closeDate);
        log.info(String.format("Specify the name of the opportunity: Opportunity name is %s", opportunityName));
        new Input(driver, "Opportunity Name").specifyOpportunity(opportunityName);
        log.info(String.format("Specify interest rate: Rate is %s", probability));
        new Input(driver, "Probability (%)").specifyProbability(probability);
        log.info(String.format("Specify amount: Amount is %s", amount));
        new Input(driver, "Amount").specifyOpportunity(amount);
        log.info(String.format("Choose the next step to development: Next Step is %s", nextStep));
        new Input(driver, "Next Step").specifyOpportunity(nextStep);
        return this;
    }

    @Step("Indicate correct information on the opportunity form: {closeDate}, {opportunityName}, {stage}, " +
            "{accountName}, {probability}, {type}, {amount}, {leadSource}, {description}, {nextStep}")
    public NewOpportunityModalPage createNewCompleteOpportunity(String closeDate, String opportunityName, String stage,
                                                                String accountName, String probability, String type, String amount,
                                                                String leadSource, String description, String nextStep) {
        log.info(String.format("Select close date: close date is %s", closeDate));
        new Input(driver, "Close Date").specifyOpportunity(closeDate);
        log.info(String.format("Specify the name of the opportunity: Opportunity name is %s", opportunityName));
        new Input(driver, "Opportunity Name").specifyOpportunity(opportunityName);
        log.info(String.format("Select stage: %s stage", stage));
        new DropDown(driver, "Stage").selectOpportunityOption(stage);
        log.info(String.format("Select linked account: Linked account is %s", accountName));
        new DropDown(driver, "Account Name").selectOpportunityOption(accountName);
        log.info(String.format("Specify interest rate: Rate is %s", probability));
        new Input(driver, "Probability (%)").specifyProbability(probability);
        log.info(String.format("Indicate your interest / type: Type is %s", type));
        new DropDown(driver, "Type").selectOpportunityOption(type);
        log.info(String.format("Specify amount: Amount is %s", amount));
        new Input(driver, "Amount").specifyOpportunity(amount);
        log.info(String.format("Specify source of information: Lead source is %s", leadSource));
        new DropDown(driver, "Lead Source").selectOpportunityOption(leadSource);
        log.info(String.format("Brief description of the possibilities: %s - in Description", description));
        new TextArea(driver, "Description").writeOpportunityTextDescription(description);
        log.info(String.format("Choose the next step to development: Next Step is %s", nextStep));
        new Input(driver, "Next Step").specifyOpportunity(nextStep);
        return this;
    }

    @Step("Save Opportunity")
    public NewOpportunityModalPage saveOpportunity() {
        log.info("Clicked the save account button");
        new Button(driver).clickSaveOpportunityButton();
        return this;
    }

    public NewOpportunityModalPage waitForOpportunityModalPageLoaded() {
        waitForPageOpened(taskAdditionalInformation, 60);
        return this;
    }

    public String getErrorMassage() {
        return driver.findElement(ERROR_MASSAGE).getText();
    }

    public String getOpportunityName(String opportunityName) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, opportunityName))).getText();
    }

    public String getOpportunityStage(String opportunityStage) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, opportunityStage))).getText();
    }

    public String getCloseDate(String closeDate) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, closeDate))).getText();
    }

    public String getOpportunityType(String type) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, type))).getText();
    }

    public String getOpportunityAmount(String amount) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, amount))).getText();
    }

    public String getOpportunityLeadSource(String leadSource) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, leadSource))).getText();
    }

    public String getOpportunityNextStep(String nextStep) {
        return driver.findElement(By.xpath(String.format(GENERAL_LOCATOR_FOR_MANY_FIELDS, nextStep))).getText();
    }
}