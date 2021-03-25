package generics;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import managers.BrowserManager;
import utility.Utility;

public class BaseTest {
	BrowserManager browserManager;
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite(alwaysRun=true)
	public void extentReportSetUp() {
		String currentDateAndTime = Utility.getCurrentDateAndTime();
		File reporterPath = new File("./Reports/blazedemo_" + currentDateAndTime + ".html");
		htmlReporter = new ExtentHtmlReporter(reporterPath);
		htmlReporter.config().setDocumentTitle("Blaze Demo Report");
		htmlReporter.config().setReportName("Blaze Demo Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		

	}
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun=true)
	public void setUp(String browser) {
		
		extent.setSystemInfo("Browser", browser);
		browserManager = new BrowserManager(browser);
		driver = browserManager.getDriver();
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		//System.out.println("Result Status;" + result.getStatus());
		//StackTraceElement[] ar=result.getThrowable().getStackTrace();
		//System.out.println("Stack Trace is: "+Arrays.toString(ar));
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = Utility.captureScreenShot(driver, result.getName());
			try {
				
				logger.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//logger.fail(e.getMessage());
			}
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.pass(result.getName() + "-Test Passed");
		}

		if (result.getStatus() == ITestResult.SKIP) {

			logger.info(result.getName() + "-Test Skipped");
		}

		extent.flush();
		driver.quit();
	}
}
