package page.classes;

import utilities.*;
import utilities.GenerateData;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		loginUserNameEditControl.sendKeys("testca@test.com");
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
	 */
	public void shopSelect3Wraps() {
	WebElement element = driver.findElement(By.xpath("//div[@data-reactid='.0.0.0.1.0']")); // parent element
	
	List<WebElement> wraps = element.findElements(By.xpath("//div[@class='row'][@data-reactid='.0.0.0.1.0.1']//a[@class='add-cart-btn']"));
	
	log.info("The number of wraps available are " + wraps.size());
	
	int maxSize = wraps.size(); // get the number of wraps that are IN STOCK
	
	int num1 = GenerateData.randInt(0,maxSize);	// get 3 random numbers to select 3 wraps of those that are IN STOCK
	int num2 = GenerateData.randInt(0,maxSize);
	int num3 = GenerateData.randInt(0,maxSize);
	log.info("The three random indexes to select wraps are: " + num1 + " " + num2 + " " + num3);
	
	wraps.get(num1).click();	// select the 3 wraps
	wraps.get(num2).click();
	wraps.get(num3).click();
	}
	
}