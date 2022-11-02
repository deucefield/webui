package homeworks.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainApp {


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        auth();

        // ЭТО ЗАГЛУШКА
        // Прошу прощения, что вовремя не могу сдать работу — я вынужден отлучиться на несколько часов
        // А доступ к сдаче закроется в 20.00
        // Но работу я сдам сегодня. Просто физически не успеваю до 20.00 сдать :(
    }

    static WebDriver driver() {
        WebDriver chrome = new ChromeDriver();
        return chrome;
    }

    static void auth() {
        driver().get("https://diary.ru");
        driver().findElement(By.xpath("//a[@href='https://diary.ru/user/login']")).click();

    }
}
