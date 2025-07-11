package stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HealthInsurancePage;
import pages.HomePage;

public class HomePageSteps extends BaseTest {

    WebDriver driver;
    HomePage homePage;
    HealthInsurancePage healthInsurancePage;

    // Proper Log4j2 logger
    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);

    @Given("I launch the browser")
    public void i_launch_the_browser() {
        logger.info("Launching the browser...");
        driver = initialize();
        logger.debug("WebDriver instance created: " + driver);
    }

    @When("I open the RenewBuy home page")
    public void i_open_the_renew_buy_home_page() {
        logger.info("Opening RenewBuy homepage...");
        homePage = new HomePage(driver);
        logger.debug("HomePage object initialized.");
    }

    @When("I click on the Health Insurance link")
    public void i_click_on_health_insurance_link() {
        logger.info("Clicking on Health Insurance link...");
        healthInsurancePage = homePage.clickHealthInsurance();
        logger.debug("Navigated to HealthInsurancePage.");
    }
}
