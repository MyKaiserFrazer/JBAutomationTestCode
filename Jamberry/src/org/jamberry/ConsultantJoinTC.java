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

public class ConsultantJoinTC {
	static Logger log = Logger.getLogger(ConsultantJoinTC.class);
	private WebDriver driver;
	ConsultantPageFactory consultPage;
	SponsorSearchPageFactory sponsorSearchPage;
	StarterKitPageFactory starterKitPage;
	EnterBillingInfoPageFactory billingInfoPage;
	int invocationCount = 1;
	
	@BeforeSuite
	public void setUp() throws Exception {
		/**
		 *  Setup a generic Firefox profile for use in Automation testing in the "QAAutomation" folder.
		 */
		File file = new File(Constants.Firefox_Profile);
		FirefoxProfile profile = new FirefoxProfile(file);		
		driver = new FirefoxDriver(profile);
		log.info("Just configured the Firefox profile and driver");
		

		/**
		 *  Creating a new object for the Shop page. This calls the constructor of ShopPageFactory class. See that constructor
		 *  in ShopPageFactory.java 
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
		 *  in StarterKitPageFactory.java
		 */		
		billingInfoPage = new EnterBillingInfoPageFactory(driver);
		log.info("The billingInfoPage object has been contructed");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Properties file used for logging	
		log.info("About to configure the log4j properties file");
		PropertyConfigurator.configure("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\repos\\automationtestcode\\Jamberry\\src\\log4j.properties");
		log.info("About to load the Consultant page");
		driver.get("https://www.dev.jamberry.com/us/en/consultant/");
		log.info("Got the url to start with");

	}
	
	/**
	 * Ensure we're starting on the right page.
	 * @throws Exception
	 */
	@BeforeMethod
	public void testPrepBefore() throws Exception {
		log.info("About to call consultPage.referenceStartPage before the test has started");
		consultPage.referenceStartPage();
	}
	
	@Test(groups = { "functest", "checkintest" }, invocationCount=50)
	public void testAll() throws Exception {
		log.info("Just entered the method testAll()");
		log.info("Method invocationCount is: " + invocationCount++);
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.addSSN();
		consultPage.checkBox();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
//		billingInfoPage.enterBillingZipCodeNZAU();
//		billingInfoPage.enterBillingZipCodeCanada();
		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = { "functest" })
	public void testNoFirstName() throws Exception {
		log.info("Just entered the testNoFirstName() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = {"OneEmptyField"}, priority=2)
	public void testNoLastName() throws Exception {
		log.info("Just entered the testNoLastName() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
//		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = {"OneEmptyField"}, priority=3)
	public void testNoEmail() throws Exception {
		log.info("Just entered the testNoEmail() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
//		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = {"OneEmptyField"}, priority=4)
	public void testNoState() throws Exception {
		log.info("Just entered the testNoState() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
//		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = {"OneEmptyField"}, priority=5)
	public void testNoBirthDate() throws Exception {
		log.info("Just entered the testNoBirthDate() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
//		consultPage.selBirthDate(); // this is a random birth date
		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@Test(groups = {"OneEmptyField"}, priority=6)
	public void testNoPhoneNumber() throws Exception {
		log.info("Just entered the testNoPhoneNumber() test method");
		consultPage.clickGetStartedButton();
		consultPage.addFirstName();
		consultPage.addLastName();
		consultPage.addEmail();
		consultPage.selLanguage();
		consultPage.selState(); // this is random
		consultPage.selBirthDate(); // this is a random birth date
//		consultPage.addPhoneNumber(); // this is a random phone number
		consultPage.addPassword();
		consultPage.addPasswordConfirm();
		consultPage.clickEBIContinueButton();
		sponsorSearchPage.clickChooseDifferentSponsor();
		sponsorSearchPage.clickNameSponsorControl();
		sponsorSearchPage.selSponsors();
		sponsorSearchPage.clickSponsorConfirmButton();
		starterKitPage.clickSelectYour3WrapsButton();
		starterKitPage.select3Wraps();
		starterKitPage.clickSaveWrapsToCartButton();
		starterKitPage.addWebSiteAlias(); // random name for personal website
		starterKitPage.clickSaveStarterKitButton();
		billingInfoPage.enterFirstNameCreditCardInfo();
		billingInfoPage.enterLastNameCreditCardInfo();
		billingInfoPage.enterCreditCardNum();
		billingInfoPage.enterExpirationMonth();
		billingInfoPage.enterExpirationYear();
		billingInfoPage.enterCCVCode();
		billingInfoPage.enterBillingAddr1();
		billingInfoPage.enterBillingAddr2();
		billingInfoPage.enterBillingCity();
		billingInfoPage.selState();
		billingInfoPage.clickPolicyAgreementCheckBox();
		billingInfoPage.enterBillingZipCodeCanada();
//		billingInfoPage.enterBillingZipCodeUSA();
		billingInfoPage.clickBillingContinueButton();
		billingInfoPage.clickConfirmButton();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
//		log.info("About to call consultPage.referenceStartPage after a test has finished.");
//		consultPage.referenceStartPage();
		//		Thread.sleep(2000);
		//		driver.quit();
	}

}
