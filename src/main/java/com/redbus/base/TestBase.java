package com.redbus.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.redbus.util.TestUtil;

public class TestBase {

	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	// Loaded once for the whole run
	public static final Properties prop = new Properties();

	static {
		String path = System.getProperty("user.dir") + "/src/main/java/com/redbus/config/config.properties";
		try (FileInputStream fis = new FileInputStream(path)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Could not load config.properties from " + path, e);
		}
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");
		WebDriver localDriver;   // local variable, never shared between threads

		if (browserName.equalsIgnoreCase("chrome")) {
			localDriver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			localDriver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		tlDriver.set(localDriver);

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		getDriver().get(prop.getProperty("url"));
	}

	public void quitDriver() {
		if (getDriver() != null) {
			getDriver().quit();
			tlDriver.remove();
		}
	}
}
