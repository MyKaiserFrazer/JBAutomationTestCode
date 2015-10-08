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
import page.classes.ConsultantPageFactory;
import page.classes.ShopPageFactory;

public class ConsultantPageTestCase {
	private WebDriver driver;
	ConsultantPageFactory consultPage;
	
	@BeforeMethod
	public void setUp() throws Exception {
		/**
		 *  Setup a generic Firefox profile for use in Automation testing in the "QAAutomation" folder.
		 */
		File file = new File(Constants.Firefox_Profile);
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		
		/**
		 *  Creating a new object for the Shop Page. This calls the constructor of ShopPageFactory class. See that constructor
		 *  in ShopPageFactory.java 
		 */
		consultPage = new ConsultantPageFactory(driver);
		
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Properties file used for logging		
		PropertyConfigurator.configure("C:\\Users\\John Steele\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");
		driver.get(Constants.cpiURL); 
	}
	
	@Test
	public void test() throws Exception {
		consultPage.referenceStartPage();
		Thread.sleep(3000);
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birthdate
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		driver.quit();
	}

}
