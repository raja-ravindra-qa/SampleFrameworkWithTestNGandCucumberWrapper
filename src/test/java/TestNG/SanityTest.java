package TestNG;

import frame.components.BaseClass;
import frame.pageobjects.CartPage;
import frame.pageobjects.LandingPage;
import frame.pageobjects.MenuBurgerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends BaseClass {
    @Test(testName = "Login Page", description="jksdfksfj dfjikdjf dfjdsf sdfjklsd")
    public void loginPage() {
        welcome.launching();
    }

    @Test
    public void logincheck() throws InterruptedException {
        welcome.launching();
        welcome.login("standard_user", "secret_sauce");
        welcome.navigateToMenu().logout();

    }

    public LandingPage loginCommon() throws InterruptedException {
        welcome.launching();
        LandingPage lp = welcome.login("standard_user", "secret_sauce");
        return lp;
    }

    @Test
    public void addTocart() throws InterruptedException {
        LandingPage lp = loginCommon();
        List<WebElement> x = lp.getItemsList();
        for (int i = 0; i < x.size(); i++) {
            if (i % 2 == 0) {
                x.get(i).findElement(By.xpath("//div[@class='pricebar']/preceding::button[contains(text(),'Add to cart')]")).click();
            }
        }
        CartPage cp = lp.navigateToCart();

        Thread.sleep(5000);
        cp.navigateToMenu().logout();

    }

    @Test
    public void allItemsPage() throws InterruptedException {
        LandingPage lp = loginCommon();
        List<WebElement> x = lp.getItemsList();
        for (int i = 0; i < x.size(); i++) {
            if (i % 2 == 0) {
                x.get(i).findElement(By.xpath("//div[@class='pricebar']/preceding::button[contains(text(),'Add to cart')]")).click();
            }
        }
        waitToSee();
        CartPage cp = lp.navigateToCart();
        waitToSee();
        cp.navigateToMenu().navigateToAllItems();
        waitToSee();
        cp.navigateToMenu().logout();


    }

    @Test
    public void navigatToabout() throws InterruptedException {
        LandingPage lp = loginCommon();
        MenuBurgerPage menu = lp.navigateToMenu();
        waitToSee();
        menu.navigateToabout();
        waitToSee();
        driver.navigate().back();
        MenuBurgerPage menu2 = lp.navigateToMenu();
        menu2.logout();

    }

    @Test
    public void resetAll() throws InterruptedException {
        LandingPage lp = loginCommon();
        List<WebElement> x = lp.getItemsList();
        for (int i = 0; i < x.size(); i++) {
            if (i % 2 == 0) {
                x.get(i).findElement(By.xpath("//div[@class='pricebar']/preceding::button[contains(text(),'Add to cart')]")).click();
            }
        }
        waitToSee();
        lp.navigateToMenu().navigateToresetAppState();
        driver.navigate().refresh();
        lp.navigateToMenu().logout();
    }

    @Test
    public void intentionalFail() throws InterruptedException {
        LandingPage lp = loginCommon();
        MenuBurgerPage menu = lp.navigateToMenu();
        lp.navigateToMenu().navigateToresetAppState();
        menu.navigateToabout();

    }
}
