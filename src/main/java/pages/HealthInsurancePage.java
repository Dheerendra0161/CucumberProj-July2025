package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

public class HealthInsurancePage {
	WebDriver driver;
	WaitHelper wait;

	public HealthInsurancePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
	}

	@FindBy(xpath = "//h1[@class='page-title']")
	WebElement textOnPage;

	@FindBy(id = "fullName")
	WebElement fullName;

	@FindBy(xpath = "//input[@placeholder='Enter Pincode']")
	WebElement pincode;

	@FindBy(id = "contactNumber")
	WebElement contactNumber;

	@FindBy(id = "captcha")
	WebElement captcha;

	@FindBy(xpath = "//div[@class='row mx-0 mb-3']")
	WebElement getQuotesBtn;

	public String getPageTitle() {
		return textOnPage.getText();
	}


	public void enterFullName(String name) {
		wait.typeWhenVisible(fullName, name);
	}

	public void enterPincode(String pin) {
		wait.typeWhenVisible(pincode, pin);
	}

	public void enterContactNumber(String number) {
		wait.typeWhenVisible(contactNumber, number);
	}

	public void enterCaptcha(String text) {
		wait.typeWhenVisible(captcha, text);
	}

	public void clickGetQuotes() {
		wait.clickWhenReady(getQuotesBtn);
	}

}
