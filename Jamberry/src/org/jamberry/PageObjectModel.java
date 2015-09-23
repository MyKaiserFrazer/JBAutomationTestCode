package org.jamberry;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import page.classes.ShopPage;


public class PageObjectModel {
	private WebDriver driver;
	private String baseUrl;
	

	@Before
	public void setUp() throws Exception {
		// Setup a Firefox profile for use in Automation testing.
		File file = new File("C:\\Users\\John Steele\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\QAAutomation");
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		
		// Using Australia site as reference ../au/en/..
		baseUrl = "https://www.jamberry.com/au/en/shop/";
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("C:\\Users\\John Steele\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");
	}

	@Test
	public void roboTest() {
		driver.get(baseUrl);
		ShopPage.findReferencePage(driver);
/*		ShopPage.destinationTextBox(driver).sendKeys("Chicago");
		ShopPage.departureDateTextBox(driver).sendKeys("12/25/2014");
		ShopPage.returnDateTextBox(driver).sendKeys("12/31/2014");
		ShopPage.clickOnSearchButton(driver);*/
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	

}
