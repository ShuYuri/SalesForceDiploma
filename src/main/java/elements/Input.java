package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input {

    WebDriver driver;
    String label;

    private static final String COMMON_XPATH = "//*[contains(text(),'%s')]/ancestor::div[contains(@class,";
    private static final String INPUT_XPATH = COMMON_XPATH + "'uiInput')]//input";
    private static final String INPUT_FOR_TASK_XPATH = COMMON_XPATH + "'slds-form-element__control')]//input";
    private static final String INPUT_FOR_TASK_DATE_XPATH = COMMON_XPATH + "'dateTime')]//input";
    private static final String INPUT_FOR_OPPORTUNITIES_MODAL_PAGE_XPATH = COMMON_XPATH + "'label-stacked')]//input";
    private static final String INPUT_FOR_EMAIL_XPATH = "//*[@class = 'standardField']//input";
    private static final String INPUT_SUBJECT_EMAIL_XPATH = "//*[contains(@class,'uiInputText')]/input";

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public Input(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill in the information on account modal page: {text}")
    public void writeText(String text) {
        driver.findElement(By.xpath(String.format(INPUT_XPATH, label))).sendKeys(text);
    }

    @Step("Fill in the information on task modal page: {subject}")
    public void writeTaskText(String subject) {
        driver.findElement(By.xpath(String.format(INPUT_FOR_TASK_XPATH, label))).sendKeys(subject);
    }

    @Step("Click checkbox on task modal page")
    public void clickReminderSet() {
        driver.findElement(By.xpath(String.format(INPUT_FOR_TASK_XPATH, label))).click();
    }

    @Step("Specify reminder date {date} on task modal page")
    public void chooseDateSendDay(String date) {
        driver.findElement(By.xpath(String.format(INPUT_FOR_TASK_DATE_XPATH, label))).clear();
        driver.findElement(By.xpath(String.format(INPUT_FOR_TASK_DATE_XPATH, label))).sendKeys(date);
    }

    @Step("Fill in the information on opportunity modal page: {text}")
    public void specifyOpportunity(String text) {
        driver.findElement(By.xpath(String.format(INPUT_FOR_OPPORTUNITIES_MODAL_PAGE_XPATH, label))).sendKeys(text);
    }

    @Step("Specify interest rate on opportunity modal page: {probability}")
    public void specifyProbability(String probability) {
        driver.findElement(By.xpath(String.format(INPUT_FOR_OPPORTUNITIES_MODAL_PAGE_XPATH, label))).clear();
        driver.findElement(By.xpath(String.format(INPUT_FOR_OPPORTUNITIES_MODAL_PAGE_XPATH, label))).sendKeys(probability);
    }

    @Step("Specify email address {emailAddress} on email modal page")
    public void writeEmailAddress(String emailAddress) {
        driver.findElement(By.xpath(INPUT_FOR_EMAIL_XPATH)).sendKeys(emailAddress);
    }

    @Step("Specify email subject {emailSubject} on email modal page")
    public void writeEmailSubject(String emailSubject) {
        driver.findElement(By.xpath(INPUT_SUBJECT_EMAIL_XPATH)).sendKeys(emailSubject);
    }
}