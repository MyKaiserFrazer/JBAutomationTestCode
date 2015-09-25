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
		
		/**
		 *  Using **Australia** site as the reference url ../au/en/..
		 */
//		baseUrl = "https://www.jamberry.com/au/en/shop/";
		
		/**
		 *  Maximize the browser's window, set the delay.
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure(Constants.Prop_Config);
		driver.get(Constants.URL);
	}

	@Test
	public void test() throws Exception {
		shopPage.referenceStartPage();
		shopPage.addFirstWrapToCart();
		shopPage.addSecondWrapToCart();
		shopPage.gotoCartPage();
/*		shopPage.returnDateTextBox(driver).sendKeys("12/31/2014");
		shopPage.clickOnSearchButton(driver);
*/	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		driver.quit();
	}
	
	

}
