package frame.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsProperties {
    public static ExtentReports reportsSetup(){
        String path= System.getProperty("user.dir")+"/output/Reports/TestReports.html";
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Sample Project With Selenium, TestNG and Cucumber Wrapper ");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports reports=new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Automation Test Engineer :", "Raveendra Nath R");
        return reports;

    }
}
