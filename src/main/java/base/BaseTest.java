package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DriverFactory;

public class BaseTest {

    public static Properties prop;

    // ✅ Initialize driver using ThreadLocal
    public WebDriver initialize() {
        // ✅ Load environment-specific properties
        String env = System.getProperty("env", "qa");
        prop = ConfigReader.loadProperties(env);

        // ✅ Get browser type from system or config
        String browser = System.getProperty("browser");
        if (browser == null || browser.trim().isEmpty()) {
            browser = prop.getProperty("browser");
        }

        browser = browser.trim().toLowerCase();
        System.out.println("✅ Browser selected: " + browser);

        WebDriver driverInstance;

        // ✅ Launch driver based on browser type
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driverInstance = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("❌ Unsupported browser: " + browser);
        }

        // ✅ Assign to ThreadLocal Driver
        DriverFactory.setDriver(driverInstance);

        // ✅ Driver setup
        driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverInstance.manage().window().maximize();
        driverInstance.get(prop.getProperty("url"));

        System.out.println("✅ URL launched: " + prop.getProperty("url"));
        return driverInstance;
    }

    // ✅ Clean up the driver after test
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
