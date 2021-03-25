package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.ConfirmationPage;
import pageObjects.LoginPage;
import pageObjects.PurchasePage;
import pageObjects.RegisterPage;
import pageObjects.ReservationPage;
import pageObjects.UserHomePage;
import pageObjects.WelcomePage;

public class PageObjectManager {

	private PurchasePage purchasePage;
	private ReservationPage reservationPage;
	private WelcomePage welcomePage;
	private ConfirmationPage confirmationPage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private UserHomePage userHomePage;
	private WebDriver driver;
	

	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
		
	}

	public PurchasePage getPurchasePage() {
		return (purchasePage == null) ? new PurchasePage(driver) : purchasePage;
	}

	public ReservationPage getReservationPage() {
		
		//return (reservationPage == null) ? new ReservationPage(driver) : reservationPage;
		if(reservationPage == null) {
			return new ReservationPage(driver);
		}
		else
		{
			return reservationPage;
		}
	}
	
	public WelcomePage getWelcomePage() {
		return (welcomePage == null) ? new WelcomePage(driver) : welcomePage;
	}
	
	public ConfirmationPage getConfirmationPage() {
		return (confirmationPage == null) ? new ConfirmationPage(driver) : confirmationPage;
	}
	
	public LoginPage getLoginPage() {
		return (loginPage == null) ? new LoginPage(driver) : loginPage;
	}
	
	public RegisterPage getRegisterPage() {
		return (registerPage == null) ? new RegisterPage(driver) : registerPage;
	}
	
	public UserHomePage getUserHomePage() {
		return (userHomePage == null) ? new UserHomePage(driver) : userHomePage;
	}
}
