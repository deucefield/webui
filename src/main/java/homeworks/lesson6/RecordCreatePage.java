package homeworks.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecordCreatePage extends MainPageHeader {

    private WebDriver driver;

    @FindBy(id = "postTitle")
    private WebElement title;

    @FindBy(id = "message_ifr")
    private WebElement messageIframe;

    @FindBy(id = "tinymce")
    private WebElement messageInput;

    @FindBy(id = "tagform-tagcustom")
    private WebElement theme;

    @FindBy(id = "rewrite")
    private WebElement create;

    @FindBy(xpath = "//div[@class='day-header']/following-sibling::div[1]//a[@class='title']")
    private WebElement firstRecord;

    public RecordCreatePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RecordCreatePage clickTitle() {
        title.click();
        return this;
    }

    public RecordCreatePage textToTitle(String str) {
        title.sendKeys(str);
        return this;
    }

    public RecordCreatePage goToMessageIframe() {
        driver.switchTo().frame(messageIframe);
        return this;
    }

    public RecordCreatePage goToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public RecordCreatePage clickMessageInput() {
        messageInput.click();
        return this;
    }

    public RecordCreatePage textToMessageInput(String str) {
        messageInput.sendKeys(str);
        return this;
    }

    public RecordCreatePage clickTheme() {
        theme.click();
        return this;
    }

    public RecordCreatePage textToTheme(String str) {
        theme.sendKeys(str);
        return this;
    }

    public RecordCreatePage clickCreate() {
        create.click();
        return this;
    }

    public String returnFirstRecordTitle() {
        return firstRecord.getText();
    }

}
