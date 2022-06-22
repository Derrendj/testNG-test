package testNg;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class launch {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	
	@BeforeSuite
	public void browserlaunch() throws Throwable {

		extent = new ExtentReports("SparkReporter",true);
		extent = new ExtentReports(System.getProperty("user.dir")+"\\testResults.html",true);
		
		extent.loadConfig(new File(System.getProperty("user.dir")+"\testResults.html"));
		extent.addSystemInfo("Environment", "DEV for L1 Screen");
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.facebook.com/");
		
		Thread.sleep(5000);
		
		
	}
	
	@BeforeMethod
	public void teardown(ITestResult result) throws Throwable {
		
		if (result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL,"TEST CASE FAILED IS "+result.getName());
			extentTest.log(LogStatus.FAIL,"TEST CASE FAILED IS "+result.getThrowable());
			
			String screenshot = launch.getScreenshot(driver,result.getName());
			
		}
	}

	public static String getScreenshot(WebDriver screenshotname, String name) throws Throwable {
		
		String dateName = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/FileTestsScreenshots/"+screenshotname+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		
	}
	
	@AfterSuite
	public void closeBrowser() {
	driver.quit();
	extent.flush();
	
	
	}
	
}
