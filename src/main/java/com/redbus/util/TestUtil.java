package com.redbus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.redbus.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 90;
	public static long IMPLICIT_WAIT = 10;
	
	public static String TESTDATA_SHEET_PATH = "//Users/hemanth/eclipse-workspace/redbus/src/main/java/com/"
			+ "redbus/testData/Redbus_BusSearch.xlsx";
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public static void switchToFrame(WebElement locator) {
		getDriver().switchTo().frame(locator);
	}

	public static void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	public static void switchToParentWindow(String parentWindow) {
		getDriver().switchTo().window(parentWindow);
	}

	public static void closeWindow() {
		getDriver().close();
	}

	public static String switchToChildWindow() {
		String parentWindowHandle = getDriver().getWindowHandle();
		Set<String> handles = getDriver().getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentWindowHandle)) {
				getDriver().switchTo().window(handle);
				break;
			}
		}
		return parentWindowHandle; // return parent handle in case you need to switch back later
	}
	
	public static By customXpath(String xpath, String parm) {
		String rawPath=xpath.replaceAll("%replace%", parm);
		return By.xpath(rawPath);
	}

	public static void implicitWait(int seconds) {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	public static <T> void explicitWait(ExpectedCondition<T> condition, int seconds) {
	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
	    wait.until(condition);
	}

	public static <T> T fluentWait(ExpectedCondition<T> condition, int timeoutSeconds, int pollingMillis) {
	    Wait<WebDriver> wait = new FluentWait<>(getDriver())
	            .withTimeout(Duration.ofSeconds(timeoutSeconds))
	            .pollingEvery(Duration.ofMillis(pollingMillis))
	            .ignoring(NoSuchElementException.class)
	            .ignoring(StaleElementReferenceException.class);
	    return wait.until(condition);
	}
	
	public static void clickUsingJS(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) getDriver();
	    js.executeScript("arguments[0].click();", element);
	}

	public static void scrollIntoView(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) getDriver();
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}
	
	public static String getFormattedDate() {
        Calendar calendar = Calendar.getInstance();  // gets current date & time
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        return sdf.format(calendar.getTime());
    }
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// Instantiate DataFormatter once here
	    DataFormatter formatter = new DataFormatter();
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(k));			}
		}
		return data;
	}
	
	public static void captureScreenshot(String testName) throws IOException {
		if (getDriver() == null) {
			System.out.println("Screenshot skipped: driver is null");
			return;
		}
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String path = currentDir + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(path));
		System.out.println("Screenshot saved: " + path);
	}

}
