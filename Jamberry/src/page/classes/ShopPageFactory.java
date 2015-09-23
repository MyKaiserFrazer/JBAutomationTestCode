package page.classes;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShopPageFactory {
	WebDriver driver;
	public static WebElement element = null;
	static Logger log = Logger.getLogger(ShopPageFactory.class);
	static String refURL = "https://www.jamberry.com/au/en/shop/?collection=collection%3A%2F%2F1120";
	
	// constructor, initialize the @FindBy elements
	public ShopPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath=".//*[@id='mainnav-collapse']/ul/li[2]/a")
	WebElement shopMenuItem;
	
	@FindBy(xpath=".//*[@id='nail-wraps']/a")
	WebElement nailWrapsMenuItem;
	
	@FindBy(xpath="html/body/header/div[1]/ul[2]/li[3]/a")
	WebElement cartButton;
	
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

	// This method is to get to a known place (Shop page with nail wraps product displayed for now).
	public void referenceStartPage() {
		System.out.println("Current URL is: " + driver.getCurrentUrl());
		if(!refURL.equals(driver.getCurrentUrl())) {
			driver.get("https://www.jamberry.com/au/en/shop/");
			shopMenuItem.click();
			log.info("Just clicked on Shop menu item");
			
			nailWrapsMenuItem.click();
			log.info("Just clicked on the Nail Wraps menu item");	
		}
	}
	
	public void clickShopMenu() {
		shopMenuItem.click();
	}
	
	public void clickNailWrapsMenu() {
		nailWrapsMenuItem.click();
	}

}
