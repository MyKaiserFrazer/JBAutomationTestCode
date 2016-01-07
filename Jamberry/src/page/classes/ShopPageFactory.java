package page.classes;

import utilities.*;
import utilities.WaitTypes;

import java.util.List;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

/**
 * public class ShopPageFactory
 * @author John Steele
 * This is where the nitty gritty work goes on for the ShopPageTestCase class which makes the code
 * cleaner and easier to read and maintain. 
 *
 */
public class ShopPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(ShopPageFactory.class);
	
	@FindBy(xpath="//div[@id='mainnav-collapse']//a[@title='Shop']")
	WebElement shopMenuItem;
	
	@FindBy(xpath="//li[@id='nail-wraps']/a")
	WebElement nailWrapsMenuItem;
	
	@FindBy(xpath="//li[@class='cart-btn mini-cart']//a[contains(@href,'/shop/cart')]")
	WebElement cartButton;
	
	@FindBy(xpath="//li[@class='login-btn']//a[contains(@href,'/shop/account')]")
	WebElement buttonBarLoginButton;
	
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
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[1]/div/div/div/a[1]")
	WebElement firstWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[2]/div/div/div/a[1]")
	WebElement secondWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[3]/div/div/div/a[1]")
	WebElement thirdWrapAddToCart;
	
	@FindBy(xpath="//input[@id='UserName']")
	WebElement loginUserNameEditControl;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement passwordEditControl;
	
	@FindBy(xpath="//input[@class='btn btn-success']")
	WebElement accountPageLoginButton;
	
	@FindBy(xpath="//li[@class='login-btn']//a[contains(@href,'/shop/myaccount')]")
	WebElement buttonBarMyAccountButton;

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
	 * This method navigates to a known place if not already there.
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
	public void clickNailWrapsMenu() {
		nailWrapsMenuItem.click();
		log.info("Just clicked the Nail Wraps menu item.");
	}
	
	/**
	 * The wraps on the Shop page change frequently so I will instead just pick the first one there.
	 */
	public void addFirstWrapToCart() {
		firstWrapAddToCart.click();
		log.info("Just added the first wrap on the Shop page to the cart.");
	}
	
	/**
	 * The wraps on the Shop page change frequently so I will instead just pick the second one there.
	 */
	public void addSecondWrapToCart() {
		secondWrapAddToCart.click();
		log.info("Just added the second wrap on the Shop page to the cart.");
	}
	
	/**
	 * The wraps on the Shop page change frequently so I will instead just pick the third one there.
	 */
	public void addThirdWrapToCart() {
		thirdWrapAddToCart.click();
		log.info("Just added the third wrap on the Shop page to the cart.");
	}
		
	/**
	 * Navigates to the Cart page.
	 */
	public void gotoCartPage() {
		cartButton.click();;
		log.info("Just clicked on the Cart button.");
	}
	
	public void clickButtonBarLoginButton() {
		buttonBarLoginButton.click();
		log.info("Just clicked the login button on the button-bar");
	}
	
	public void enterLoginUserID() {
		loginUserNameEditControl.clear();
		loginUserNameEditControl.sendKeys("tbigg");
		log.info("Just entered the userID");
	}
	
	public void enterPassword() {
		passwordEditControl.clear();
		passwordEditControl.sendKeys("Test123!");
		log.info("Just entered the password");
	}
	
	public void clickAccountPageLoginButton() {
		accountPageLoginButton.click();
		log.info("Just clicked the Login button on the account page");
	}
	
	public void clickMyAccountButtonBarButton() {
		buttonBarMyAccountButton.click();
		log.info("Just clicked the MyAccount button-bar button");
	}
	
	/**
	 * Select 3 random items from the default Shop page which is usually wraps. See referenceStartPage() 
	 * method above.
	 * @throws InterruptedException 
	 */
	public void shopSelect3Wraps() throws InterruptedException {
	Random randomno = new Random();
	WebElement element = driver.findElement(By.cssSelector(".col-md-9.col-sm-8>div")); // parent element
	
	List<WebElement> wraps = element.findElements(By.cssSelector(".tile"));
	// div.tile > div.footer > div.tools > a.add-cart-btn
	
	log.info("The number of wraps available are " + wraps.size());
	
	int maxSize = wraps.size(); // get the number of wraps that are IN STOCK
	int minSize = 1;
	
	int num1 = 	randomno.nextInt((maxSize-minSize)+1) + minSize; 	// get 3 random numbers to select 3 wraps of those that are IN STOCK
	int num2 =  randomno.nextInt((maxSize-minSize)+1) + minSize;
	int num3 =  randomno.nextInt((maxSize-minSize)+1) + minSize;
	log.info("The three random indexes to select wraps are: " + num1 + " " + num2 + " " + num3);
	
	WebElement tile1 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
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

	
	WebElement tile2 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
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

	WebElement tile3 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 30);
	if(tile3.isDisplayed())
		if(tile3.isEnabled()){
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

	
/*	WebElement tile1 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	tile1.click();
	log.info("Found and clicked the first randomly selected wrap.");
	WebElement tile2 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	tile2.click();
	log.info("Found and clicked the second randomly selected wrap.");
	WebElement tile3 = WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	tile3.click();
	log.info("Found and clicked the third randomly selected wrap.");
*/	
	
/*	WaitTypes.fluentWait(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)")).click();
	log.info("Found and clicked the first randomly selected wrap.");
	WaitTypes.fluentWait(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)")).click();
	log.info("Found and clicked the second randomly selected wrap.");
	WaitTypes.fluentWait(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)")).click();
	log.info("Found and clicked the third randomly selected wrap.");
*/

/*	WaitTypes.clickWhenReady(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	log.info("Found and clicked the first randomly selected wrap.");
//	Thread.sleep(3000);
	WaitTypes.clickWhenReady(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	log.info("Found and clicked the second randomly selected wrap.");
//	Thread.sleep(3000);
	WaitTypes.clickWhenReady(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10);
	log.info("Found and clicked the third randomly selected wrap.");
//	Thread.sleep(3000);
 */


/*	WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num1 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10).click();
	log.info("Found and clicked the first randomly selected wrap.");
	WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num2 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10).click();
	log.info("Found and clicked the second randomly selected wrap.");
	WaitTypes.getWhenVisible(driver, By.cssSelector("section#jbn-shop > div > div > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type("+ num3 +") > div > div > div:nth-of-type(2) > a:nth-of-type(1)"), 10).click();
	log.info("Found and clicked the third randomly selected wrap.");
*/
	
/*	wraps.get(num1).click();	// select the 3 wraps
	log.info("Found and clicked the first randomly selected wrap.");
	wraps.get(num2).click();
	log.info("Found and clicked the second randomly selected wrap.");
	wraps.get(num3).click();
	log.info("Found and clicked the third randomly selected wrap.");
*/
	}
	
}