package hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
public class Hooks {

    BaseTest baseTest = new BaseTest();
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = baseTest.initialize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                // Take screenshot only if driver is initialized and test failed
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
                File destination = new File("screenshots/" + fileName);

                try {
                    FileUtils.copyFile(source, destination);
                    System.out.println("Screenshot saved: " + destination.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit(); // always close the browser
        } else {
            System.out.println("WebDriver was null in @After hook.");
        }
    }}
