package com.redbus.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.TestBase;
import com.redbus.pages.BusSearchResultsPage;
import com.redbus.pages.BusTicketsHomePage;

public class BusSearchResultsPageTest extends TestBase {
	
	BusTicketsHomePage busTicketsHomePage;
	BusSearchResultsPage busSearchResultsPage;
	
	public BusSearchResultsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void busSearch() {
		initialization();
		busTicketsHomePage = new BusTicketsHomePage();
		busSearchResultsPage = busTicketsHomePage.searchBuses(prop.getProperty("fromCity"), 
				prop.getProperty("toCity"),prop.getProperty("date"), 
				prop.getProperty("month"), prop.getProperty("year"));
	}
	
	@Test(priority = 1)
	public void verifySearchResultsNavigation() {
		String expected = prop.getProperty("fromCity") + " to " + prop.getProperty("toCity");
		Assert.assertEquals(busSearchResultsPage.getNavigationText(), expected);
		Assert.assertTrue(busSearchResultsPage.isTabSelected("Bus"), "Bus tab is not selected");
	}
	
	@AfterMethod
	public void tearDown() {
		quitDriver();
	}

}
