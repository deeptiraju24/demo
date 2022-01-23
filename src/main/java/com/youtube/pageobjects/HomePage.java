package com.youtube.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	
	protected By youtubeLogo = By.xpath("//*[@id='masthead-container']/descendant::a[@id='logo']");
	protected By searchTextField = By.xpath("//*[@id='search']/descendant::input[@id='search']");
	protected By searchButton = By.xpath("//*[@id='search']/descendant::button[@aria-label='Search']");
	protected By youtubeAppsIcon = By.xpath("//*[@id='masthead']/descendant::*[@id='button' and @aria-label='YouTube apps']");
	protected By searchResultVideoList = By.xpath("(//ytd-item-section-renderer//*[@id='contents'])[*]/descendant::*[@id='dismissible']");
	private static final String PAGENAME = "HOMEPAGE";
	private WebDriver driver = null;
	public HomePage(WebDriver driver_v) {
		super(driver_v);
		this.driver=driver_v;
	}
	
	public void enterSearchText(String searchText) {
		this.findElement(searchTextField).sendKeys(searchText);
		System.out.println(PAGENAME+" : Entered Search Text : "+searchText);
	}
	
	public void clickSearchButton() {
		this.findElement(searchButton).click();
		System.out.println(PAGENAME+" : Clicked on Search Button");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isYoutubeLogoPresent() {
		boolean isLogoPresent = this.findElement(youtubeLogo).isDisplayed();
		if(isLogoPresent) {
			System.out.println(PAGENAME+" : YouTube Logo is present");
		}else {
			System.out.println(PAGENAME+" : YouTube Logo is NOT present");
		}
		return isLogoPresent;
	}
	
	public void click_youtubeAppsIcon() {
		this.findElement(youtubeAppsIcon).click();
		System.out.println(PAGENAME+" : Clicked on YouTube Apps Icon");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getListOfYouTubeAppsShownAfterHover(){
		List<WebElement> youtubeAppsListElement = driver.findElements(By.xpath("//*[@id='contentWrapper']/descendant::*[@id='container']//*[@id='items']/descendant::a/descendant::*[@id='primary-text-container']/child::*[@id='label']"));
		List<String> listOfAppNames = new ArrayList<String>();		
		for(WebElement appEle : youtubeAppsListElement) {
			listOfAppNames.add(appEle.getText());
		}
		System.out.println(PAGENAME+" : Get List of YouTube Apps - "+listOfAppNames);
		return listOfAppNames;
	}
	
	public List<WebElement> getSearchResultVideoElements() {
		return this.driver.findElements(searchResultVideoList);		
	}
	
	public int getSearchResultVideosCount() {
		return this.getSearchResultVideoElements().size();		
	}
	
}
