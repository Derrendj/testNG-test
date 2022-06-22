package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testNg.launch;

public class login extends launch{
	
	@Test
	public void loginfb() {
		extentTest=extent.startTest("loginfb","This is loginfb");
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement pass = driver.findElement(By.xpath("//input[@id='pass']"));
		
		email.sendKeys("derrendj");
		pass.sendKeys("derren dj");
	}

}
