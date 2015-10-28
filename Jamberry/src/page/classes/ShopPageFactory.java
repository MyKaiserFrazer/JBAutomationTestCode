package page.classes;

import utilities.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * public class ShopPageFactory
 * @author John Steele
 * This is where the nitty gritty work goes on for the ShopPageTestCase class which makes the code
 * cleaner and ea 
 *
 */
public class ShopPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(ShopPageFactory.class);
	
	@FindBy(xpath="//*[@id='mainnav-collapse']//a[@title='Shop']")
	WebElement shopMenuItem;
	
	@FindBy(xpath=".//*[@id='nail-wraps']/a")
	WebElement nailWrapsMenuItem;
	
	@FindBy(xpath="//li[@class='cart-btn mini-cart']//a[contains(@href,'/shop/cart')]")
	WebElement cartButton;
	
	@FindBy(xpath="//li[@class='login-btn']//a[contains(@href,'/shop/account')]")
	WebElement buttonBarLoginButton;
	
	@FindBy(name="search-btn")
	WebElement searchButton;
	
	@FindBy(id="locale-dropdown")
	WebElement localeButton;
	
	@FindBy(xpath=".//*[@id='lacquers']/a")
	WebElement lacquersMenuItem;
	
	@FindBy(xpath=".//*[@id='nail-care']/a")
	WebElement handNailCareMenuItem;
	
	@FindBy(xpath=".//*[@id='gelenamel']/a")
	WebElement gelEnamelMenuItem;
	
	@FindBy(xpath="//a[@class='add-cart-btn'][contains(@data-reactid,'butterfly-kisses')]")
	WebElement butterflyKissesWrapAddToCart;
	
	@FindBy(xpath="//a[@class='add-cart-btn'][contains(@data-reactid,'leo-geo-lace')]")
	WebElement leoGeoLaceWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[1]/div/div/div/a[1]")
	WebElement firstWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[2]/div/div/div/a[1]")
	WebElement secondWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='jbn-shop']/div/div/div[2]/div/div[2]/div[3]/div/div/div/a[1]")
	WebElement thirdWrapAddToCart;
	
	@FindBy(xpath=".//*[@id='UserName']")
	WebElement loginUserNameEditControl;
	
	@FindBy(xpath=".//*[@id='Password']")
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
			shopMenuItem.click();
			log.info("Just clicked on Shop menu item");
			
			nailWrapsMenuItem.click();
			log.info("Just clicked on the Nail Wraps menu item");	
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
	 * Adds the Butterfly Kisses wrap to the Cart.
	 */
	public void addButterflyKissesWrapToCart() {
		butterflyKissesWrapAddToCart.click();
		log.info("Just added the Butterfly-Kisses wrap to the cart.");
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
	 * Adds the Leo, Geo & Lace type wrap to the Cart.
	 */
	public void addLeoGeoLaceWrapToCart() {
		leoGeoLaceWrapAddToCart.click();
		log.info("Just added the Leo, Geo & Lace wrap to the cart.");
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
		loginUserNameEditControl.sendKeys("joseywales@test.com");
		log.info("Just entered the userID");
	}
	
	public void enterPassword() {
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
	
	
/*		double expectedMerchandiseTotal = 44.00;
		// String actualMerchandiseTotal;
		
		
		// get the window handle
		String parentHandle = driver.getWindowHandle();
		
		// get all windows handles.
		Set<String> handles = driver.getWindowHandles();
		
		for(String handle: handles){
			if(!handle.equals(parentHandle)) {
				// switch to new window
				driver.switchTo().window(handle);
				
				// do the asserts on merchandise Total, shipping price
				// and total-before-tax.
				actualMerchandiseTotal = driver.findElement(By.xpath("//div[@class='cart-totals']//td[@data-bind='currencyDisplay: merchandiseTotal']")).getText();
				
			}
		}
		
		
	}
*/
}