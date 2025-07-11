package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    BaseTest baseTest = new BaseTest();
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = baseTest.initialize();
    }

    @After
    public void tearDown() {
        baseTest.tearDown();
    }
}
