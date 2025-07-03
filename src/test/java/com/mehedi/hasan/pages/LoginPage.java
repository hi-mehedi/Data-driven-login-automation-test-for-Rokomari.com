package com.mehedi.hasan.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.mehedi.hasan.drivers.PageDriver;
import com.mehedi.hasan.utilities.GetScreenshot;
import com.mehedi.hasan.utilities.commonMathod;


public class LoginPage extends commonMathod {

	/*
	 * Locators Methods
	 */

	ExtentTest test;
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test = test;
	}

	@FindBys({ @FindBy(xpath = "//*[@id=\"js--login-form\"]/div[2]/div/div/input[2]") })
	WebElement userEmail;

	@FindBys({ @FindBy(xpath = "//*[@id=\"js--btn-next\"]") })
	WebElement nextButton;

	@FindBys({ @FindBy(xpath = "//*[@id=\"login-form\"]/div[1]/div/input")
			 })
	WebElement password;
	
	@FindBys({ @FindBy(xpath = "//*[@id=\"login-form\"]/button")
	 })
    WebElement logIN;
	
	@FindBys({ @FindBy(xpath = "//*[@id=\"ts--desktop-navigation\"]/div[2]/div/div[2]/button/span")
	 })
   WebElement text;
	

	// Report
	public void passCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
	}

	@SuppressWarnings("unused")
	public void passCaseWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	@SuppressWarnings("unused")
	public void softFailCase(String message, String scName) throws IOException {
	    test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
	    String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
	    String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
	    test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}

	// Fail
	@SuppressWarnings("unused")
	public void failCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}

	public void loginWithData(String emailValue, String passwordValue) throws IOException {
		try {
			test.info("Please enter your email address");
			if (userEmail.isDisplayed()) {
				userEmail.clear();
				userEmail.sendKeys(emailValue);
				passCase("You have successfully entered your email");
				timeout(1000);
				try {
					test.info("Please enter your NextButton");
					if (nextButton.isDisplayed()) {
						nextButton.click();
						passCase("You have successfully entered your nextButton");
						timeout(2000);
						
				        if (password.isDisplayed() && logIN.isDisplayed()) {
				            passCase("Password field and Login button appeared after Next click");
				        } else {
				            softFailCase("Password field and Login button did not appear — invalid email/number", "InvalidEmailOrNumber");
				            return;
				        }
						try {
							test.info("Please enter Password");
							if (password.isDisplayed()) {
								password.clear();
								password.sendKeys(passwordValue);
								timeout(1000);
								passCase("You have successfully entered password");
								try {
									test.info("Please enter Password");
									if(logIN.isDisplayed()) {
										logIN.click();
										timeout(1000);
										try {
										    test.info("Checking login success element");
										    try {
										        if (text.isDisplayed()) {
										            text.click();
										            passCaseWithSC("You have successfully logged in", "LoginSuccess");
										        } else {
										            softFailCase("Login failed for this user (invalid credentials)", "LoginFailed");
										        }
										    } catch (Exception e) {
										        softFailCase("Login failed for this user (GetText not found)", "LoginFailed");
										    }

										} catch (Exception e) {
										    failCase("Error occurred while verifying login", "LoginCheckError");
										}
									}
								} catch (Exception e) {
									failCase("Login locator was not found", "login_fail");
								}
							}
						} catch (Exception e) {
							failCase("password locator was not found", "pass_fail");
						}
					}
				} catch (Exception e) {
					failCase("Nextbutton locator was not found", "next_fail");
				}
			}
		} catch (Exception e) {
			failCase("User email locator was not found", "user_fail");
		}
	}

}