package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HealthInsurancePage;

public class HealthInsuranceSteps {

    WebDriver driver = BaseTest.driver;
    HealthInsurancePage healthPage = new HealthInsurancePage(driver);

    private static final Logger logger = LogManager.getLogger(HealthInsuranceSteps.class);

    @Then("I should be on the Health Insurance page")
    public void i_should_be_on_the_health_insurance_page() {
        logger.info("Verifying if user is on the Health Insurance page.");
        String url = driver.getCurrentUrl();
        logger.debug("Current URL: " + url);
        Assert.assertTrue(url.contains("health-insurance"), "Not navigated to health insurance page.");

        boolean titlePresent = healthPage.getPageTitle("Compare & buy customised Health Plans");
        logger.debug("Page title verification result: " + titlePresent);
        Assert.assertTrue(titlePresent, "Expected page title not found on the page.");
    }

    @When("I fill the form with name {string}, pincode {string}, number {string}, and captcha {string}")
    public void i_fill_the_form_with_data(String fullName, String pincode, String number, String captcha) {
        logger.info("Filling the health insurance form with test data.");
        logger.debug("Full Name: " + fullName + ", Pincode: " + pincode + ", Number: " + number + ", Captcha: " + captcha);

        healthPage.enterFullName(fullName);
        healthPage.enterPincode(pincode);
        healthPage.enterContactNumber(number);
        healthPage.enterCaptcha(captcha);
        healthPage.clickGetQuotes();

        logger.info("Clicked on 'Get Quotes' button.");
    }

    @Then("I should see the quote or confirmation")
    public void i_should_see_quote_or_confirmation() {
        logger.info("Expecting to see quote/confirmation on the Health Insurance page.");
        System.out.println("Quote/confirmation is expected – add actual assertion based on UI.");
        // Add actual assertion if any confirmation message or element is visible
    }
}
