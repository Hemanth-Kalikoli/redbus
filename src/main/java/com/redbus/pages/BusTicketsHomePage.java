package com.redbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.redbus.base.TestBase;
import com.redbus.util.TestUtil;

public class BusTicketsHomePage extends TestBase {
		
	// Page Factory : Object Repository
	
	    @FindBy(xpath="//input[@id='srcinput']//ancestor::div[contains(@class,'inputWrapper___')]")
	    WebElement fromTextBoxWrapper;
	    
	    @FindBy(xpath="//input[@id='destinput']//ancestor::div[contains(@class,'inputWrapper___')]")
	    WebElement toTextBoxWrapper;
	    
		@FindBy(id="srcinput")
		WebElement fromTextBox;
		
		@FindBy(xpath="//input[@id='destinput']")
		WebElement toTextBox;
		
		@FindBy(xpath="//div[contains(@class,'spinner-icon___')]")
		WebElement spinnerIcon;
		
		@FindBy(xpath="//div[@aria-label='Select date of journey']")
		WebElement dateOfJourney;
		
		@FindBy(xpath="//button[text()='Search buses']")
		WebElement searchBusesButton;
		
		@FindBy(xpath="//div[contains(@class,'listItem___') and @id='suggestion-0']")
		WebElement firstSuggestion;
		
		String todayTimeStamp="//div[contains(@class,'date__') and contains(@aria-label,'%replace%')]";

        String dynamicDate="//div[contains(@class,'date___')]/span[text()='%replace%']";
		
		@FindBy(xpath="//p[contains(@class,'monthYear__')]")
		WebElement getMonthYear;
		
		@FindBy(xpath="//i[contains(@aria-label,'Next month') and @aria-disabled='false']")
		WebElement nextMonthIconArrow;
		
		@FindBy(xpath="//div[contains(@class,'topNavigationContainer__')]//span[@role='text']")
		WebElement busNavigation;

		//Initializing Page Objects
		public BusTicketsHomePage() {
			PageFactory.initElements(getDriver(), this);
		}
		
		public void enterFromCity(String cityName) {
			TestUtil.explicitWait(ExpectedConditions.elementToBeClickable(fromTextBox), 5);
			TestUtil.clickUsingJS(fromTextBox);
			fromTextBox.clear();
			fromTextBox.sendKeys(cityName);
			TestUtil.explicitWait(ExpectedConditions.invisibilityOf(spinnerIcon), 5);
			firstSuggestion.click();
		}
		
		public String selectedFromCity() {
			String selectedCity=fromTextBox.getAttribute("value");
			return selectedCity;
		}
		
		public void enterToCity(String cityName) {
			TestUtil.explicitWait(ExpectedConditions.elementToBeClickable(toTextBox), 5);
			TestUtil.clickUsingJS(toTextBox);
			toTextBox.clear();
			toTextBox.sendKeys(cityName);
			TestUtil.explicitWait(ExpectedConditions.invisibilityOf(spinnerIcon), 5);
			firstSuggestion.click();
		}
		
		public String selectedToCity() {
			String selectedCity=toTextBox.getAttribute("value");
			return selectedCity;
		}
		
		public void selectTodayDateOfJourney() {
			dateOfJourney.click();
			By timeStamp = TestUtil.customXpath(todayTimeStamp, TestUtil.getFormattedDate());
			getDriver().findElement(timeStamp).click();
		}
		
		public static String[] getMonthAndYear(String monthAndYearValue) {
		    return monthAndYearValue.split(" ");
		}
		
		public void selectDateOfJourneyDynamic(String expectedDate, String expectedMonth, String expectedYear) {
			dateOfJourney.click();
		    if(Integer.parseInt(expectedDate)>31) {
		    	System.out.println("Invalid Date: "+ expectedDate);
		    	return;
		    }
		    if(expectedMonth.equals("February") && Integer.parseInt(expectedDate)>29) {
		    	System.out.println("Invalid Date for February month: ");
		    	return;
		    }
		    
		    try {
		        java.time.LocalDate target = java.time.LocalDate.of(
		                Integer.parseInt(expectedYear),
		                java.time.Month.valueOf(expectedMonth.toUpperCase()),
		                Integer.parseInt(expectedDate));
		        if (target.isBefore(java.time.LocalDate.now())) {
		            System.out.println("Past date not allowed: " + expectedDate + " " + expectedMonth + " " + expectedYear);
		            return;
		        }
		    } catch (Exception e) {
		        System.out.println("Invalid date: " + expectedDate + " " + expectedMonth + " " + expectedYear);
		        return;
		    }
		    
			String monthAndYearValue=getMonthYear.getText();
			
            int clicks=0;
		    while(!(getMonthAndYear(monthAndYearValue)[0].equals(expectedMonth) && 
		    		getMonthAndYear(monthAndYearValue)[1].equals(expectedYear))) {
		    	if (clicks++ > 24) { 
		            System.out.println("Month not found in calendar: " + expectedMonth + " " + expectedYear);
		            return;
		        }
		    	nextMonthIconArrow.click();
			    monthAndYearValue=getMonthYear.getText();
		    }
		    try {
		    	getDriver().findElement(TestUtil.customXpath(dynamicDate, expectedDate)).click();
		    }
		    catch(Exception e) {
		    	System.out.println("Wrong date selection "+expectedDate+" "+expectedMonth);
		    }
		}
		
		public BusSearchResultsPage clickOnSearchBusesButton() {
			searchBusesButton.click();
			//TestUtil.explicitWait(ExpectedConditions.visibilityOf(busNavigation), 20);
			return new BusSearchResultsPage();
		}
		
		public BusSearchResultsPage searchBuses(String fromCity, String toCity, String date, String month,
				String year) {
			enterFromCity(fromCity);
			enterToCity(toCity);
			selectDateOfJourneyDynamic(date, month, year);
			return clickOnSearchBusesButton(); 
		}

}
