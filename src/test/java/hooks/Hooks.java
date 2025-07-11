package hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;

public class Hooks {

    BaseTest baseTest = new BaseTest();

    @Before
    public void setUp() {
        baseTest.initialize();  // Internally sets ThreadLocal driver
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            if (scenario.isFailed()) {
                try {
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File src = ts.getScreenshotAs(OutputType.FILE);

                    String screenshotDir = "screenshots";
                    Files.createDirectories(Paths.get(screenshotDir));

                    File dest = new File(screenshotDir + "/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png");
                    FileUtils.copyFile(src, dest);

                    System.out.println("🖼 Screenshot captured: " + dest.getAbsolutePath());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            DriverFactory.quitDriver();  // ✅ Proper cleanup
        } else {
            System.out.println("⚠️ WebDriver is null — skipping screenshot and quit");
        }
    }
}
