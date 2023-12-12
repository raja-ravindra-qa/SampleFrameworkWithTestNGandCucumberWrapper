package frame.components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import frame.resources.ExtentReportsProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Listeners_TestNG_FrameWork extends BaseClass implements ITestListener {
    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */

    WebDriver driver;
    ExtentTest test;
    ExtentReports ext=ExtentReportsProperties.reportsSetup();
    ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        test=ext.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);

    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
       extentTestThreadLocal.get().log(Status.PASS,result.getTestName());
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {

        extentTestThreadLocal.get().log(Status.FAIL, result.getThrowable());
//        String filePath= null;
//        try {
//            filePath = roboShot(result.getMethod().getMethodName());
//        } catch (IOException e) {
//
//
//            throw new RuntimeException(e);
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] byteString=null;
//        try {
//            byteString= Files.readAllBytes(new File(filePath).toPath());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String base64= Base64.getEncoder().encodeToString(byteString);
//        extentTestThreadLocal.get().addScreenCaptureFromBase64String(base64);
//        extentTestThreadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        String filePath = null;
        try {
           driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
            byte[] byteString=null;
        try {
            byteString= Files.readAllBytes(new File(filePath).toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64= Base64.getEncoder().encodeToString(byteString);
        extentTestThreadLocal.get().addScreenCaptureFromBase64String(base64);

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //Screenshot, Attach to report


    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    @Override
    public void onFinish(ITestContext context) {
            ext.flush();
    }
}
