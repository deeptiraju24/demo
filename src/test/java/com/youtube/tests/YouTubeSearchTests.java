package com.youtube.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.youtube.pageobjects.HomePage;
import com.youtube.tests.base.BaseTest;

public class YouTubeSearchTests extends BaseTest{
	
	private HomePage homePage = null;
	private List<String> expectedListOfYouTubeApps = Arrays.asList("YouTube TV","YouTube Music","YouTube Kids", "YouTube for Artists");
	private static final String SEARCHTEXT="bata drums";
	private static final String URL = "http://www.youtube.com";
	
	@Test(priority=1)
	public void launchYouTube() {
		driver.get(URL);
		System.out.println("YouTube is Launched. URL = "+URL);
		
		//Construct YouTube HomePage object
		this.homePage = new HomePage(driver);
	}
	
	@Test(priority=2)
	public void verifyYouTubeLogo() {
		Assert.assertTrue(this.homePage.isYoutubeLogoPresent(), "FAIL: HOMEPAGE : YouTube logo is not present");
	}
	
	@Test(priority=3)
	public void verifyYouTubeAppsList() {
		this.homePage.click_youtubeAppsIcon();
		List<String> actualListOfYouTubeApps = this.homePage.getListOfYouTubeAppsShownAfterHover();
		this.homePage.click_youtubeAppsIcon();
		Assert.assertTrue(expectedListOfYouTubeApps.equals(actualListOfYouTubeApps), "FAIL: HOMEPAGE : YouTube Apps list is NOT matching. ACTUAL = "+actualListOfYouTubeApps+", EXPECTED = "+expectedListOfYouTubeApps);
	}
	
	@Test(priority=4)
	public void searchTextAndVerifyInfiniteScrolling() {
		this.homePage.enterSearchText(SEARCHTEXT);
		this.homePage.clickSearchButton();
		
		Integer beforeScrollVideosCount = null;
		Integer afterScrollVideosCount = null;
		
		Integer initialLoadedVideosCount = null;
		Integer finallLoadedVideosCount = null;
		
		do {
			beforeScrollVideosCount = this.homePage.getSearchResultVideosCount();
			System.out.println("Before Scroll Total Videos Count = "+beforeScrollVideosCount);			
			WebElement lastElement = this.homePage.getSearchResultVideoElements().get(beforeScrollVideosCount-1);
			this.scrollToElement(lastElement);
			System.out.println("Scroll To Last Loaded Video(bottom element) is Performed");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			afterScrollVideosCount = this.homePage.getSearchResultVideosCount();
			System.out.println("After Scroll Total Videos Count = "+afterScrollVideosCount);
			
			if(beforeScrollVideosCount.intValue()==afterScrollVideosCount.intValue()) {
				System.out.println("Infinity Page Scrolling is ENDED. Total Loaded Videos Count = "+beforeScrollVideosCount);	
				finallLoadedVideosCount = afterScrollVideosCount;
			}
			initialLoadedVideosCount = (initialLoadedVideosCount==null) ? beforeScrollVideosCount : null;
			System.out.println("*****************************************");			
		}while(beforeScrollVideosCount!=null && afterScrollVideosCount!=null && beforeScrollVideosCount.intValue()!=afterScrollVideosCount.intValue());
		if(initialLoadedVideosCount.intValue() == finallLoadedVideosCount.intValue()) {
			Assert.fail("Infinite Scrolling is not working. Initial Loaded videos count is same as Final Loaded videos count. Count="+finallLoadedVideosCount);
		}
	}
}
