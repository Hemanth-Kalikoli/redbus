package com.redbus.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.TestBase;
import com.redbus.pages.LoginPage;
import com.redbus.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginWithMobileNumber() {
		loginPage.clickOnAccount();
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.verifyLoginModalPopup(), true);
		Assert.assertEquals(loginPage.verifyMobileNumberTextField(), true);
		loginPage.enterMobileNumber();
		TestUtil.switchToFrame(loginPage.getreCAPTCHAiframe());
		Assert.assertEquals(loginPage.clickOnreCAPTCHACheckbox(), true);
		TestUtil.switchToDefaultContent();
		loginPage.clickOnContinueButton();
		TestUtil.implicitWait(10);
		loginPage.enterOTP("234567");
		loginPage.clickOnVerifyOTPButton();
	}

	@Test(enabled = false)
	public void signInWithGoogle() {
		loginPage.clickOnAccount();
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.verifyLoginModalPopup(), true);
		TestUtil.switchToFrame(loginPage.getGoogleSignInFrame());
		Assert.assertEquals(loginPage.verifySignInWithGoogle(), true);
		loginPage.clickOnSignInWithGoogle();
		TestUtil.switchToDefaultContent();
		String parentWindow = TestUtil.switchToChildWindow();
		loginPage.enterEmailOrPhone();
		loginPage.verifyNextButtonInSignInWithGoogle();
		loginPage.clickOnNextButtonInSignInWithGoogle();
		TestUtil.switchToParentWindow(parentWindow);
		TestUtil.closeWindow();
	}
	
	@AfterMethod
	public void tearDown() {
		quitDriver();
	}

}
