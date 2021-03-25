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
import pageObjects.UserHomePage;
import pageObjects.WelcomePage;

public class ValidLogin_TC extends BaseTest {
	private LoginPage loginPage;
	private WelcomePage welcomePage;
	private UserHomePage userHomePage;
	private PageObjectManager pageObjectManager;

	@Test(dataProvider="TestData",groups= {"SmokeTest"})
	public void validLogin_TC(String email, String password) {
		
		logger = extent.createTest("validLogin_TC", "Valid Login Test Case");
		//System.out.println("logger is: "+logger);
		//System.out.println(logger);
		logger.info("Test Data Details is: "+email+","+password);
		pageObjectManager = new PageObjectManager(driver);
		welcomePage = pageObjectManager.getWelcomePage();
		loginPage=welcomePage.clickOnHome();
		Assert.assertTrue(loginPage.verifyLoginPageIsDisplayed(),"Verify Login Page");
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		userHomePage=loginPage.clickLogin();
		Assert.assertTrue(userHomePage.verifyUserLoggedIn(),"Verify User Home Page");
		userHomePage.clickOnUser();
		welcomePage=userHomePage.clickOnLogOutLink();
		Assert.assertTrue(welcomePage.verifyWelcomePageIsDisplayed(),"Verify Welcome Page");

	}
	
	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("validLogin_TC");
		int rowCount = excelFileReader.getRowCount();
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			String email = excelFileReader.getCellData("email", rowNumber);
			String password = excelFileReader.getCellData("password", rowNumber);
			Object data[] = { email,password };
			testdata.add(data);	

		}
		
		return testdata.iterator();

	}
}