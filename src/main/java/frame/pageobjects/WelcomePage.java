package frame.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends AbstractClass {
    WebDriver driver;

    public WelcomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement userId;
    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(name = "login-button")
    WebElement loginButton;

    public void launching() {
        driver.get("https://www.saucedemo.com/");
    }

    public LandingPage login(String id, String password){
        userId.sendKeys(id);
        passwordField.sendKeys(password);
        loginButton.click();

        return new LandingPage(driver);
    }
}
