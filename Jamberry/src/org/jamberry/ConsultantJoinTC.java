package org.jamberry;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utilities.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import page.classes.ConsultantPageFactory;
import page.classes.EnterBillingInfoPageFactory;
import page.classes.SponsorSearchPageFactory;
import page.classes.StarterKitPageFactory;

/**
 * <b>Description:</b><p> Represents a test case class that mimics a user going through the process to become
 * a Jamberry Consultant or the Consultant Join process.
 * @author John Steele
 * @version 1.2
 * @since 02/10/2016
 *
 */
public class ConsultantJoinTC {
	
	/**
	 * Create a Logger object to log messages to the Console with Log4j.
	 */
	static Logger log = Logger.getLogger(ConsultantJoinTC.class);
	
	/**
	 *	Create a Selenium WebDriver reference in preparation for a new Firefox browser object
	 */
	private WebDriver driver;
	
	/**
	 * 	A ConsultantPageFactory reference which will represent the page/form that will provide 
	 * 	the basic info about the person applying to become a consultant. Info such as name, birthdate,
	 * 	phone number and so on.
	 */
	ConsultantPageFactory consultPage;
	
	/**
	 * 	A SponsorSearchPageFactory reference which will represent the sponsor search page
	 * 	from which a sponsor will be identified as the parent sponsor of the applicant.
	 */
	SponsorSearchPageFactory sponsorSearchPage;
	
	/**
	 * 	A StarterKitPageFactory reference which will represent the choices the applicant makes to include
	 * 	in their Starter Kit package which will be mailed to them. Choices such as which 3 nail wrap styles
	 * 	were preferred, their name for a personal Jamberry web site, and so on.
	 */
	StarterKitPageFactory starterKitPage;
	
	/**
	 * 	A EnterBillingInfoPageFactory reference which will represent the applicants name, address, 
	 * 	credit card number and other relevant information for billing purposes for the Starter Kit purchase.
	 */
	EnterBillingInfoPageFactory billingInfoPage;
	
	/**
	 * 	Initial value of the loops through the testAll() method
	 */
	int invocationCount = 1;
	
	/**
	 * This method prepares the WebDriver object to talk to the Firefox browser. Instantiates page factory objects
	 * for the multiple web pages that must to navigated through for an applicant to become a 
	 * Jamberry consultant.
	 * @param None
	 * @return Nothing
	 * @throws Exception
	 */
	@BeforeSuite
	public void setUp() throws Exception {
		/***
		 * 
		 *  Setup a generic Firefox profile that is light weight 
		 *  for use in Automation testing in my local "QAAutomation" folder. My path just happens to be:
		 *  "C:\\Users\\JSteele\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\QAAutomation" but 
		 *  yours will vary. I don't want to use my default Firefox profile as there are additional extensions
		 *  and such that I don't care to have for automation testing, therefore it will be faster and
		 *  more reliable to use a light weight profile.
		 *    
		 */
		File file = new File(Constants.Firefox_Profile);
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		log.info("Just configured the Firefox profile and driver");
		

		/**
		 *  Creating a new object for the Consultant Join page. This calls the constructor of ConsultantPageFactory class. See that constructor
		 *  in ConsultantPageFactory.java 
		 */
		consultPage = new ConsultantPageFactory(driver);
		log.info("The consultPage object has been constructed");


		/**
		 *  Creating a new object for the Sponsor Search page. This calls the constructor of SponsorSearchPageFactory class. See that constructor
		 *  in SponsorSearchPageFactory.java
		 */
		sponsorSearchPage = new SponsorSearchPageFactory(driver);
		log.info("The sponsorSearchPage object has been constructed");
		
		/**
		 *  Creating a new object for the Starter Kit page. This calls the constructor of StarterKitPageFactory class. See that constructor
		 *  in StarterKitPageFactory.java
		 */
		starterKitPage = new StarterKitPageFactory(driver);
		log.info("The starterKitPage object has been constructed");
		
		/**
		 *  Creating a new object for the Enter Billing Information page. This calls the constructor of EnterBillingInfoPageFactory class. See that constructor
		 *  in EnterBillingInfoPage.java
		 */		
		billingInfoPage = new EnterBillingInfoPageFactory(driver);
		log.info("The billingInfoPage object has been contructed");
		
		/**
		 *	Maximize the browser window on startup and have an implicit wait of 15 seconds. Ideally this means
		 *	there should be no thread.sleep() delays and proper use of the Wait class according to good 
		 *	coding practices.
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		/**
		 *  Properties file used for logging, see log4j.properties file in the src folder of the project	
		 */
		log.info("About to configure the log4j properties file");
		PropertyConfigurator.configure("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");

		/**
		 * 	Load the initial page as the starting point for the test. 
		 */
		log.info("About to load the Consultant page");
		driver.get("https://www.dev.jamberry.com/us/en/consultant/");
		log.info("Got the url to start with");

	}
	
	/**
	 * Ensure we're starting on the right page just to make sure. The call to the referenceStartPage() method
	 * could be bypassed to save time if needed.
	 * @throws Exception
	 */
	@BeforeMethod
	public void testPrepBefore() throws Exception {
		log.info("About to call consultPage.referenceStartPage before the test has started");
		consultPage.referenceStartPage();
	}
	
	/**
	 * This is the main test method and will continually loop the number of times set in the
	 * TestNG @Test declaration variable "invocationCount".
	 * @param none
	 * @return nothing
	 * @throws Exception
	 */
	@Test(groups = { "functest", "checkintest" }, invocationCount=50)
	public void testAll() throws Exception {
		log.info("Just entered the method testAll()");
		log.info("Method invocationCount is: " + invocationCount++);
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();						// input a first name of random alpha characters
		consultPage.addLastName();						// input a last name of random alpha characters
		consultPage.addEmail(); 						// input an email adddress in the form of "JoinTestnnnnnn@test.com", where nnnnnn is a random #.
		consultPage.selLanguage();
		consultPage.selState(); 						// selects a random state/US province
		consultPage.selBirthDate(); 					// inputs a random birth date for day, month, and year
		consultPage.addPhoneNumber(); 					// inputs a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
//		consultPage.addSSN();							// inputs a bogus ssn where the first three numbers are zeros.
//		consultPage.checkBox();
		consultPage.clickEBIContinueButton();			// that's it for the initial basic info page, now on to select a sponsor
		sponsorSearchPage.clickNameSponsorControl();	// type the characters "Jes" into the search control to generate a list of sponsors whose first name beings with 'Jes'.
		sponsorSearchPage.selSponsors(); 				// randomly select 1 sponsor from the list 
		sponsorSearchPage.clickSponsorConfirmButton();	// now that the sponsor is selected move onto the starter kit page
		starterKitPage.clickSelectYour3WrapsButton();	
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); 				// input a random name for a personal website name
		starterKitPage.clickSaveStarterKitButton(); 	// completed filling out inof on the starter kit page, now onto billing
		billingInfoPage.enterFirstNameCreditCardInfo(); // inputs a first name of 12 random characters
		billingInfoPage.enterLastNameCreditCardInfo();	// inputs a last name of 16 random characters
		billingInfoPage.enterCreditCardNum();			// inputs a special innocuous test number of 4111111111111111
		billingInfoPage.enterExpirationMonth(); 		// inputs a random month from 1 to 12
		billingInfoPage.enterExpirationYear();			// inputs a random year from 2016 to 2026
		billingInfoPage.enterCCVCode();					// inputs the number 999, a valid innocuous test number 
		billingInfoPage.enterBillingAddr1();			// inputs random string and a number
		billingInfoPage.enterBillingAddr2();			// inputs random string and a number
		billingInfoPage.enterBillingCity();				// inputs a random string of 12 characters
		billingInfoPage.selState(); 					// select a random state from the dropdown control
		billingInfoPage.clickPolicyAgreementCheckBox();	
//		billingInfoPage.enterBillingZipCodeNZAU();		// inputs a zip code of 4 numbers "most of the time" sometimes 5 on purpose 
//		billingInfoPage.enterBillingZipCodeCanada();	// inputs a Canadian format zip code of the form "a1a 1a1"
		billingInfoPage.enterBillingZipCodeUSA();		// inputs a USA format zip code of 5 numbers, but sometimes 6 on purpose
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();			// after this step a new consultant has joined Jamberry
	}
	

	@AfterMethod
	public void tearDown() throws Exception {
//		log.info("About to call consultPage.referenceStartPage after a test has finished.");
//		consultPage.referenceStartPage();
//				Thread.sleep(5000);	
//				driver.quit();	// FYI: uncomment this line if you don't care that the browser stays up after test completion. However
								// resources will not be released.
	}

}
