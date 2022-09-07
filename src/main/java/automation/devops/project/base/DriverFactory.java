package automation.devops.project.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import automation.devops.project.utils.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static DriverFactory instance = new DriverFactory();
    public static final Boolean BROWSER_MODE = Boolean.valueOf(Config.isBrowserEnabled());
    public static final String BROWSER = Config.getBrowserType();
    /*
     * create a thread-safe driver object
     */
    private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() {
        @Override
        protected RemoteWebDriver initialValue() {
            RemoteWebDriver webDriver = null;
            if (BROWSER_MODE && BROWSER.equals("chrome")) {
            	WebDriverManager.chromedriver().setup();
                ChromeOptions options=new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--disable-logging");
                webDriver = new ChromeDriver(options);
            } else if (BROWSER_MODE && BROWSER.equals("firefox")) {
            	WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--private");
                options.addArguments("--disable-logging");
                webDriver = new FirefoxDriver(options);
            } else {
                try {
                	DesiredCapabilities capabilities = new DesiredCapabilities();
                	capabilities.setBrowserName(BROWSER);
            		webDriver = new RemoteWebDriver(new URL(Config.getLocalGridURL()), capabilities);
                } catch (MalformedURLException e) {
                    //return null;
                }
            }
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            return webDriver;
        }
    };

    private DriverFactory() {
        //Do-nothing. Do not allow to initialize this class from outside
    }

    public static DriverFactory getInstance() {
        return instance;
    }

    /* 
     * call this method to initialize/get the driver object
     */
    public RemoteWebDriver getDriver() {
        return driver.get();
    }

    /* 
     * call this method to close the current window
     */
    public void closeDriver() {
        driver.get().close();
    }
    
    /* 
     * call this method to kill the driver object
     */
    public void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
