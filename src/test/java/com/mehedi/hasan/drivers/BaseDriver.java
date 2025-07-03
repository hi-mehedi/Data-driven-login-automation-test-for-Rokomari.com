package com.mehedi.hasan.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {
	
	public  static String url = "https://www.rokomari.com/login";

	public static WebDriver driver;
	@BeforeSuite
	public void StartBrowser() {
		String webBrowser = "chrome";
		if(webBrowser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(webBrowser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		PageDriver.getInstance().setDriver(driver);

	}
	@AfterSuite
	public void closeBrowse() {
        driver.close();
  }
}
