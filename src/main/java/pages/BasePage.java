package pages;

import constants.IConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage implements IConstants {
    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void waitForPageOpened(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(element))));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}