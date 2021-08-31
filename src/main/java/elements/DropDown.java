package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropDown {
    WebDriver driver;
    String label;

    private static final String COMMON_DROPDOWN = "//*[contains(text(),'%s')]/ancestor::div[contains(@class,";
    private static final String DROPDOWN_XPATH = COMMON_DROPDOWN + "'uiInputSelect')]//a";
    private static final String SELECT_OPTION_XPATH = "//*[contains(@class,'select-options')]/descendant::a[contains(text(),'%s')]";
    private static final String TIME_DROPDOWN_XPATH = COMMON_DROPDOWN + "'dateTime')]//input";
    private static final String SELECT_TIME_DROPDOWN_XPATH = "//*[contains(@class, 'uiInputTimePicker')]//li[contains(@id, '%s')]";
    private static final String SELECT_DROPDOWN_OPPORTUNITY_XPATH = "//*[contains(text(),'%s')]/ancestor::div[contains(@class, 'label-stacked')]//input";
    private static final String SELECT_OPPORTUNITY_OPTION_XPATH = "//*[@class = 'slds-truncate'][contains(., '%s')]";

    public DropDown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    @Step("Select option {option}")
    public void select(String option) {
        driver.findElement(By.xpath(String.format(DROPDOWN_XPATH, label))).click();
        driver.findElement(By.xpath(String.format(SELECT_OPTION_XPATH, option))).click();
    }

    @Step("Select time {timeId}")
    public void selectTimeDropDown(String timeId) {
        driver.findElement(By.xpath(String.format(TIME_DROPDOWN_XPATH, label))).click();
        driver.findElement(By.xpath(String.format(SELECT_TIME_DROPDOWN_XPATH, timeId))).click();
    }

    @Step("Select opportunity option: {stage}")
    public void selectOpportunityOption(String stage) {
        driver.findElement(By.xpath(String.format(SELECT_DROPDOWN_OPPORTUNITY_XPATH, label))).click();
        driver.findElement(By.xpath(String.format(SELECT_OPPORTUNITY_OPTION_XPATH, stage))).click();
    }
}