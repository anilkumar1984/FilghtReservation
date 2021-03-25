package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class RegisterPage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Register')]")
	private WebElement registerButton;

	@FindBy(id = "name")
	private WebElement nameInput;

	@FindBy(id = "company")
	private WebElement companyInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "password-confirm")
	private WebElement passwordConfirmInput;
	
	PageObjectManager pageObjectManager;

	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager=new PageObjectManager(driver);
	}

	public boolean verifyRegisterPageIsDisplayed() {
		return waitForAnElement(registerButton);
	}

	public void enterName(String name) {
		sendKeys(nameInput, name);
	}

	public void enterCompany(String company) {
		sendKeys(companyInput, company);
	}

	public void enterEmailAddress(String email) {
		sendKeys(emailInput, email);
	}

	public void enterPassword(String password) {
		sendKeys(passwordInput, password);

	}

	public void enterConfirmPassword(String confirmPassword) {
		sendKeys(passwordConfirmInput, confirmPassword);

	}
	
	public UserHomePage clickOnRegisterButton() {
		click(registerButton);
		return pageObjectManager.getUserHomePage();
	}

}
