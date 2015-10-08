package page.classes;

import utilities.Constants;
import utilities.*;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ConsultantPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(ConsultantPageFactory.class);
	
	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 */
	public ConsultantPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for ConsultantPageFactory.");
	}

	public void referenceStartPage() throws Exception {
		System.out.println("Current URL is: " + driver.getCurrentUrl());
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

	public void clickGetStartedButton() {
		btnGetStarted.click();
		log.info("Just clicked the Get Started button." );
	}
	
	public void addFirstName() {
		ctlFirstNameField.clear();
		ctlFirstNameField.sendKeys("John");
		log.info("Just entered the First name");
	}

	public void addLastName() {
		ctlLastNameField.clear();
		ctlLastNameField.sendKeys("O'Grady");
		log.info("Just entered the Last name");
	}

	public void addEmail() {
		ctlEmailField.clear();
		ctlEmailField.sendKeys("email@domain.com");
		log.info("Just entered the email address");
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
		int sizeMax = statesList.size();
		int randNum = randInt(1, sizeMax); // get my index to a State
		
		sel1.selectByIndex(randNum);
		log.info("Choosing the state " + randNum);		
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public void selBirthDate() {
		calBirthDate.click();
		String year, month, day;
		int yearNum = randInt(1950, 1997);
		int monthNum = randInt(1,12);
		int dayNum = randInt(1,31);
		
		calBirthDate.sendKeys(String.valueOf(monthNum) + "-" + String.valueOf(dayNum) + "-" + String.valueOf(yearNum));
		log.info("Just entered the birthdate");
	}
	
	public void addPhoneNumber() {
		
	}

}

