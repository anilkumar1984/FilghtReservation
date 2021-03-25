package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import dataProviders.ExcelFileReader;
import generics.BaseTest;
import managers.BrowserManager;
import managers.PageObjectManager;
import pageObjects.ConfirmationPage;
import pageObjects.PurchasePage;
import pageObjects.ReservationPage;
import pageObjects.WelcomePage;

public class BookAFlightWithLowerPrice_TC extends BaseTest {

	private PageObjectManager pageObjectManager;
	private WelcomePage welcomePage;
	private ReservationPage reservationPage;
	private PurchasePage purchasePage;
	private ConfirmationPage confirmationPage;
	private ExcelFileReader excelFileReader;
	private static int priceIndexRow;
	

	@Test(dataProvider = "TestData",groups= {"SmokeTest"})
	public void bookAFlightWithLowerPriceTC(String from, String to, String name, String address, String city,
			String state, String zipCode, String cardType, String creditCardNumber, String month, String year,
			String nameOnCard) throws InterruptedException {
		logger = extent.createTest("bookAFlightWithLowerPrice_TC", "Book a flight which is having lower price");
		//System.out.println("logger is: "+logger);
		logger.info("Test Data Details is: " + from + "," + to + "," + name + "," + address + "," + city + "," + state
				+ "," + zipCode + "," + cardType + "," + creditCardNumber + "," + month + "," + year + ","
				+ nameOnCard);

		pageObjectManager = new PageObjectManager(driver);
		welcomePage = pageObjectManager.getWelcomePage();
		Assert.assertTrue(welcomePage.verifyWelcomePageIsDisplayed(), "Verify Welcome Page");
		welcomePage.selectDepartureCity(from);
		welcomePage.selectDestinationCity(to);
		reservationPage = welcomePage.clickOnFindFlightsButton();
		Assert.assertTrue(reservationPage.verifyReservationPageIsDisplayed(), "Verify Reservation Page");
		priceIndexRow = reservationPage.selectFlightWithLowPrice();
		purchasePage = reservationPage.clickOnChooseFlight(priceIndexRow);
		Assert.assertTrue(purchasePage.verifyPurchasePageIsDisplayed());
		purchasePage.enterName(name);
		purchasePage.enterAddress(address);
		purchasePage.enterCity(city);
		purchasePage.enterState(state);
		purchasePage.enterZipCode(zipCode);
		purchasePage.selectCardType(cardType);
		purchasePage.enterCreditNumber(creditCardNumber);
		purchasePage.enterMonth(month);
		purchasePage.enterYear(year);
		purchasePage.enterNameOnCard(nameOnCard);
		confirmationPage = purchasePage.clickOnPurchaseFlight();
		Assert.assertTrue(confirmationPage.verifyConfirmationPageIsDisplayed(), "Verify Confirmation Page");
		String confirmationID = confirmationPage.getConfirmationID();
		// System.out.println("logger is:"+logger);
		logger.info("Confirmation ID is: " + confirmationID);

	}

	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {

		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("BookAFlightWithLowerPrice_TC");
		int rowCount = excelFileReader.getRowCount();
		// System.out.println("Row count: "+rowCount);
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			String from = excelFileReader.getCellData("From", rowNumber);
			// System.out.println("data from coulmn: "+from);
			String to = excelFileReader.getCellData("To", rowNumber);
			// System.out.println("data from coulmn: "+to);
			String name = excelFileReader.getCellData("Name", rowNumber);
			// System.out.println("data from coulmn: "+name);
			String address = excelFileReader.getCellData("Address", rowNumber);
			String city = excelFileReader.getCellData("City", rowNumber);
			String state = excelFileReader.getCellData("State", rowNumber);

			String zipCode = excelFileReader.getCellData("ZipCode", rowNumber);
			String cardType = excelFileReader.getCellData("CardType", rowNumber);
			String creditCardNumber = excelFileReader.getCellData("CreditCardNumber", rowNumber);
			String month = excelFileReader.getCellData("Month", rowNumber);
			String year = excelFileReader.getCellData("Year", rowNumber);
			String nameOnCard = excelFileReader.getCellData("NameOnCard", rowNumber);
			Object data[] = { from, to, name, address, city, state, zipCode, cardType, creditCardNumber, month, year,
					nameOnCard };
			// System.out.println("data from coulmn: "+data);
			// logger.info("Test data Details is: "+Arrays.toString(data));
			testdata.add(data);
			// System.out.println(testdata);
			// System.out.println(logger);
			// logger.info("Test Data details: "+testdata);

		}
		// System.out.println("Testdata is: "+testdata);

		return testdata.iterator();

	}
}
