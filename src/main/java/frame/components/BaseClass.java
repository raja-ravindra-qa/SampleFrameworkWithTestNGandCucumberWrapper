package frame.components;

import frame.pageobjects.WelcomePage;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass extends CommonUtilities {

    public WebDriver driver;
    public WelcomePage welcome;
   RemoteWebDriver rdriver;


    public WebDriver initializeDriver() throws IOException {
        // properties class

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//frame//resources//propertySetUp.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        //prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);


        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "/Users/rahulshetty//documents//geckodriver");
            driver = new FirefoxDriver();
            // Firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            // Edge
           WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--inprivate");
            driver = new EdgeDriver(options);
        }


        return driver;

    }

public String roboShot(String testCaseName) throws IOException, AWTException {

        // Create a Robot object to capture the screen
        Robot robot = new Robot();

        // Capture the screen or a specific region (e.g., the entire screen)
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);

        // Save the screenshot to a file
        String filePath=System.getProperty("user.dir") + "//src//output//images//" + testCaseName + ".png";
        File outputFile = new File(filePath);
        ImageIO.write(screenshot, "png", outputFile);

        System.out.println("Screenshot saved to " + outputFile.getAbsolutePath());

    return outputFile.getAbsolutePath();
}
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//src//output//images//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//src//output//images//" + testCaseName + ".png";


    }

    @BeforeMethod(alwaysRun = true)
    public WelcomePage launchApplication() throws IOException {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        welcome = new WelcomePage(driver);
        welcome.launching();
        System.out.println("Before class is executed");
        return welcome;

    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {
        driver.quit();
    }
}
