package testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviders.ExcelFileReader;
import generics.BaseTest;
import managers.PageObjectManager;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.UserHomePage;
import pageObjects.WelcomePage;
import utility.Utility;

public class Register_TC extends BaseTest {
	private WelcomePage welcomePage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private UserHomePage userHomePage;
	private PageObjectManager pageObjectManager;

	@Test(dataProvider = "TestData",groups= {"SmokeTest"})
	public void register_TC(String name, String company, String email, String password, String confirmPassword) {
		logger = extent.createTest("register_TC", "Register a User");
		//System.out.println("logger is: "+logger);

		
		logger.info(
				"Test Data Details is: " + name + "," + company + "," + email + "," + password + "," + confirmPassword);
		pageObjectManager = new PageObjectManager(driver);
		welcomePage = pageObjectManager.getWelcomePage();
		loginPage = welcomePage.clickOnHome();
		Assert.assertTrue(loginPage.verifyLoginPageIsDisplayed(), "Verify Login Page");
		registerPage = loginPage.clickOnRegisterLink();
		Assert.assertTrue(registerPage.verifyRegisterPageIsDisplayed(), "Verify Register Page");
		registerPage.enterName(name);
		registerPage.enterCompany(company);
		registerPage.enterEmailAddress(email);
		registerPage.enterPassword(password);
		registerPage.enterConfirmPassword(confirmPassword);
		userHomePage = registerPage.clickOnRegisterButton();
		Assert.assertTrue(userHomePage.verifyUserLoggedIn(), "Verify User Home Page");
		userHomePage.clickOnUser();
		welcomePage=userHomePage.clickOnLogOutLink();
		Assert.assertTrue(welcomePage.verifyWelcomePageIsDisplayed(), "Verify Welcome Page");

	}

	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {

		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("register_TC");
		int rowCount = excelFileReader.getRowCount();
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			String name = excelFileReader.getCellData("name", rowNumber);
			String company = excelFileReader.getCellData("company", rowNumber);
			String email = Utility.generateRandomUniqueEmailAddress();
			String password = excelFileReader.getCellData("password", rowNumber);
			String confirmPassword = excelFileReader.getCellData("confirmPassword", rowNumber);
			Object data[] = { name, company, email, password, confirmPassword };
			// System.out.println("data from coulmn: "+data);
			// logger.info("Test data Details is: "+Arrays.toString(data));
			testdata.add(data);

		}

		return testdata.iterator();

	}
}
