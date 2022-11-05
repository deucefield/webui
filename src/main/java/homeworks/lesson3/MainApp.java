package homeworks.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class MainApp {


    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        Cookie auth = new Cookie("_identity_", "e1e7dd0986a9cd304010372af8d45c1f6e423dbc74f7f3f8a34576232e46bd47a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3574441%2C%22D1PtwtkJO0tXTwpwf2KDkqOn65hMGL27%22%2C2592000%5D%22%3B%7D");
        WebDriver chrome = new ChromeDriver();

        // Авторизация
        chrome.get("https://diary.ru");
        chrome.manage().addCookie(auth);
        chrome.navigate().refresh();

        // Создание записи
        chrome.findElement(By.id("writeThisDiary")).click();
        final String title = "testTitle" + new Random().nextInt(10);
        chrome.findElement(By.id("postTitle")).sendKeys(title);

        chrome.switchTo().frame(chrome.findElement(By.id("message_ifr")));
        chrome.findElement(By.id("tinymce")).sendKeys("testText");

        chrome.switchTo().parentFrame();
        chrome.findElement(By.id("tagform-tagcustom")).sendKeys("testTheme");
        chrome.findElement(By.id("rewrite")).click();

        // Удаление записи
        chrome.get("https://diary.ru");
        chrome.findElement(By.id("myCommunityLink")).click();
        List<WebElement> postTitles = chrome.findElements(By.xpath("//a[@class='title']"));
        postTitles.stream()
                .filter(p -> p.getText().equals(title))
                .findFirst()
                .get()
                .findElement(By.xpath("//a[@class='delPostLink']"))
                .click();

        new WebDriverWait(chrome, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='fade modal in']//button[@class='btn btn-primary on confirm_delete_post']")));
        chrome.findElement(By.xpath("//div[@class='fade modal in']//button[@class='btn btn-primary on confirm_delete_post']")).click();

        Thread.sleep(5000);
        chrome.quit();

    }

    static WebDriver driver() {
        WebDriver chrome = new ChromeDriver();
        return chrome;
    }
}
