package com.redbus.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.redbus.base.TestBase;
import com.redbus.util.TestUtil;

public class LoginPage extends TestBase{
	
	TestUtil testUtil=new TestUtil();
	
	// Page Factory : Object Repository
	@FindBy(xpath="//button[text()='Account']")
	WebElement accountButton;
	
	@FindBy(xpath="//button/span[text()='Sign up']")
	WebElement signUpButton;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'bottomSheetContainer')]")
	WebElement modalPopup;
	
	@FindBy(xpath="//label[text()='Mobile number']//following-sibling::input")
	WebElement mobileNumberTextField;
	
	@FindBy(xpath ="//span[contains(@class,'recaptcha-checkbox') and @aria-checked='false']")
	WebElement recaptchaCheckbox;
	
	@FindBy(xpath="//button[text()='Continue']")
	public WebElement continueButton;
	
	@FindBy(xpath="//input[contains(@class,'otpInput___')]")
	List<WebElement> otpInputBoxes;
	
	@FindBy(xpath="//button[text()='Verify OTP']")
	WebElement verifyOTPButton;
	
	@FindBy(xpath="//span[text()='Sign in with Google']")
	WebElement signinWithGoogleButton;
	
	@FindBy(xpath="//input[@aria-label='Email or phone']")
	WebElement emailOrPhoneTextBox;
	
	@FindBy(xpath="//span[text()='Next']//parent::button")
	WebElement nextButtonInSignin;
	
	@FindBy(xpath="//iframe[@title='Sign in with Google Button']")
	WebElement iframeSignInWithGoogle;
	
    @FindBy(xpath="//iframe[@title='reCAPTCHA']")	
    WebElement iframereCAPTCHA;
    
	//Initializing Page Objects
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//Actions
	public void clickOnAccount() {
		accountButton.click();
	}
	
	public void clickOnLoginButton() {
		TestUtil.explicitWait(ExpectedConditions.elementToBeClickable(loginButton), 5);
		loginButton.click();
	}
	
	public boolean verifyLoginModalPopup() {
		return modalPopup.isDisplayed();
	}
	
	public String verifyModalPopupText() {
		return modalPopup.getText();
	}
	
	public boolean verifyMobileNumberTextField() {
		return mobileNumberTextField.isDisplayed();
	}
	
	public void enterMobileNumber() {
		mobileNumberTextField.sendKeys(prop.getProperty("mobileNumber"));
	}
	
	public WebElement getGoogleSignInFrame() {
	    return iframeSignInWithGoogle;
	}
	
	public boolean verifySignInWithGoogle() {
		return signinWithGoogleButton.isDisplayed();
	}
	
	public void clickOnSignInWithGoogle() {
		signinWithGoogleButton.click();
	}
	
	public boolean verifyEmailOrPhoneTextField() {
		return emailOrPhoneTextBox.isDisplayed();
	}
	
	public void enterEmailOrPhone() {
		emailOrPhoneTextBox.click();
		emailOrPhoneTextBox.sendKeys(prop.getProperty("email"));
	}
	
	public boolean verifyNextButtonInSignInWithGoogle() {
		return nextButtonInSignin.isDisplayed();
	}
	
	public void clickOnNextButtonInSignInWithGoogle() {
		nextButtonInSignin.click();
	}
	
	public boolean clickOnreCAPTCHACheckbox() {
		recaptchaCheckbox.click();
		return recaptchaCheckbox.isSelected();
	}
	
	public void clickOnContinueButton() {
		TestUtil.explicitWait(ExpectedConditions.elementToBeClickable(getContinueButton()), 20);
		continueButton.click();
	}
	
	public WebElement getContinueButton() {
		return continueButton;
	}
	
	public void enterOTP(String otp) {
		for(int i=0;i<otpInputBoxes.size();i++) {
			otpInputBoxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
	}
	
	public BusTicketsHomePage clickOnVerifyOTPButton() {
		verifyOTPButton.click();
		return new BusTicketsHomePage();
	}
	
	public WebElement getreCAPTCHAiframe() {
		return iframereCAPTCHA;
	}
}
