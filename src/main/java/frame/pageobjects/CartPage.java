package frame.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractClass{
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

@FindBy(xpath = "//button[normalize-space()='Remove']")
    WebElement removeButton;

    @FindBy(xpath = "//button[contins(text(), 'Continue Shopping')]")
    WebElement backButton;

    @FindBy(xpath = "//button[contains(@class, 'checkout_button')]")
    WebElement checkOut;
}
