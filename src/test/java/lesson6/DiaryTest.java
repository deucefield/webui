package lesson6;

import homeworks.lesson6.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
@Epic("Тесты Diary.ru")
public class DiaryTest {

    Cookie auth;
    WebDriver chrome;
    WebDriverWait chromeWait;
    static String title;

    @BeforeAll
    static void prepare() {
        WebDriverManager.chromedriver().setup();

        title = "testTitle" + new Random().nextInt(10);
    }

    @BeforeEach
    void driverCreate() {
        chrome = new ChromeDriver();
        chromeWait = new WebDriverWait(chrome, Duration.ofSeconds(3));
        auth = new Cookie("_identity_", "e1e7dd0986a9cd304010372af8d45c1f6e423dbc74f7f3f8a34576232e46bd47a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3574441%2C%22D1PtwtkJO0tXTwpwf2KDkqOn65hMGL27%22%2C2592000%5D%22%3B%7D");

        chrome.get("https://diary.ru");
        chrome.manage().addCookie(auth);
        chrome.navigate().refresh();
    }

    @Test
    @DisplayName("Добавление записи в дневник")
    @Severity(SeverityLevel.BLOCKER)
    void recordAdd() {
        String result = new RecordCreatePage(chrome)
                .goToRecordCreatePage()
                .textToTitle(title)
                .goToMessageIframe()
                .textToMessageInput("test")
                .goToParentFrame()
                .textToTheme("test")
                .clickCreate()
                .returnFirstRecordTitle();

        Assertions.assertEquals(title, result);
    }

    @Test
    @DisplayName("Удаление записи")
    @Severity(SeverityLevel.CRITICAL)
    void recordDel() {
        MyDiaryPage myDiaryPage = new MyDiaryPage(chrome, chromeWait);
        myDiaryPage.goToMyDiaryPage();
        List<WebElement> titlesFromPage = myDiaryPage.returnRecordsList();
        titlesFromPage.stream()
                .filter(p -> p.getText().equals(title))
                .findFirst()
                .get()
                .findElement(By.xpath("//a[@class='delPostLink']"))
                .click();

        myDiaryPage.clickRecordDeleteConfirm();

        chrome.navigate().refresh();
        Assertions.assertNotEquals(title, myDiaryPage.returnFirstRecordText());
    }

    @Test
    @DisplayName("Появление упоминания в журнале упоминаний")
    @Severity(SeverityLevel.MINOR)
    void notificationTest() {
        String result = new MentionsPage(chrome)
                .goToMentionsPage()
                .clickDeleteAllNotifications()
                .goToRecordCreatePage()
                .textToTitle("mentionTest")
                .goToMessageIframe()
                .textToMessageInput("[J]deucedeuce[/J]")
                .goToParentFrame()
                .clickCreate()
                .goToMentionsPage()
                .returnFirstMentionedDiaryName();

        Assertions.assertEquals("test", result);
    }

    @Test
    @DisplayName("Прибавление лайков")
    @Severity(SeverityLevel.MINOR)
    void likeTest() throws InterruptedException {
        ForLikeTestPage forLikeTestPage = new ForLikeTestPage(chrome, chromeWait);
        Integer beforeLike = forLikeTestPage
                .goToTestPage()
                .clickLike()
                .returnLikeCount();

        Integer afterLike = forLikeTestPage
                .clickLike()
                .returnLikeCount();

        Assertions.assertTrue(afterLike==beforeLike+1);
    }

    @AfterEach
    void tearDown() {
        chrome.quit();
    }

}