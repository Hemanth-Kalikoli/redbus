package com.redbus.testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.redbus.base.TestBase;
import com.redbus.pages.BusSearchResultsPage;
import com.redbus.pages.BusTicketsHomePage;
import com.redbus.util.TestUtil;

public class BusTicketsHomePageTest extends TestBase {

	BusTicketsHomePage busTicketsHomePage;
	BusSearchResultsPage busSearchResultsPage;
	String sheetName = "BusSearch";

	public BusTicketsHomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		busTicketsHomePage = new BusTicketsHomePage();
	}

	@Test(priority = 2)
	public void verifyBusSearch() {
		busSearchResultsPage = busTicketsHomePage.searchBuses(prop.getProperty("fromCity"), prop.getProperty("toCity"),
				prop.getProperty("date"), prop.getProperty("month"), prop.getProperty("year"));
	}

	//By using DataProvider
	@DataProvider
	public Object[][] getExcelTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getExcelTestData")
	public void validateBusSearchMultipleTimes(String fromCity, String toCity, String date, String month, String year){
		busSearchResultsPage = busTicketsHomePage.searchBuses(fromCity, toCity, date, month, year);
	}

	@AfterMethod
	public void tearDown() {
		quitDriver();
	}
}
