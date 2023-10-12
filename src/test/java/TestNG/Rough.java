package TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Rough {
    public static void main(String[] arg) throws InterruptedException {
        WebDriverManager.chromiumdriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.close();
    }
}
