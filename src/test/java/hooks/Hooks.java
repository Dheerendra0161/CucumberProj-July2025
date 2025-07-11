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
import utils.ScreenshotUtil;

public class Hooks {

	BaseTest baseTest = new BaseTest();

	@Before
	public void setUp() {
		baseTest.initialize(); // Internally sets ThreadLocal driver
	}

	@After
	public void tearDown(Scenario scenario) {
		WebDriver driver = DriverFactory.getDriver();

		if (driver != null) {
			if (scenario.isFailed()) {
				// ✅ Use your utility method instead of repeating code
				String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
						scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
				System.out.println("🖼 Screenshot captured at: " + screenshotPath);
			}

			DriverFactory.quitDriver(); // ✅ Always close browser
		} else {
			System.out.println("⚠️ WebDriver is null — skipping screenshot and quit");
		}
	}
}
