package frame.pageobjects;

import frame.components.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractClass extends CommonUtilities {
    WebDriver driver;

    public AbstractClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBurger;
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement logout;

    public void logout() {
        logout.click();
    }
    public MenuBurgerPage navigateToMenu(){
        menuBurger.click();
        return new MenuBurgerPage(driver);
    }
}
