package Cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/Cucumber/features",
        glue = {"Cucumber.stepdef"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true,
        publish = true)

    public class loginRunner extends AbstractTestNGCucumberTests {
    public loginRunner() {
        super();
    }

//    @BeforeClass(alwaysRun = true)
//    @Override
//    public void setUpClass(ITestContext context) {
//        super.setUpClass(context);
//    }
//
//    @Override
//    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
//        super.runScenario(pickleWrapper, featureWrapper);
//    }

    /**
     * Returns two dimensional array of {@link PickleWrapper}s with their
     * associated {@link FeatureWrapper}s.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

//    @AfterClass(alwaysRun = true)
//    @Override
//    public void tearDownClass() {
//        super.tearDownClass();
//    }
}
