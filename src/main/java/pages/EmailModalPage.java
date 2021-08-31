package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class EmailModalPage extends BasePage {

    public EmailModalPage(WebDriver driver) {
        super(driver);
    }

    private static final String EMAIL_ERROR_MASSAGE = "//*[@class = 'pageLevelErrors']//li[contains(., '%s')]";

    @FindBy(xpath = "//*[contains(@class, 'photoContainer')]")
    WebElement iconImage;

    @Step("Open new email modal page")
    public EmailModalPage openNewEmailModalPage(){
        new Button(driver).clickAndSelectGlobalActionButton();
        return this;
    }

    @Step("Specify the subject and try to send an email: {emailSubject}")
    public EmailModalPage creatAndSendNewEmailWithoutRecipient(String emailSubject){
        log.info(String.format("Specify the subject of the email: Subject email - %s", emailSubject));
        new Input(driver).writeEmailSubject(emailSubject);
        return this;
    }

    @Step("Specify the recipient and try to send the letter: {emailAddress}")
    public EmailModalPage creatAndSendNewEmailWithoutSubject(String emailAddress){
        log.info(String.format("Specify the recipient of the email: Recipient email - %s", emailAddress));
        new Input(driver).writeEmailAddress(emailAddress);
        return this;
    }

    public EmailModalPage waitForHomePageLoaded() {
        waitForPageOpened(iconImage, 20);
        return this;
    }

    @Step("Send email")
    public EmailModalPage sendEmail() {
        log.info("Click send email button");
        new Button(driver).clickSendEmailButton();
        return this;
    }

    public String getErrorText(String errorText) {
        return driver.findElement(By.xpath(String.format(EMAIL_ERROR_MASSAGE, errorText))).getText();
    }
}