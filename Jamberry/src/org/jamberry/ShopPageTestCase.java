package org.jamberry;

import utilities.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import page.classes.ShopPageFactory;


public class ShopPageTestCase {
	private WebDriver driver;
//	private String baseUrl;
	ShopPageFactory shopPage;
	

	@Before
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
		shopPage.addButterflyKissesToCart();
		shopPage.addLeoGeoLaceToCart();
/*		shopPage.departureDateTextBox(driver).sendKeys("12/25/2014");
		shopPage.returnDateTextBox(driver).sendKeys("12/31/2014");
		shopPage.clickOnSearchButton(driver);
*/	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	

}
