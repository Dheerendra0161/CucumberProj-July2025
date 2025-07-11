package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.*;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String path = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
