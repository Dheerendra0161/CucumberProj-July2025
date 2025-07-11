package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

public class HomePage {
    WebDriver driver;
    WaitHelper wait;

    @FindBy(xpath = "//div[@class='col-auto d-none d-lg-block']//img[@alt='Frame 1171275512-3.svg']")
    WebElement healthInsuranceImage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WaitHelper(driver);
    }

    public HealthInsurancePage clickHealthInsurance() {
        wait.clickWhenReady(healthInsuranceImage);
        return new HealthInsurancePage(driver);
    }
}
