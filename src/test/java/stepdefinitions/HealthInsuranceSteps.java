package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HealthInsurancePage;
import utils.DriverFactory;

public class HealthInsuranceSteps {

    WebDriver driver;
    HealthInsurancePage healthPage;

    private static final Logger logger = LogManager.getLogger(HealthInsuranceSteps.class);

    public HealthInsuranceSteps() {
        this.driver = DriverFactory.getDriver();  // ✅ Use ThreadLocal driver
        this.healthPage = new HealthInsurancePage(driver);
    }

    @Then("I should be on the Health Insurance page")
    public void i_should_be_on_the_health_insurance_page() {
        logger.info("Verifying if user is on the Health Insurance page.");
        String url = driver.getCurrentUrl();
        logger.debug("Current URL: {}", url);
        Assert.assertTrue(url.contains("health-insurance"), "❌ Not navigated to health insurance page.");

        String actualTitle = healthPage.getPageTitle();
        logger.debug("Page title: {}", actualTitle);
        Assert.assertTrue(
            actualTitle.contains("Compare & buy customised Health Plans"),
            "❌ Expected page title not found."
        );
    }

    @When("I fill the form with name {string}, pincode {string}, number {string}, and captcha {string}")
    public void i_fill_the_form_with_data(String fullName, String pincode, String number, String captcha) {
        logger.info("Filling the health insurance form.");
        logger.debug("Full Name: {}, Pincode: {}, Number: {}, Captcha: {}", fullName, pincode, number, captcha);

        healthPage.enterFullName(fullName);
        healthPage.enterPincode(pincode);
        healthPage.enterContactNumber(number);
        healthPage.enterCaptcha(captcha);
        healthPage.clickGetQuotes();

        logger.info("Clicked 'Get Quotes' button.");
    }

    @Then("I should see the quote or confirmation")
    public void i_should_see_quote_or_confirmation() {
        logger.info("Checking for quote or confirmation.");
        // 🔧 You can add a proper wait/assertion here based on your app's behavior
        // e.g., Assert.assertTrue(healthPage.isQuoteDisplayed(), "❌ Quote not visible.");
        System.out.println("✅ Quote or confirmation expected — implement proper verification logic.");
    }
}
