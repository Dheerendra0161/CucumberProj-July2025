package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

    private static ExtentReports extentReport;

    public static ExtentReports generateExtentReport() {

        if (extentReport != null) return extentReport;

        extentReport = new ExtentReports();
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Extent Report - Cucumber Selenium Framework");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

        extentReport.attachReporter(sparkReporter);

        // Load info from config.properties
        try {
            Properties configProp = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                    + "/src/main/java/eCom/Config/config.properties");
            configProp.load(fis);

            extentReport.setSystemInfo("Application URL", configProp.getProperty("url", "N/A"));
            extentReport.setSystemInfo("Browser", configProp.getProperty("browserName", "N/A"));
            extentReport.setSystemInfo("Email", configProp.getProperty("validEmail", "N/A"));
            extentReport.setSystemInfo("Password", configProp.getProperty("validPassword", "N/A"));
        } catch (Exception e) {
            System.err.println("⚠️ Could not load config.properties: " + e.getMessage());
        }

        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("User", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
