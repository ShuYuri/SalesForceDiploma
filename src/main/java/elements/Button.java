package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button {

    WebDriver driver;

    private static final String DELETE_BUTTON_XPATH = "//*[contains(@class, 'forceModalActionContainer')]//span[contains(., 'Delete')]";
    private static final String SAVE_BUTTON_XPATH = "//*[contains(@class,'button-container-inner')]/descendant::span[text() ='Save']";
    private static final String SAVE_OPPORTUNITY_BUTTON_XPATH = "//*[contains(@class, 'buttons')]//button[text() ='Save']";
    private static final String GLOBAL_ACTIONS_BUTTON_XPATH = "//*[contains(@class, 'slds-global-header')]/descendant::div[contains(@class, 'headerTrigger')]//*[contains(@class, 'slds-icon_x-small')]";
    private static final String SELECT_GLOBAL_ACTIONS_BUTTON_XPATH = "//*[contains(@class, 'globalCreateMenuList popupTargetContainer')]//span[contains(., 'Email')]";
    private static final String SEND_EMAIL_BUTTON_XPATH = "//*[contains(@class, 'bottomBarRight')]/descendant::span[contains(., 'Send')]";

    public Button(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click delete button")
    public void clickDeleteButton() {
        driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
    }

    @Step("Click save button")
    public void clickSaveButton() {
        driver.findElement(By.xpath(SAVE_BUTTON_XPATH)).click();
    }

    @Step("Click save opportunity button")
    public void clickSaveOpportunityButton() {
        driver.findElement(By.xpath(SAVE_OPPORTUNITY_BUTTON_XPATH)).click();
    }

    @Step("Click & select global action")
    public void clickAndSelectGlobalActionButton(){
        driver.findElement(By.xpath(GLOBAL_ACTIONS_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(SELECT_GLOBAL_ACTIONS_BUTTON_XPATH)).click();
    }

    @Step("Click send email button")
    public void clickSendEmailButton() {
        driver.findElement(By.xpath(SEND_EMAIL_BUTTON_XPATH)).click();
    }
}