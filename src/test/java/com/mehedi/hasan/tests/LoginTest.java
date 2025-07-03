package com.mehedi.hasan.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mehedi.hasan.drivers.PageDriver;
import com.mehedi.hasan.pages.LoginPage;
import com.mehedi.hasan.utilities.ExcelUtils;
import com.mehedi.hasan.utilities.ExtentFactory;
import com.mehedi.hasan.utilities.commonMathod;



public class LoginTest extends commonMathod{
	
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void openUrl() throws InterruptedException {
		PageDriver.getCurrentDriver().get(url);
		timeout(2000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:13px\"><b>LOGIN</b></p>").assignAuthor("QA Team").assignDevice("Windows");
		
	}
	
	@BeforeMethod
	public void resetPage() throws InterruptedException {
	    PageDriver.getCurrentDriver().manage().deleteAllCookies();
	    PageDriver.getCurrentDriver().get(url);
	    timeout(2000);
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
	    ExcelUtils excelUtils = new ExcelUtils();
	    return excelUtils.getExcelData();
	}


	@Test(dataProvider = "loginData")
	public void multipleLoginTest(String email, String password, String expected) throws IOException {
	    // Creating child node with both email and expected outcome in title
	    childTest = parentTest.createNode(
	        "<p style=\"color:#3E96E7; font-size:13px\"><b>Login Test for: " + email + " (Expected: " + expected + ")</b></p>"
	    );
	    LoginPage loginPage = new LoginPage(childTest);
	    loginPage.loginWithData(email, password);
	}


	
//	@Test (priority = 1)
//	public void createNewUser() throws IOException {
//		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>New User</b></p>");
//		LoginPage loginPage = new LoginPage(childTest);
//		loginPage.createUser();
//	}
	
	@AfterClass
	public void report() {
		report.flush();
	}
	
}