package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import generics.BasePage;
import managers.PageObjectManager;

public class UserHomePage extends BasePage {
	
	private PageObjectManager pageObjectManager;
	

	public UserHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager=new PageObjectManager(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'You are logged in')]")
	private WebElement userLoggedInSuccessMsg;

	@FindBy(xpath = "//span[@class='caret']")
	private WebElement userDropDown;

	@FindBy(xpath = "//a[contains(@href,'logout')]")
	private WebElement LogoutLink;

	public boolean verifyUserLoggedIn() {
		return waitForAnElement(userLoggedInSuccessMsg);
	}

	public WelcomePage clickOnLogOutLink() {
		click(LogoutLink);
		return pageObjectManager.getWelcomePage();
	}

	public void clickOnUser() {
		click(userDropDown);
	}

}
