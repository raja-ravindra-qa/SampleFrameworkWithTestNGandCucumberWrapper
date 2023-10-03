package frame.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuBurgerPage extends AbstractClass {
    WebDriver driver;

    public MenuBurgerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'All Items')]")
    WebElement allItems;

    @FindBy(xpath = "//a[contains(text(), 'About')]")
    WebElement about;



    @FindBy(xpath = "//a[contains(text(), 'Reset App State')]")
    WebElement resetAppState;

    public void navigateToAllItems(){
        allItems.click();
    }
    public void navigateToabout(){
        about.click();
    }

    public void navigateTologout(){
        logout.click();
    }

    public void navigateToresetAppState(){
        resetAppState.click();
    }


}
