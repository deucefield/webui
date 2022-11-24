package homeworks.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(xpath = "//span[@title='Главная']")
    private WebElement mainPage;

    @FindBy(id = "writeThisDiary")
    private WebElement recordCreate;

    @FindBy(id = "myCommunityLink")
    private WebElement myDiary;

    @FindBy(xpath = "//span[.='Упоминания']")
    private WebElement mentions;

    public MainPageHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPageHeader goToMainPage() {
        mainPage.click();
        return this;
    }

    public RecordCreatePage goToRecordCreatePage() {
        recordCreate.click();
        return new RecordCreatePage(driver);
    }

    public MyDiaryPage goToMyDiaryPage() {
        myDiary.click();
        return new MyDiaryPage(driver, driverWait);
    }

    public MentionsPage goToMentionsPage() {
        mentions.click();
        return new MentionsPage(driver);
    }

}
