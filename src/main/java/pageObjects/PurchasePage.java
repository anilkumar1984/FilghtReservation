package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class PurchasePage extends BasePage {
	
	@FindBy(xpath="//h2[contains(text(),'Your flight from')]")
	private WebElement purchasePageHeader;
	
	@FindBy(id="inputName")
	private WebElement nameInputBox;
	
	@FindBy(id="address")
	private WebElement addressInputBox;
	
	@FindBy(id="city")
	private WebElement cityInputBox;
	
	@FindBy(id="state")
	private WebElement stateInputBox;
	
	@FindBy(id="zipCode")
	private WebElement zipCodeInputBox;
	
	@FindBy(id="creditCardNumber")
	private WebElement creditCardNumberInputBox;
	
	
	@FindBy(id="creditCardMonth")
	private WebElement creditCardMonthInputBox;
	
	
	@FindBy(id="creditCardYear")
	private WebElement creditYearInputBox;
	
	@FindBy(xpath="//input[@value='Purchase Flight']")
	private WebElement purchaseFlightButton;
	
	
	@FindBy(id="nameOnCard")
	private WebElement nameOnCardInputBox;
	
	@FindBy(id="cardType")
	private WebElement cardTypeDropDown;
	
	private PageObjectManager pageObjectManager;
	
	
	
	
	public PurchasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
		pageObjectManager=new PageObjectManager(driver);
	}
	
	public boolean verifyPurchasePageIsDisplayed() {
		return waitForAnElement(purchasePageHeader);
		
	}
	public void enterName(String name) {
		sendKeys(nameInputBox,name);
	}
	
	public void enterAddress(String address) {
		sendKeys(addressInputBox,address);
	}
	
	public void enterCity(String city) {
		sendKeys(cityInputBox,city);
	}
	
	public void enterState(String state) {
		sendKeys(stateInputBox,state);
	}
	
	public void enterZipCode(String zipCode) {
		sendKeys(zipCodeInputBox,zipCode);
	}
	
	public void enterCreditNumber(String creditCardNumber) {
		sendKeys(creditYearInputBox,creditCardNumber);
	}
	
	public void enterMonth(String month) {
		sendKeys(creditCardMonthInputBox,month);
	}
	
	public void enterYear(String year) {
		sendKeys(creditYearInputBox,year);
	}
	
	public void enterNameOnCard(String enterNameOnCard) {
		sendKeys(nameOnCardInputBox,enterNameOnCard);
	}
	
	public ConfirmationPage clickOnPurchaseFlight() {
		click(purchaseFlightButton);
		return pageObjectManager.getConfirmationPage();
	}
	
	public void selectCardType(String cardType) {
		selectFromDropDown(cardTypeDropDown,cardType);
		
	}
	
	
	
	
	
	

}
