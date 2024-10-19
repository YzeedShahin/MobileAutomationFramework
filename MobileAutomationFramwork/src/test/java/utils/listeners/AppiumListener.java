package utils.listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

public class AppiumListener implements ITestListener {

    public static ExtentManager report;
    public static ExtentTest test;

    @Override
    public synchronized void onStart(ITestContext context) {
        report = new ExtentManager(test);
        try {
            report.initReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        report.closeReport();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        test = ExtentManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        report.logTestResults(result);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        report.logTestResults(result);
    }

    public void onTestFailure(ITestResult result) {
        String imagePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod() + ".png";
        String imageBase64 = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64);
        // Convert base64 to byte array
        byte[] screenshotBytes = Base64.getDecoder().decode(imageBase64);

        // Save the screenshot to a file
        File screenshotFile = new File(imagePath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(screenshotFile);
            fileOutputStream.write(screenshotBytes);
            fileOutputStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //log screenshot to report as base64 image
        report.logTestResults(result, imageBase64);
    }
}