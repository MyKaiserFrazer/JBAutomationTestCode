package page.classes;

import utilities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.interactions.Actions;


/**
 * public class ShopPageFactory
 * @author John Steele
 * This class defines the web elements that a consumer user would interact with on the default shop page (which is 
 * generall the Nail Wraps page). Methods are provide for a test user accoutn (tbigg) to login with a default
 * password (Test123) and then shop for 3 randomly selected nail wraps and add them to the cart. Basically this is
 * where the nitty gritty work goes on for the ShopPageTestCase class which makes the code cleaner and easier to
 *  read and maintain.
 *   
 */
public class ShopPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(ShopPageFactory.class);
	
	@FindBy(xpath="//div[@id='mainnav-collapse']//a[@title='Shop']")
	WebElement shopMenuItem;
	
	@FindBy(xpath="//a[@title='Nail Wraps'][text()='Nail Wraps']")
	WebElement nailWrapsSubmenuItem;
	
	@FindBy(xpath="//li[@class='cart-btn mini-cart']//a[contains(@href,'/shop/cart')]")
	WebElement cartButton;
	
	@FindBy(xpath="//li[@class='dropdown']//a[@id='login-dropdown']")
	WebElement buttonBarLoginButton;
	
	@FindBy(xpath="//a[@href='/us/en/shop/account'][text()='Customer Login']")
	WebElement customerLoginMenuItem;
	
	@FindBy(css=".search-btn>a")
	WebElement searchButton;
	
	@FindBy(id="locale-dropdown")
	WebElement localeButton;
	
	@FindBy(xpath="//li[@id='lacquers']/a")
	WebElement lacquersMenuItem;
	
	@FindBy(xpath="//li[@id='nail-care']/a")
	WebElement handNailCareMenuItem;
	
	@FindBy(xpath="//li[@id='gelenamel']/a")
	WebElement gelEnamelMenuItem;
	
	@FindBy(xpath="//input[@id='UserName']")
	WebElement loginUserNameEditControl;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement passwordEditControl;
	
	@FindBy(xpath="//input[@class='btn btn-success']")
	WebElement accountPageLoginButton;
	
	@FindBy(xpath="//li[@class='login-btn']//a[contains(@href,'/us/en/shop/myaccount')]")
	WebElement MyAccountSubmenuItem;

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 */
	public ShopPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for ShopPageFactory.");
	}
	
	/**
	 * This method navigates to a known place if not already there. Use of this method may not be necessary
	 * but I include it just as extra assurance we're starting the test on the expected page.
	 *  (Shop page with nail wraps product displayed)
	 */	
	public void referenceStartPage() {
		System.out.println("Current URL is: " + driver.getCurrentUrl());
		if(!Constants.Reference_URL.equals(driver.getCurrentUrl())) {
			driver.get(Constants.URL);	// opens the web page.
			log.info("On the base URL");
		}
	}
	
	/**
	 * Selects the Shop main menu item.
	 */
	public void clickShopMenu() {
		shopMenuItem.click();
		log.info("Just clicked the Shop Menu item." );
	}
	
	/**
	 * Selects the Nail Wraps menu item.
	 */
	public void clickNailWrapsSubmenu() {
		nailWrapsSubmenuItem.click();
		log.info("Just clicked the Nail Wraps menu item.");
	}
		
	/**
	 * Navigates to the Cart page.
	 */
	public void gotoCartPage() {
		cartButton.click();
		log.info("Just clicked on the Cart button.");
	}
	
	public void clickButtonBarLoginButton() {
		buttonBarLoginButton.click();
		log.info("Just clicked the login button on the button-bar");
	}
	
	public void clickCustomerLoginMenuItem() {
		customerLoginMenuItem.click();
		log.info("Just clicked the Customer Login submenu item.");
	}
	
	public void enterLoginUserID() {
		loginUserNameEditControl.clear();
		loginUserNameEditControl.sendKeys("tbigg");
		log.info("Just entered the userID");
	}
	
	public void enterPassword() {
		passwordEditControl.clear();
		passwordEditControl.sendKeys("Test123");
		log.info("Just entered the password");
	}
	
	public void clickAccountPageLoginButton() {
		accountPageLoginButton.click();
		log.info("Just clicked the Login button on the account page");
	}
	
	public void clickMyAccountSubmenuItem() {
		MyAccountSubmenuItem.click();
		log.info("Just clicked the MyAccount button-bar button");
	}
	
	/**
	 * Method: shopSelect3Wraps()
	 * Selects 3 random nail wrap items from the default Shop page which is usually nail wraps. 
	 * @throws InterruptedException 
	 */
	public void shopSelect3Wraps() throws InterruptedException {
	// Get the parent element for the tiles on the default shop page.
	WebElement element = driver.findElement(By.cssSelector(".col-md-9.col-sm-8>div")); // parent element

	// I'm trying to eliminate the need for this sleep() as it is not good programming practice in automation code.
	Thread.sleep(500);	// I need to work without this somehow??

	// Build the list of wraps which are on the default shop page
	List<WebElement> wraps = element.findElements(By.cssSelector(".tile"));
	
	// If execution gets here there is a problem most likely! Don't comment out the Thread.sleep(500) above.
	while (!(wraps.size()>1)){
		Thread.sleep(100);
		log.info("wraps.size() = " + wraps.size());
	}
	log.info("The number of wraps available are " + wraps.size());
	
	int maxSize = wraps.size(); // get the number of wraps that are IN STOCK
	int minSize = 1;

	/**
	 * Get 3 random numbers to select 3 nail wraps of those that are IN STOCK. If the default page happens to
	 * have no nail wraps listed, this will timeout. tbd.
	 * Add 1 to avoid an index=0 situation.
	 */
	int num1 = GenerateData.randInt(minSize, maxSize) + 1;
	int num2 = GenerateData.randInt(minSize, maxSize) + 1;
	int num3 = GenerateData.randInt(minSize, maxSize) + 1;
	log.info("The three random indexes to select wraps are: " + num1 + " " + num2 + " " + num3);
	
	/**
	 * Define these 3 elements the traditional way since we're inside a method.
	 */
	WebElement firstWrap = driver.findElement(By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"));
	log.info("Built the element firstWrap");
	WebElement secondWrap = driver.findElement(By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"));
	log.info("Built the element secondWrap");
	WebElement thirdWrap = driver.findElement(By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"));
	log.info("Built the element thirdWrap");

	
	/**
	 * *** Runs some Javascript code to scroll the window or viewport to the element we need to click on. 
	 * Selenium Webdrive doesn't have a provision to do this so we need some help from Javascript. ***
	 */
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", firstWrap);
	firstWrap.click();
	log.info("Clicked on the first wrap.");
	
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", secondWrap);
	secondWrap.click();
	log.info("Clicked on the second wrap.");

	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", thirdWrap);
	thirdWrap.click();
	log.info("Clicked on the third wrap.");
	}


	/**
	 * Debug code to take a screen shot of the page as item #1 of 3 is selected to go in the cart.
	 */
	/*	WebElement tile1 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
	if(tile1.isDisplayed())
		if(tile1.isEnabled()){
			log.info("About to take the before snapshot for Tile1");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\before1.png"));
			} catch(Exception e) {e.printStackTrace();}
			tile1.click();
			log.info("Found and clicked the first randomly selected wrap.");
			log.info("About to take the after snapshot for Tile1");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\after1.png"));
			} catch(Exception e) {e.printStackTrace();}
		}else log.info("Tile1 is not Enabled yet");
	else log.info("Tile1 is not displayed yet");
	*/
	
	/**
	 * Debug code to take a screen shot of the page as item #2 of 3 is selected to go in the cart.
	 */
	/* WebElement tile2 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
	if(tile2.isDisplayed())
		if(tile2.isEnabled()){
			log.info("About to take the before snapshot for Tile2");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\before2.png"));
			} catch(Exception e) {e.printStackTrace();}
			tile2.click();
			log.info("Found and clicked the second randomly selected wrap.");
			log.info("About to take the after snapshot for Tile2");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\after2.png"));
			} catch(Exception e) {e.printStackTrace();}
		}else log.info("Tile2 is not Enabled yet");
	else log.info("Tile2 is not displayed yet");
	*/
	
	/**
	 * Debug code to take a screen shot of the page as item #3 of 3 is selected to go in the cart.
	 */
	/* WebElement tile3 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
	if(tile3.isDisplayed())
		if(tile3.isEnabled()){
			log.info("About to take the before snapshot for Tile3");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\before3.png"));
			} catch(Exception e) {e.printStackTrace();}
			tile3.click();
			log.info("Found and clicked the third randomly selected wrap.");
			log.info("About to take the after snapshot for Tile3");
			try {
				File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrShot, new File("C:\\Users\\JSteele.DESKTOP-6HUUN3S\\Documents\\screenShots\\after3.png"));
			} catch(Exception e) {e.printStackTrace();}
		}else log.info("Tile3 is not Enabled yet");
	else log.info("Tile3 is not displayed yet");
*/
}
