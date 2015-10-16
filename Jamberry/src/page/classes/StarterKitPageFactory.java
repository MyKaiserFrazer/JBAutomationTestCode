package page.classes;

import java.util.List;
import java.util.Random;
import utilities.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.lang3.RandomStringUtils;

public class StarterKitPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(StarterKitPageFactory.class);

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 * @throws Exception 
	 */
	public StarterKitPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for StarterKitPageFactory.");
	}
	
	@FindBy(xpath="//a[contains(text(),'Select your 3 wraps')]")
	WebElement btnSelectYour3Wraps;
	
	@FindBy(xpath="//button[@class='continue-button btn btn-primary']")
	WebElement btnSaveWrapsToCart;
	
	@FindBy(id="WebsiteAlias")
	WebElement ctlWebSiteAlias;
	
	@FindBy(xpath="//input[@id='continue-btn']")
	WebElement btnSaveStarterKit;
	
	public void clickSelectYour3WrapsButton() {
		btnSelectYour3Wraps.click();
		log.info("Clicked on the Select Your 3 Wraps button");
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum;
	}

	
	public void select3Wraps() {
		WebElement element = driver.findElement(By.xpath("//div[@class='wraps-holder']")); // parent element
		
		List<WebElement> wraps = element.findElements(By.xpath("//div[@id='wraps-holder']/div[not(contains(@class,'wrap-outstock'))]"));
		
		log.info("The number of wraps available are " + wraps.size());
		
		int maxSize = wraps.size(); // get the number of wraps that are IN STOCK
		
		int num1 = randInt(0,maxSize);	// get 3 random numbers to select 3 wraps of those that are IN STOCK
		int num2 = randInt(0,maxSize);
		int num3 = randInt(0,maxSize);
		log.info("The three random indexes to select wraps are: " + num1 + " " + num2 + " " + num3);
		
		wraps.get(num1).click();	// select the 3 wraps
		wraps.get(num2).click();
		wraps.get(num3).click();
	}
	
	public void clickSaveWrapsToCartButton() throws Exception {
		btnSaveWrapsToCart.click();
		Thread.sleep(3000); // for demo purposes
	}
	
	public void addWebSiteAlias() {
		String siteAlias = new GenerateData().generateRandomString(8);
		ctlWebSiteAlias.sendKeys(siteAlias);
		log.info("Just entered the website alias name: " + siteAlias);
	}
	
	public void clickSaveStarterKitButton(){
		btnSaveStarterKit.click();
		log.info("Just clicked the Save Starter Kit button");
	}

}
