package homeworks.lesson6;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForLikeTestPage extends MainPageHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    private String likeTestUrl = "https://diary-spirit.diary.ru/p220964138.htm";
    @FindBy(xpath = "//a[@data-blog-id=4733]")
    private WebElement postToLike;
    @FindBy(xpath = "//a[@class='lastLink post_likes']")
    private WebElement beforeLike;
    @FindBy(xpath = "//a[@style='font-weight:0;cursor:pointer']")
    private WebElement beforeLikeV2;
    @FindBy(xpath = "//a[@class='lastLink post_likes delLike']")
    private WebElement afterLike;
    @FindBy(xpath = "//span[@class='count_likes']")
    private WebElement likeCounter;

    public ForLikeTestPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver);
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public ForLikeTestPage goToTestPage() {
        driver.get(likeTestUrl);
        return this;
    }

    public ForLikeTestPage clickLike() throws InterruptedException {
        Thread.sleep(500);
        postToLike.click();

        /*try {
            if(beforeLike.isDisplayed()) {
                postToLike.click();
                driverWait.until(ExpectedConditions.visibilityOf(afterLike));
            }
        } catch (NoSuchElementException e) {
            e.getMessage();
            if(afterLike.isDisplayed()) {
                postToLike.click();
                driverWait.until(ExpectedConditions.visibilityOf(beforeLike));
            }
        }*/


        /*if(ExpectedConditions.visibilityOf(beforeLikeV1)) {
            postToLike.click();
            driverWait.until(ExpectedConditions.visibilityOf(afterLike));
        } else if(afterLike.isDisplayed()) {
            postToLike.click();
            driverWait.until(ExpectedConditions.visibilityOf(beforeLikeV2));
        }*/
        return this;
    }

    public Integer returnLikeCount() {
        return Integer.valueOf(likeCounter.getText());
    }

}
