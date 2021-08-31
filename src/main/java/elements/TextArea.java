package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextArea {

    WebDriver driver;
    String label;

    private static final String TEXTAREA_XPATH = "//*[contains(text(),'%s')]/ancestor::div[contains(@class,";
    private static final String TEXTAREA_ACCOUNT_MODAL_XPATH = TEXTAREA_XPATH + "'uiInput')]/textarea";
    private static final String TEXTAREA_OPPORTUNITIES_MODAL_PAGE_XPATH = TEXTAREA_XPATH + "'label-stacked')]//textarea";

    public TextArea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    @Step("Filling in text information in the fields describing the terms of the transaction, " +
            "place of delivery, etc. {description}")
    public void writeTextDescription(String description) {
        driver.findElement(By.xpath(String.format(TEXTAREA_ACCOUNT_MODAL_XPATH, label))).sendKeys(description);
    }

    @Step("Describe the possibilities in the description field {text}")
    public void writeOpportunityTextDescription(String text) {
        driver.findElement(By.xpath(String.format(TEXTAREA_OPPORTUNITIES_MODAL_PAGE_XPATH, label))).sendKeys(text);
    }
}