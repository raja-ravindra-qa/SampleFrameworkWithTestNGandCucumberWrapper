package Cucumber.stepdef;

import frame.components.BaseClass;
import frame.pageobjects.LandingPage;
import frame.pageobjects.MenuBurgerPage;
import frame.pageobjects.WelcomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

public class MyStepdef {


    WebDriver driver;
    public WelcomePage welcome;
    BaseClass bs = new BaseClass();



    @Before(order = 1)
    public void initDriver() throws IOException {
        System.out.println("b4-- order1");

        driver = bs.initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        welcome = new WelcomePage(driver);
        welcome.launching();
        System.out.println("Before class is executed");

    }

    @Before(order = 2)
    public void initDriver2() throws IOException {
        System.out.println("b4-- order2");

    }


    @After(order = 1)
    public void tearOff1() throws IOException {
        System.out.println(" after order--1");
        bs.tearDown();
    }

    @After(order = 2)
    public void tearOff(Scenario scenario) throws IOException, AWTException {
        System.out.println("after order--2");

        boolean s = scenario.isFailed();
        if (s) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] source = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(source, "image/png", "Screenshot2" + scenario.getId());

                System.out.println("driver is null");
                System.out.println("Failed");


                String shotLocation = bs.roboShot(scenario.getName());
                byte[] byteString = null;
                try {
                    byteString = Files.readAllBytes(new File(shotLocation).toPath());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String base64 = Base64.getEncoder().encodeToString(byteString);
                scenario.attach(byteString, "image/png", "Screenshot1_ " + scenario.getName());




//                System.out.println("driver is not null");
//                TakesScreenshot ts = (TakesScreenshot) driver;
//                byte[] source = ts.getScreenshotAs(OutputType.BYTES);
//                scenario.attach(source, "image/png", "Screenshot2" + scenario.getId());

        }

    }

    @Given("^login with the (.+) and (.+)$")
    public void login_with_the_and(String id, String password) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(id + " " + password);

    }


    @When("Click on login button")
    public void click_on_login_button() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^The (.+) will be displayed$")
    public void the_will_be_displayed(String message) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("verify failure")
    public void verify_failure() {
        // Write code here that turns the phrase above into concrete actions
        LandingPage lp = welcome.login("standard_user", "secret_sauce");
        MenuBurgerPage menu = lp.navigateToMenu();
        lp.navigateToMenu().navigateToresetAppState();
        menu.navigateToabout();

    }
}
