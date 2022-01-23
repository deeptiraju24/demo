package com.youtube.tests.base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	private static final long IMPLICITWAITTIMEOUT = 40;
	private static final long PAGELOADTIMEOUT = 40;
	protected WebDriver driver = null;
	public enum Browser{
		CHROME
	}

	@BeforeTest
	public void setup() {
		//Currently the driver is hard coded but later we can enhance it to dynamic
        this.driver = getWebDriver(Browser.CHROME);
	}
	
	private WebDriver getWebDriver(Browser browser) {
		WebDriver driver = null;
		if(browser == Browser.CHROME) {
			driver = getChromeDriver();
		}
		
		// Maximize the browser
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		//Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITWAITTIMEOUT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGELOADTIMEOUT));
		return driver;
	}
	
	private WebDriver getChromeDriver() {
		//Set the driver path
		System.setProperty("webdriver.chrome.driver", getChromeDriverPath());	
		
		//Creating an object of ChromeDriver
		return new ChromeDriver();
	}
	
	private String getCurrentProjectFolderPath() {
		return System.getProperty("user.dir");
	}

	private String getChromeDriverPath() {
		return getCurrentProjectFolderPath()+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"drivers"+File.separator+"chrome"+File.separator+"chromedriver.exe";        
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver)
	     .executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	@AfterTest
	public void teardown() {
		this.driver.close();
		this.driver.quit();
		System.out.println("Quit Browser!");
	}

}
