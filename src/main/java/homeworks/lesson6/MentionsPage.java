package homeworks.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MentionsPage extends MainPageHeader {

    WebDriver driver;

    @FindBy(xpath = "//button[@class='btn btn-default delAll']")
    private WebElement deleteAll;

    @FindBy(xpath = "//table[@class='table table-content options_site_reference']//tbody//a[.='test']")
    private WebElement firstMentionedDiary;

    public MentionsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MentionsPage clickDeleteAllNotifications() {
        deleteAll.click();
        return this;
    }

    public String returnFirstMentionedDiaryName() {
        return firstMentionedDiary.getText();
    }

}
