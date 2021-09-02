package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By LOGIN_BUTTON = By.id("Login");
    private static final By ERROR_TEXT = By.id("error");
    private static final By LOGO_ICON = By.xpath("//*[contains(@class, 'photoContainer')]");
    private static final By LOGO_NAME = By.xpath("//*[@class = 'profile-card-name']/descendant::a[contains(@class,'profile-link-label')]");

    @FindBy(xpath = "//*[contains(@class, 'photoContainer')]")
    WebElement iconImage;

//    @FindBy(xpath = "//*[contains(@class, 'density')][contains(text(),'Display Density')]")
//    WebElement iconLogoModalPage;

    @FindBy(id = "error")
    WebElement errorText;

    public LoginPage loginPage() {
        log.info("Open login page" + LOGIN_URL_PAGE);
        super.openPage(LOGIN_URL_PAGE);
        return this;
    }

    @Step("The user is logged into the account. Send {username} & {password} for login.")
    public void login(String username, String password) {
        log.info(String.format("Fill in username: %s in Login field", username));
        driver.findElement(By.id("username")).sendKeys(username);
        log.info(String.format("Fill in password: %s in password field", password));
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        new HomePage(driver);
    }

    public LoginPage waitForHomePageLoaded() {
        waitForPageOpened(iconImage, 30);
        return this;
    }

//    public LoginPage waitForLogoModalPageLoaded() throws InterruptedException {
//        Thread.sleep(2000);
//        waitForPageOpened(iconLogoModalPage, 30);
//        return this;
//    }

    public LoginPage waitForErrorTextMessage() {
        waitForPageOpened(errorText, 30);
        return this;
    }

    public String getErrorText() {
        return driver.findElement(ERROR_TEXT).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public LoginPage clickLogoName() {
        driver.findElement(LOGO_ICON).click();
        return this;
    }

    public String getLogoName() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(LOGO_NAME).getText();
    }
}