package org.jamberry;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import utilities.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import page.classes.ShopPageFactory;


public class ShopPageTestCase {
	private WebDriver driver;
//	private String baseUrl;
	ShopPageFactory shopPage;
	

	@BeforeMethod
	public void setUp() throws Exception {
		/**
		 *  Setup a Firefox profile for use in Automation testing in the "QAAutomation" folder.
		 */
		File file = new File(Constants.Firefox_Profile);
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		
		/**
		 *  Creating a new object for the Shop Page. This calls the constructor of ShopPageFactory class. See that constructor
		 *  in ShopPageFactory.java 
		 */
		shopPage = new ShopPageFactory(driver); 
		
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("C:\\Users\\John Steele\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");
		driver.get(Constants.URL); 
	}

	@Test
	public void test() throws Exception {
		int sleepTime = 3000;
		
		shopPage.referenceStartPage(); // start at the beginning
		Thread.sleep(sleepTime);
		shopPage.addFirstWrapToCart(); // add the 1st wrap on the page to the Cart.
		Thread.sleep(sleepTime);
		shopPage.addSecondWrapToCart(); // add the 2nd wrap on the page to the Cart.
		Thread.sleep(sleepTime);
		shopPage.addThirdWrapToCart(); // add the 3rd wrap on the page to the Cart.
		Thread.sleep(sleepTime);
		shopPage.gotoCartPage(); // navigate to the Cart page
		Thread.sleep(sleepTime);
		shopPage.clickShopMenu(); // go back to the Shop page
		Thread.sleep(sleepTime);
		shopPage.clickButtonBarLoginButton(); // prepare to login
		Thread.sleep(sleepTime);
		shopPage.enterLoginUserID(); // enter userID to login
		Thread.sleep(sleepTime);
		shopPage.enterPassword(); // enter the password
		Thread.sleep(sleepTime);
		shopPage.clickAccountPageLoginButton(); // login as a user
		shopPage.clickMyAccountButtonBarButton(); // prove that we've logged in
		Thread.sleep(sleepTime);
		shopPage.gotoCartPage(); // continue on at the Cart page
		Thread.sleep(sleepTime);
		shopPage.findItemsInCart(); // what's in my Cart?
		Thread.sleep(sleepTime);

//		shopPage.returnDateTextBox(driver).sendKeys("12/31/2014");
//		shopPage.clickOnSearchButton(driver);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		driver.quit();
	}
	
	

}
