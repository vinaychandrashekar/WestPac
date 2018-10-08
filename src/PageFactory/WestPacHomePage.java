package PageFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class WestPacHomePage {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"ubermenu-section-1-8-ps\"]")

	WebElement fxTravelMigrantLink;

	@FindBy(xpath = "//*[@id=\"ubermenu-item-cta-currency-converter-ps\"]")

	WebElement currencyConverterButton;

	public WestPacHomePage(WebDriver driver) {

		this.driver = driver;

		// Create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Navigate to Currency Converter Page from Home Page

	public void navigateToCurrencyConverterPage() {

		fxTravelMigrantLink.click();
		currencyConverterButton.click();

	}

}