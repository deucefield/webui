package homeworks.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyDiaryPage extends MainPageHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(xpath = "//a[@class='title']")
    private List<WebElement> recordsList;

    @FindBy(xpath = "//a[@class='delPostLink']")
    private WebElement recordDelete;

    @FindBy(xpath = "//div[@class='fade modal in']//button[@class='btn btn-primary on confirm_delete_post']")
    private WebElement recordDeleteConfirm;

    @FindBy(xpath = "//div[@class='day-header']/following-sibling::div[1]//a[@class='title']")
    private WebElement firstRecord;

    public MyDiaryPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver);
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> returnRecordsList() {
        return recordsList;
    }

    public MyDiaryPage clickRecordDelete() {
        recordDelete.click();
        return this;
    }

    public MyDiaryPage clickRecordDeleteConfirm() {
        driverWait.until(ExpectedConditions.elementToBeClickable(recordDeleteConfirm));
        recordDeleteConfirm.click();
        return this;
    }

    public String returnFirstRecordText() {
        return firstRecord.getText();
    }

}
