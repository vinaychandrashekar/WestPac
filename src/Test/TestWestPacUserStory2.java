package Test;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import PageFactory.CurrencyConverterPage;
import PageFactory.WestPacHomePage;

/**
 * Created by Vijay on 8/10/2018. Test for WestPac User Story 2 As a Product
 * Owner want the feature to Convert Currency A to a Currency B and vice versa
 * with the currency converter So that The user gets an indication of the
 * exchange rates and makes an informed decision on their foreign transactions
 */
public class TestWestPacUserStory2 {

	WebDriver driver;
	String westPacURL = "http://www.westpac.co.nz";
	WestPacHomePage objHomePage;
	CurrencyConverterPage objCurrencyConvPage;

	@BeforeTest

	public void setup() {

		System.setProperty("webdriver.gecko.driver",
				"C:\\WestPacAssignment\\selenium-java-3.6.0\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(westPacURL);
		objHomePage = new WestPacHomePage(driver);
		objCurrencyConvPage = new CurrencyConverterPage(driver);
		objHomePage.navigateToCurrencyConverterPage();
	}

	@Test(priority = 1, description = "Test Error Message when invalid amount is entered")
	@Parameters({ "fromCurrency", "toCurrency", "amountToConvert" })

	public void test_Valid_Currency_Conversion(String fromCurrency, String toCurrency, String amountToConvert)
			throws InterruptedException {
		objCurrencyConvPage.calculateCurrencyConversion(fromCurrency, toCurrency, amountToConvert);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}