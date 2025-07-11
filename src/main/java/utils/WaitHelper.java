package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 1. Wait until element is visible
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 2. Wait until element is clickable
    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 3. Wait until element disappears (not visible)
    public void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // 4. Wait for page title
    public void waitForTitleContains(String partialTitle) {
        wait.until(ExpectedConditions.titleContains(partialTitle));
    }

    // 5. Wait for URL to contain
    public void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    // 6. Wait and Click (Safe click)
    public void clickWhenReady(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    // 7. Wait and Send Keys
    public void typeWhenVisible(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // 8. Fluent Wait (advanced use case)
    public Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

	
}
