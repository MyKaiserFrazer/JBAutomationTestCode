package page.classes;

import utilities.Constants;
import utilities.WaitTypes;
import utilities.GenerateData;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * public class ConsultantPageFactory
 * @author John Steele
 * <b>Description</b> This class defines the web elements that a user would interact with when entering their
 * basic information such as first and last names, email address, State, birthdate, phone, and password. The
 * methods provide for input of random strings of characters or numbers in these web elements.
 *
 */
public class ConsultantPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(ConsultantPageFactory.class);
	
	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 * @throws Exception 
	 */
	public ConsultantPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for ConsultantPageFactory.");
	}

	/**
	 * Ensure I'm still on the right page.
	 * @throws Exception
	 */
	public void referenceStartPage() throws Exception {
		log.info("Current URL is: " + driver.getCurrentUrl());
		if(!Constants.Reference_cpiURL.equals(driver.getCurrentUrl())) {
			driver.get(Constants.cpiURL);	// opens the web page.
			log.info("On the base URL");
		}
	}

	@FindBy(xpath="//a[@class='btn btn-primary']")
	WebElement btnGetStarted;
	
	@FindBy(id="nameFirst")
	WebElement ctlFirstNameField;

	@FindBy(id="nameLast")
	WebElement ctlLastNameField;
	
	@FindBy(id="Email")
	WebElement ctlEmailField;
	
	@FindBy(id="state")
	WebElement ddnCtlState;
	
	@FindBy(id="BirthDate")
	WebElement calBirthDate;
	
	@FindBy(id="Phone")
	WebElement ctlPhoneField;
	
	@FindBy(id="Password")
	WebElement ctlPasswordField;

	@FindBy(id="PasswordConfirm")
	WebElement ctlPasswordConfirmField;
	
	@FindBy(xpath="//button[@id='continue-btn']")
	WebElement btnContinueEnterBasicInfo;
	
	@FindBy(xpath="//input[@id='TaxId']")
	WebElement ssnField;
	
	@FindBy(xpath="//a[@href='#']")
	WebElement chkBox;

	public void clickGetStartedButton() {
		btnGetStarted.click();
		log.info("Just clicked the Get Started button." );
	}
	
/*	public void clearAllInputFields() {
		log.info("About to clear all input fields");
		ctlFirstNameField.clear();
		ctlLastNameField.clear();
		ctlEmailField.clear();
		ddnCtlState.clear();
		calBirthDate.clear();
		ctlPhoneField.clear();
		ctlPasswordField.clear();
		ctlPasswordConfirmField.clear();
		log.info("Just cleared all input fields");
	}
*/	
	public void addFirstName() {
		ctlFirstNameField.clear();
		String firstName = new GenerateData().generateRandomString(19);
		ctlFirstNameField.sendKeys(firstName);
		log.info("Just entered a random First name with 19 characters: " + firstName);
	}

	public void addLastName() {
		ctlLastNameField.clear();
		String lastName = new GenerateData().generateRandomString(23);
		ctlLastNameField.sendKeys(lastName);
		log.info("Just entered a random Last name with 23 characters: " + lastName);
	}

	public void addEmail() {
		ctlEmailField.clear();
		int ran;
		ran = 100 + (int)(Math.random()*((1000000-100)+1));
		driver.findElement(By.id("Email")).sendKeys("JoinTest" + ran + "@test.com");
//		ctlEmailField.sendKeys("email@domain.com");
		log.info("Just entered the email address: " + "JoinTest" + ran + "@test.com");
	}
	
	public void selLanguage() {
		Select sel1 = new Select(driver.findElement(By.id("language")));
		List<WebElement> options = sel1.getOptions();
		
		int size = options.size();
		if(size == 1) {
			sel1.selectByVisibleText("English");
		}
		log.info("Just selected language");
			
	}
	
	public void selState() {
		// Get a Select tag so as to select an option
		Select sel1 = new Select(driver.findElement(By.id("state")));

		// Find all the options in the State dropdown control
		List<WebElement> statesList = sel1.getOptions();
		
		// Get the number of options and randomly pick one
		int randNum = 0;
		int sizeMax = statesList.size();
		
		// The province of Quebec (#11) is not open to Jamberry at this time
		if (((randNum = randInt(1, sizeMax)) == 11)) {
			randNum++;
		}
	
		sel1.selectByIndex(randNum);
		log.info("Choosing the state in the initial Consultant info form - " + sel1.getOptions().get(randNum).getText());		
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum + 1; // can't have a month or day that is zero
	}
	
	public void selBirthDate() {
		int yearNum = (Constants.baseYear + randInt(1950, 1997));
		int monthNum = randInt(1,12);
		int dayNum = randInt(1,31);
		
		log.info("The random numbers used for the birthdate are: " + monthNum + " " + dayNum + " " + yearNum);
		
		calBirthDate.sendKeys(String.valueOf(monthNum) + "-" + String.valueOf(dayNum) + "-" + String.valueOf(yearNum) + Keys.ENTER);
		log.info("Just entered the birthdate");
	}
	
	public void addPhoneNumber() {
		ctlPhoneField.click();
		ctlPhoneField.clear();
		int areaCode = randInt(200,999);
		int cityCode = randInt(200,999);
		int mainCode = randInt(1000,9999);
		log.info("The phone number should be: " + areaCode + "." + cityCode + "-" + mainCode);
		ctlPhoneField.sendKeys("+1(" + String.valueOf(areaCode) + ")." + String.valueOf(cityCode) + "-" + 
				String.valueOf(mainCode));
	}
	
	public void addPassword() {
		ctlPasswordField.clear();
		ctlPasswordField.sendKeys("Test123!");
		log.info("Entered the password");
	}
	
	public void addPasswordConfirm() {
		ctlPasswordConfirmField.clear();
		ctlPasswordConfirmField.sendKeys("Test123!");
		log.info("Confirmed the password");
	}
	
	public void addSSN() {
		// ssnField.clear();
		ssnField.sendKeys("000234567");
		log.info("entered bogus ssn #");
	}
	
	public void checkBox() {
		chkBox.click();
		log.info("just clicked the ssn check box");
	}

	public void clickEBIContinueButton() throws InterruptedException {
		log.info("About to click the Continue button");
		btnContinueEnterBasicInfo.click();
		Thread.sleep(2000);		// I will eventually want to remove this and all sleep() methods and go with Wait types.
		log.info("Clicked the Continue button");
//		WaitTypes.clickWhenReady(driver, By.xpath("//button[@id='continue-btn']"), 30);
	}

}

