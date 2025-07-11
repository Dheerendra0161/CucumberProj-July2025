package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentReporter;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class CucumberTestListener implements ITestListener {

    private ExtentReports extentReport;
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        try {
            Desktop.getDesktop().browse(new File(System.getProperty("user.dir")
                    + "/test-output/ExtentReports/extentReport.html").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
