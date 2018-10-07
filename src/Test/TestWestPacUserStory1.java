package Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.CurrencyConverterPage;
import PageFactory.WestPacHomePage;
/**
 * Created by Vijay on 8/10/2018.
 * Test for WestPac User Story 1
 * As a Product Owner 
 * If there is no amount entered and the convert button is clicked then I want to see an error message “Please enter the amount you want to convert.” on the currency converter page.
 * So that 
 * user is able to get a clear indication as to what has to be rectified.
 */
public class TestWestPacUserStory1 {

	WebDriver driver;
	String westPacURL = "http://www.westpac.co.nz";
	WestPacHomePage objHomePage;

	CurrencyConverterPage objCurrencyConvPage;

	@BeforeTest

	public void setup() {

		System.setProperty("webdriver.gecko.driver",
				"C:\\selenium java downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(westPacURL);
		objHomePage = new WestPacHomePage(driver);
		objCurrencyConvPage = new CurrencyConverterPage(driver);
		objHomePage.navigateToCurrencyConverterPage();
	}

	@Test(priority = 1)
	@Parameters({ "fromCurrency", "toCurrency"})

	public void test_Warning_Message_For_Invalid_Input(String fromCurrency, String toCurrency)
			throws InterruptedException {
		objCurrencyConvPage.calculateCurrencyConversion_WithoutValidData(fromCurrency, toCurrency);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}