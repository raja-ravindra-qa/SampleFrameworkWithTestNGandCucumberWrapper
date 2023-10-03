package frame.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends AbstractClass {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> getItemsList() {
        return itemsList;
    }

    public List<WebElement> getItemPriceList() {
        return itemPriceList;
    }

    @FindBy(xpath = "//div[@class='inventory_item']")
    List<WebElement> itemsList;



    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPriceList;

    @FindBy(css="a[class='shopping_cart_link']")
WebElement cartIcon;
    public CartPage navigateToCart() {
cartIcon.click();
return new CartPage(driver);


    }




}
