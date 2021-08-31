package pages;

import elements.Button;
import elements.DropDown;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class NewTaskModalPage extends BasePage {

    public NewTaskModalPage(WebDriver driver) {
        super(driver);
    }

    private static final String TASK_SUBJECT = "//*[contains(@class,'column')]//descendant::span[contains(@class, 'uiOutputText')][contains(., '%s')]";
    private static final String TASK_DUE_DATE = "//*[contains(@class, 'slds-col')]//span[contains(@class, 'slds-grow')][contains(., '%s')]";
    private static final String TASK_REMINDER_SET = "//*[contains(@class, 'itemBody')]//span[contains(., '%s')]";

    @FindBy(xpath = "//*[contains(text(),'Additional Information')]")
    WebElement taskAdditionalInformation;

    @Step("Open new task modal page")
    public NewTaskModalPage openNewTaskPage() {
        log.info("Open new task modal page" + NEW_TASK_MODAL_PAGE);
        super.openPage(NEW_TASK_MODAL_PAGE);
        return this;
    }

    @Step("Create task without Reminder Set - Date & Time. " +
            "Fill in the fields with information: {subject}, {relatedTo}, {dueDate}.")
    public NewTaskModalPage createInformationIntoTaskNewPage(String subject, String relatedTo, String dueDate) {
        log.info(String.format("Select the subject of the task: %s in Subject", subject));
        new Input(driver, "Subject").writeTaskText(subject);
        log.info(String.format("Select which account the task refers to: Related To account %s", relatedTo));
        new Input(driver, "Related To").writeTaskText(relatedTo);
        log.info(String.format("Specify due date: %s in Due Date", dueDate));
        new Input(driver, "Due Date").writeTaskText(dueDate);
        log.info("Remove the ability to specify the date and time of the reminder - remove the checkbox");
        new Input(driver, "Reminder Set").clickReminderSet();
        return this;
    }

    @Step("Create task with Reminder Set - Date & Time.\n" +
            "Fill in the fields with information: {subject}, {relatedTo}, {dueDate}, {date}, {timeId}, {status}, {priority}.")
    public NewTaskModalPage createNewTaskWithDateAndTime(String subject, String relatedTo, String dueDate, String date,
                                                         String timeId, String status, String priority) {
        log.info(String.format("Select the subject of the task: %s in Subject", subject));
        new Input(driver, "Subject").writeTaskText(subject);
        log.info(String.format("Select which account the task refers to: Related To account %s", relatedTo));
        new Input(driver, "Related To").writeTaskText(relatedTo);
        log.info(String.format("Specify due date: %s in Due Date", dueDate));
        new Input(driver, "Due Date").writeTaskText(dueDate);
        log.info(String.format("Specify Reminder Date: %s in Reminder Date", date));
        new Input(driver, "Date").chooseDateSendDay(date);
        log.info(String.format("Specify Reminder Time: %s in Reminder Time", timeId));
        new DropDown(driver, "Time").selectTimeDropDown(timeId);
        log.info(String.format("Specify status: Status %s", status));
        new DropDown(driver, "Status").select(status);
        log.info(String.format("Specify priority: Priority %s", priority));
        new DropDown(driver, "Priority").select(priority);
        return this;
    }

    @Step("Save account")
    public NewTaskModalPage clickSaveNewTaskButton() {
        log.info("Clicked save new task button");
        new Button(driver).clickSaveButton();
        return this;
    }

    public NewTaskModalPage waitForTaskModalPageLoaded() {
        waitForPageOpened(taskAdditionalInformation, 30);
        return this;
    }

    public String getTaskSubject (String subject) {
        return driver.findElement(By.xpath(String.format(TASK_SUBJECT, subject))).getText();
    }

    public String getTaskDueDate (String dueDate) {
        return driver.findElement(By.xpath(String.format(TASK_DUE_DATE, dueDate))).getText();
    }

    public String getTaskReminderSet (String date) {
        return driver.findElement(By.xpath(String.format(TASK_REMINDER_SET, date))).getText();
    }
}