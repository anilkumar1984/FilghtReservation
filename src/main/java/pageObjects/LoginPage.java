package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class LoginPage extends BasePage {

	@FindBy(id = "email")
	private WebElement emailAddressInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement submitBtn;

	@FindBy(xpath = "//a[text()='Register']")
	private WebElement registerLink;
	
	private PageObjectManager pageObjectManager;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager=new PageObjectManager(driver);
	}

	public void enterEmail(String email) {
		sendKeys(emailAddressInput, email);
	}

	public void enterPassword(String password) {
		sendKeys(passwordInput, password);
	}

	public UserHomePage clickLogin() {
		click(submitBtn);
		return pageObjectManager.getUserHomePage();
	}

	public RegisterPage clickOnRegisterLink() {
		click(registerLink);
		return pageObjectManager.getRegisterPage();
	}

	public boolean verifyLoginPageIsDisplayed() {
		return waitForAnElement(emailAddressInput);
	}
}
