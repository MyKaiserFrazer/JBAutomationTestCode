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

import page.classes.CartPageFactory;
import page.classes.ShopPageFactory;

/**
 * <b>Description:</b><p> Represents a test case class that mimics a user going through the process to shop for
 * Jamberry nail wraps, randomly picking 3 items to put in the cart and then continuing on through the checkout
 * and billing process to purchase the items. 
 * @author John Steele
 * @version 1.2
 * @since 02/10/2016
 *
 */
public class ShopPageTestCase {
	/**
	 *	Create a Selenium WebDriver reference in preparation for a new Firefox browser object
	 */
	private WebDriver driver;
//	private String baseUrl;
	
	/**
	 * 	A ShopPageFactory <b>reference</b> which will represent the Shop page displaying Jamberry nail wrap
	 * 	products. It will first login the user than randomly select 3 nail wraps and continue on 
	 * 	to the cart page (see CartPageFactory next).
	 */
	ShopPageFactory shopPage;
	
	/**
	 * 	A CartPageFactory <b>reference</b> which will represent the Cart page displaying the Jamberry nail wrap
	 * 	items selected for purchase. 
	 */
	CartPageFactory cartPage;
	
	/**
	 * This method prepares the WebDriver object to talk to the Firefox browser. Instantiates page factory objects
	 * for the multiple web pages that must to navigated through for a consumer to shop and purchase products on
	 * the Jamberry.com web site.
	 * @param None
	 * @return Nothing
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		/**
		 *  Setup a Firefox profile for use in Automation testing in the "QAAutomation" folder.
		 */
		File file = new File(Constants.Firefox_Profile);
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		
		/**
		 *  Creating a new object for the Shop Page. This calls the constructor of ShopPageFactory class. See 
		 *  that constructor in ShopPageFactory.java 
		 */
		shopPage = new ShopPageFactory(driver);
		
		/**
		 *  Creating a new object for the Cart Page. This calls the constructor of CartPageFactory class. See 
		 *  that constructor in CartPageFactory.java 
		 */
		cartPage = new CartPageFactory(driver);
		
		/**
		 * Maximize the browser window when it displays, setup an implicit timeout of 10 seconds, configure logging
		 * by setting up log4j.properties file, load the initial page to begin the test on.
		 * 
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");
		driver.get(Constants.URL); 
	}

	@Test(invocationCount=1)
	public void test() throws Exception {
//		int sleepTime = 0;
		shopPage.referenceStartPage(); // start at the beginning
		Thread.sleep(2000); // just to easier see what's happening through observation
		shopPage.clickButtonBarLoginButton(); // prepare to login
		shopPage.clickCustomerLoginMenuItem();
		shopPage.enterLoginUserID(); // enter userID to login
		shopPage.enterPassword(); // enter the password
		shopPage.clickAccountPageLoginButton(); // click the Login button
		shopPage.clickButtonBarLoginButton(); // click the Login or now My Account button to display the sub menu
		shopPage.clickMyAccountSubmenuItem(); // click on My Account sub menu item to prove that we've logged in
//		Thread.sleep(3000);
		shopPage.clickShopMenu(); // go back to the Shop page
		shopPage.clickNailWrapsSubmenu();	// get to the Nail Wraps shop page
//		Thread.sleep(2000);
		shopPage.shopSelect3Wraps();
		shopPage.gotoCartPage(); // continue on at the Cart page
		cartPage.numItemsInCart();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		driver.quit();	// This is commented out so the browser will still display after the test, however 
		// resources won't be released.
	}
	
	

}
