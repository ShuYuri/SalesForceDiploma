package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DeleteAccountModalPage extends BasePage {

    public DeleteAccountModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Confirm account delete on modal page")
    public DeleteAccountModalPage clickDeleteAccountOnModalPage() {
        log.info("Confirm account delete on modal page");
        new Button(driver).clickDeleteButton();
        return this;
    }
}
