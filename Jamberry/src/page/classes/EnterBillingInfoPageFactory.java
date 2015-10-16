package page.classes;

import java.util.List;
import java.util.Random;

import utilities.Constants;
import utilities.GenerateData;
import utilities.WaitTypes;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;

public class EnterBillingInfoPageFactory {
	
	WebDriver driver;
	static Logger log = Logger.getLogger(EnterBillingInfoPageFactory.class);

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 * @throws Exception 
	 */
	public EnterBillingInfoPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for EnterBillingInfoPageFactory.");
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum + 1; // can't have a month or day that is zero
	}

	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement ctlCCFirstName;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement ctlCCLastName;
	
	@FindBy(xpath="//div[@id='payment-number']")
	WebElement ctlCCNum;
	
	@FindBy(xpath="//div[@id='payment-expiration-month']")
	WebElement ctlPmtExpireMonth;
	
	@FindBy(xpath="//div[@id='payment-cvv']")
	WebElement ctlPmtCvv;
	
	@FindBy(xpath="//input[@id='BillingAddress_Address1']")
	WebElement ctlBillingAddr1;

	@FindBy(xpath="//input[@id='BillingAddress_Address2']")
	WebElement ctlBillingAddr2;

	@FindBy(xpath="//input[@id='BillingAddress_Locality']")
	WebElement ctlBillingCity;
	
	@FindBy(xpath="//input[@id='BillingAddress_PostalCode']")
	WebElement ctlBillingZipCode;

	@FindBy(xpath="//div[@class='terms-checkbox']/a")
	WebElement ctlPolicyAgreementChkBox;

	@FindBy(id="btnConfirm")
	WebElement btnConfirm;

	public void enterFirstNameCreditCardInfo(){
		String ccFirstName = new GenerateData().generateRandomString(12);
		ctlCCFirstName.sendKeys(ccFirstName);
		log.info("Entered firstname for creditcard information");
	}
	
	public void enterLastNameCreditCardInfo(){
		String ccLastName = new GenerateData().generateRandomString(16);
		ctlCCLastName.sendKeys(ccLastName);
		log.info("Entered lastname for creditcard information");
	}
	
	public void enterCreditCardNum(){
//		WaitTypes.clickWhenReady(driver, By.xpath("//div[@id='payment-number']"), 5);
		WebElement creditCardControl = WaitTypes.fluentWait(driver, By.xpath("//div[@id='payment-number']"));
		creditCardControl.sendKeys("4111111111111111");
		log.info("Entered creditcard number");
	}
	
	public void enterExpirationMonth(){
		int monthNum = randInt(1,12);
		ctlPmtExpireMonth.sendKeys(String.valueOf(monthNum));
		log.info("Entered expiration month");
	}
	
	public void enterExpirationYear(){
		int yearNum = Constants.baseExpireYear + randInt(2015, 2025);
		ctlPmtExpireMonth.sendKeys(String.valueOf(yearNum));
		log.info("Entered expiration year: " + yearNum);
	}
	
	public void enterCCVCode(){
		ctlPmtCvv.sendKeys("999");
		log.info("Entered CVV code 999");
	}
	
	public void enterBillingAddr1(){
		String strBillingAddr1a = new GenerateData().generateRandomString(10);
		String strBillingAddr1b = new GenerateData().generateRandomString(4);
		String strBillingAddr1c = new GenerateData().generateRandomString(3);
		int addrNum = randInt(0,999);
		
		// build a funky random address string
		ctlBillingAddr1.sendKeys(strBillingAddr1a + "" + strBillingAddr1b + "" + strBillingAddr1c + " " + addrNum);
		log.info("Entered a random address string into Address 1 field");
	}
	
	public void enterBillingAddr2(){
		String strBillingAddr2a = new GenerateData().generateRandomString(30);
		int addrNum = randInt(0,999);
		
		// build a funky random address string
		ctlBillingAddr2.sendKeys(strBillingAddr2a + "" + " " + addrNum);
		log.info("Entered a random address string into Address 2 field");
	}
	
	public void enterBillingCity(){
		String strBillingCity= new GenerateData().generateRandomString(10);
		
		// build a random city name string
		ctlBillingCity.sendKeys(strBillingCity);
		log.info("Entered a random city name string into City field");
	}
	
	public void selState() {
		// Get a Select tag so as to select an option
		Select sel1 = new Select(driver.findElement(By.id("BillingAddress_Region")));

		// Find all the options in the State dropdown control
		List<WebElement> statesList = sel1.getOptions();
		
		// Get the number of options and randomly pick one
		int sizeMax = statesList.size();
		int randNum = randInt(1, sizeMax); // get my index to a State
	
		sel1.selectByIndex(randNum);
		log.info("Choosing the state in the Billing Address form: " + randNum);		
	}
	
	public void enterBillingZipCode(){
		int zipNum = randInt(10000, 109999);
		ctlBillingZipCode.sendKeys(String.valueOf(zipNum));		
		log.info("Entered the zip code in the Billing Address form");
	}
	
	public void clickPolicyAgreementCheckBox(){
		ctlPolicyAgreementChkBox.click();
		log.info("Checked the Policy/Agreement/Consent/Privacy checkbox");
	}
	
	public void clickConfirmButton(){
		btnConfirm.click();
		log.info("Clicked the Confirm button");
	}
}