package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	private WebDriver driver = null;
	public BasePage(WebDriver driver_v) {
		this.driver=driver_v;
	}
	
	public WebElement findElement(By by) {
		return this.driver.findElement(by);
	}

}
