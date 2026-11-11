package com.redbus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.redbus.base.TestBase;

public class GlobalPage extends TestBase {
	
	@FindBy(xpath="//a[@aria-label='redBus logo']")
	WebElement redbusLogo;

}
