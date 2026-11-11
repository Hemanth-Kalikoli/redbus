package com.redbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.redbus.base.TestBase;
import com.redbus.util.TestUtil;

public class BusSearchResultsPage extends TestBase{
	
	@FindBy(xpath="//div[contains(@class,'topNavigationContainer__')]//span[@role='text']")
	WebElement busNavigation;
	
	String tabSelected = "//span[text()='%replace%']/parent::div[@aria-selected='true']";
	
	// Initializing Page Objects
	public BusSearchResultsPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getNavigationText() {
		return busNavigation.getAttribute("aria-label");
	}

	public boolean isTabSelected(String tabName) {
		By selectedTab = TestUtil.customXpath(tabSelected, tabName);
		return getDriver().findElement(selectedTab).isDisplayed();
	}

}
