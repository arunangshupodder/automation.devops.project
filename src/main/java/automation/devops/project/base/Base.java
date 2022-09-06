package automation.devops.project.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import automation.devops.project.utils.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	protected RemoteWebDriver driver;
	
	@BeforeSuite
	public void SetupSuite() {
		
	}
	
	@BeforeMethod
	public void SetupTestMethod() {
		if (Boolean.parseBoolean(Config.isBrowserEnabled())) {
			switch (Config.getBrowserType()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--incognito");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--private");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			}
		} else {
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(Config.getURL());
	}
	
	@AfterMethod
	public void TeardownTestMethod() {
		driver.close();
	}
	
	@AfterSuite
	public void TeardownSuite() {
		
	}
}
