package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class WelcomePage extends BasePage {
	
	
	@FindBy(xpath="//a[text()='home']")
	private WebElement homeLink;
	
	@FindBy(xpath="//h1[contains(text(),'Welcome to the Simple Travel Agency')]")
	private WebElement WelcomePageHeader;
	
	@FindBy(xpath="//select[@name='fromPort']")
	private WebElement fromPortDropDown;
	
	@FindBy(xpath="//select[@name='toPort']")
	private WebElement toPortDropdown;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement FindFlightsButton;
	
	PageObjectManager pageObjectManager;
	
	public WelcomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager=new PageObjectManager(driver);
	}
	
	public boolean verifyWelcomePageIsDisplayed() {
		return waitForAnElement(WelcomePageHeader);
	}
	
	public void selectDepartureCity(String departureValue) {
		selectFromDropDown(fromPortDropDown, departureValue);
	}
	
	public void selectDestinationCity(String destinationCity) {
		selectFromDropDown(toPortDropdown, destinationCity);
	}
	
	public ReservationPage clickOnFindFlightsButton() {
		click(FindFlightsButton);
		return pageObjectManager.getReservationPage();
	}
	
	public LoginPage clickOnHome() {
		click(homeLink);
		return pageObjectManager.getLoginPage();
	}
		
	
}
