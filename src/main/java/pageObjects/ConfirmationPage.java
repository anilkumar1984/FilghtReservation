package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class ConfirmationPage extends BasePage {
	
	@FindBy(xpath="//h1[contains(text(),'Thank you for your purchase today')]")
	private WebElement confirmationPageHeader;
	
	@FindBy(xpath="//tr[1]/td[2]")
	private WebElement confirmationId;
	PageObjectManager pageObjectManager;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager =new PageObjectManager(driver);
	}
	
	public String getConfirmationID() {
		return getText(confirmationId);
	}
	

	public boolean verifyConfirmationPageIsDisplayed() {
		return waitForAnElement(confirmationPageHeader);
	}
	
	
	
}
