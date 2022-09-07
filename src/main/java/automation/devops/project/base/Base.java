package automation.devops.project.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import automation.devops.project.utils.Config;

public class Base {
	
	@BeforeSuite
	public void SetupSuite() {
		
	}
	
	@BeforeMethod
	public void SetupTestMethod() {
		DriverFactory.getInstance().getDriver().get(Config.getURL());
	}
	
	@AfterMethod
	public void TeardownTestMethod() {
		DriverFactory.getInstance().closeDriver();
	}
	
	@AfterSuite
	public void TeardownSuite() {
		
	}
	
	protected WebDriver getDriver() {
		return DriverFactory.getInstance().getDriver();
	}
}
