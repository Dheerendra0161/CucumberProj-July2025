package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public static WebDriver driver;
    public static Properties prop;

    public WebDriver initialize() {
        // ✅ Load environment config
        String env = System.getProperty("env", "qa");
        prop = ConfigReader.loadProperties(env);

        // ✅ Get browser from system property or config file
        String browser = System.getProperty("browser");
        if (browser == null || browser.trim().isEmpty()) {
            browser = prop.getProperty("browser");
        }

        browser = browser.trim().toLowerCase();
        System.out.println("✅ Browser selected: " + browser);

        // ✅ Launch browser based on selection
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("❌ Unsupported browser: " + browser);
        }

        // ✅ Browser setup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

        System.out.println("✅ URL launched: " + prop.getProperty("url"));
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}
