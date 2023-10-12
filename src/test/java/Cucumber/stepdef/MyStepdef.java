package Cucumber.stepdef;

import frame.components.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class MyStepdef extends BaseClass {
WebDriver driver;
    @Before
    public void initDriver() throws IOException {
        launchApplication();
    }

    @After
    @Before
    public void tearOff() throws IOException {
        tearDown();
    }
    @Given("^login with the (.+) and (.+)$")
    public void login_with_the_and(String id, String password) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(id+ " "+password);

    }


    @When("Click on login button")
    public void click_on_login_button() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^The (.+) will be displayed$")
    public void the_will_be_displayed(String message) {
        // Write code here that turns the phrase above into concrete actions

    }
}
