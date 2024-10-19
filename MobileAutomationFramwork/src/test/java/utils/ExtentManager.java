package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentTest test;

    public ExtentManager(ExtentTest test){
        ExtentManager.test = test;
    }

    public void initReport() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "reportConfigs.json";
        final File CONF = new File(path);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "automation_report.html");
        htmlReporter.loadJSONConfig(CONF);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public void logTestResults(ITestResult result, String imageBase64){
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getThrowable());
            test.addScreenCaptureFromBase64String(imageBase64);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped");
        }
    }

    public void logTestResults(ITestResult result){
        logTestResults(result, "");
    }

    public void closeReport(){
        extent.flush();
    }

    public static ExtentTest createTest(String testName){
        return test = extent.createTest(testName);
    }
}
