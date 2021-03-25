package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class ReservationPage extends BasePage {

	

	@FindBy(xpath = "//h3[contains(text(),'Flights from')]")
	private WebElement reservationPageHeader;

	@FindBy(xpath = "//tr//td[6]")
	private List<WebElement> listOfActualPriceValueElements;

	@FindBy(xpath = "//input[@value='Choose This Flight']")
	private List<WebElement> chooseThisFlightButton;
	
	private PageObjectManager pageObjectManager;

	public ReservationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager=new PageObjectManager(driver);
	}

	public boolean verifyReservationPageIsDisplayed() {
		return waitForAnElement(reservationPageHeader);
	}

	public int selectFlightWithLowPrice() {
		int lowPriceIndex;
		ArrayList<String> al1 = new ArrayList<String>();
		int count = listOfActualPriceValueElements.size();
		for(int i=0;i<count;i++) {
			String actualPrice=listOfActualPriceValueElements.get(i).getText();
			al1.add(actualPrice);
			
		}

		ArrayList<String> al2 = new ArrayList<String>();
		al2.addAll(al1);
		Collections.sort(al2);
		//System.out.println(al2);
		String lowestPrice = al2.get(0);
		lowPriceIndex= al1.indexOf(lowestPrice);
		//System.out.println("Index is:"+lowPriceIndex);
		return lowPriceIndex;
		//System.out.println("Index is:"+ index);

	}

	public PurchasePage clickOnChooseFlight(int priceIndex) throws InterruptedException {
		Thread.sleep(1000);
		click(chooseThisFlightButton.get(priceIndex));
		return pageObjectManager.getPurchasePage();
	}

}
