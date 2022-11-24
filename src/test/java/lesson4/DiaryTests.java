package lesson4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DiaryTests {
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
    void recordAdd() {
        chrome.findElement(By.id("writeThisDiary")).click();
        chrome.findElement(By.id("postTitle")).sendKeys(title);

        chrome.switchTo().frame(chrome.findElement(By.id("message_ifr")));
        chrome.findElement(By.id("tinymce")).sendKeys("testText");

        chrome.switchTo().parentFrame();
        chrome.findElement(By.id("tagform-tagcustom")).sendKeys("testTheme");
        chrome.findElement(By.id("rewrite")).click();

        Assertions.assertEquals(title, chrome.findElement(By.xpath("//div[@class='day-header']/following-sibling::div[1]//a[@class='title']")).getText());
    }

    @Test
    void recordDel() {
        chrome.findElement(By.id("myCommunityLink")).click();
        List<WebElement> postTitles = chrome.findElements(By.xpath("//a[@class='title']"));
        postTitles.stream()
                .filter(p -> p.getText().equals(title))
                .findFirst()
                .get()
                .findElement(By.xpath("//a[@class='delPostLink']"))
                .click();

        chromeWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='fade modal in']//button[@class='btn btn-primary on confirm_delete_post']")));
        chrome.findElement(By.xpath("//div[@class='fade modal in']//button[@class='btn btn-primary on confirm_delete_post']")).click();

        chrome.navigate().refresh();
        Assertions.assertNotEquals(title, chrome.findElement(By.xpath("//div[@class='day-header']/following-sibling::div[1]//a[@class='title']")).getText());
    }

    @Test
    void notificationTest() {
        chrome.get("https://diary.ru/options/site/?reference");
        chrome.findElement(By.xpath("//button[@class='btn btn-default delAll']")).click();

        chrome.findElement(By.id("writeThisDiary")).click();
        chrome.findElement(By.id("postTitle")).sendKeys("testTitle");

        chrome.switchTo().frame(chrome.findElement(By.id("message_ifr")));
        chrome.findElement(By.id("tinymce")).sendKeys("[J]deucedeuce[/J]");

        chrome.switchTo().parentFrame();
        chrome.findElement(By.id("tagform-tagcustom")).sendKeys("testTheme");
        chrome.findElement(By.id("rewrite")).click();

        chrome.get("https://diary.ru/options/site/?reference");

        Assertions.assertEquals("test", chrome.findElement(By.xpath("//table[@class='table table-content options_site_reference']//tbody//a[.='test']")).getText());
    }

    @Test
    void likeTest() throws InterruptedException {
        int beforeLike;
        int afterLike;

        chrome.get("https://diary-spirit.diary.ru/p220964138.htm");

        beforeLike = Integer.valueOf(chrome.findElement(By.xpath("//span[@class='count_likes']")).getText());
        chrome.findElement(By.xpath("//span[@class='count_likes']")).click();
        // Ждём пока пройдёт анимация. Так как скорость её проигрывания фиксирована, насколько уместно использование слипа здесь?
        Thread.sleep(500);

        afterLike = Integer.valueOf(chrome.findElement(By.xpath("//span[@class='count_likes']")).getText());
        chrome.findElement(By.xpath("//span[@class='count_likes']")).click();                                  // Вот тут у меня очень важный вопрос, ответьте, пожалуйста на странице урока. Этот клик нужен для того чтобы вернуть лайки в исходное,
                                                                                                                            // то есть не нажатое состояние. Но в рамках теста делать выглядит как-то неправильно. Есть ли какой-то условный @AfterTest, который
        Assertions.assertTrue(afterLike==beforeLike+1);                                                             // выполняется после конкретного теста? Также интересно и про @BeforeTest
    }

    @AfterEach
    void tearDown() {
        chrome.quit();
    }
}
