package PageFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import java.math.BigDecimal;

public class CurrencyConverterPage {

	WebDriver driver;

	@FindBy(id = "convert")

	WebElement currencyConvertButton;

	@FindBy(id = "ConvertFrom")

	WebElement currencyConvertFromDropDown;

	@FindBy(id = "ConvertTo")

	WebElement currencyConvertToDropDown;

	@FindBy(id = "Amount")

	WebElement amountField;

	@FindBy(xpath = "/html/body/div/table[1]/tbody/tr/td/form/div[1]/em")

	WebElement resultsDisplayField;

	public CurrencyConverterPage(WebDriver driver) {
		this.driver = driver;
		// Create all WebElements
		PageFactory.initElements(driver, this);
	}

	public void calculateCurrencyConversion(String fromCurrency, String toCurrency, String amount) {
		switchToIframe();
		Select convertFrom = new Select(currencyConvertFromDropDown);
		Select convertTo = new Select(currencyConvertToDropDown);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		convertFrom.selectByVisibleText(fromCurrency);
		amountField.clear();
		amountField.sendKeys(amount);
		convertTo.selectByVisibleText(toCurrency);
		currencyConvertButton.click();
		verifyCurrencyConverted(fromCurrency, toCurrency, amount);
		switchToParentFrame();
	}

	public void verifyCurrencyConverted(String fromCurrency, String toCurrency, String amount) {

		String actualMsg;
		actualMsg = resultsDisplayField.getText();
		Assert.assertTrue(actualMsg.contains(fromCurrency),
				"In the Message display " + fromCurrency + " : is not present in the : " + actualMsg);
		Assert.assertTrue(actualMsg.contains(toCurrency),
				"In the Message display " + toCurrency + " : is not present in the : " + actualMsg);
		Assert.assertTrue(actualMsg.contains("Rates updated as of "),
				"In the Message display : New Zealand Dollar @ : is not present in the : " + actualMsg);
		
		switch (fromCurrency) {
		case "New Zealand Dollar":
			Assert.assertTrue(actualMsg.contains("Certain transactions are subject to a commission charge."),
					"In the Message display : Certain transactions are subject to a commission charge. : is not present in the : "
							+ actualMsg);
			break;
		case "United States Dollar":
		case "Pound Sterling":
			Assert.assertTrue(actualMsg.contains("if you are purchasing "),
					"In the Message display : if you are purchasing  : is not present in the : " + actualMsg);
			Assert.assertTrue(actualMsg.contains("for notes you wish to exchange"),
					"In the Message display : for notes you wish to exchange: is not present in the : " + actualMsg);
			Assert.assertTrue(actualMsg.contains("for cheques and travellers cheques received from overseas"),
					"In the Message display :for cheques and travellers cheques received from overseas: is not present in the : "
							+ actualMsg);
			Assert.assertTrue(actualMsg.contains("for telegraphic transfers received from overseas"),
					"In the Message display : for telegraphic transfers received from overseas : is not present in the : "
							+ actualMsg);
			Assert.assertTrue(actualMsg.contains("Some fees and charges may apply."),
					"In the Message display : Some fees and charges may apply. : is not present in the : " + actualMsg);
		}
	}
	// Get the User name from Home Page

	public void calculateCurrencyConversion_WithoutValidData(String fromCurrency, String toCurrency) {

		switchToIframe();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Select convertFrom = new Select(currencyConvertFromDropDown);
		Select convertTo = new Select(currencyConvertToDropDown);
		convertFrom.selectByVisibleText(fromCurrency);
		amountField.clear();
		convertTo.selectByVisibleText(toCurrency);
		currencyConvertButton.click();
		verifyWarningMessage("Please enter the amount you want to convert.",
				"/html/body/div/table[1]/tbody/tr/td/form/div/ul/li");
		switchToParentFrame();
	}

	public void switchToIframe() {
		driver.switchTo().frame("westpac-iframe");
	}

	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}
	// Get the User name from Home Page

	public void verifyWarningMessage(String expErrMessage, String xpathValue) {
		String actualMsg;
		actualMsg = driver.findElement(By.xpath(xpathValue)).getText();
		Assert.assertTrue(actualMsg.contentEquals(expErrMessage),
				"Error Message : " + expErrMessage + " is not displayed");
	}

}