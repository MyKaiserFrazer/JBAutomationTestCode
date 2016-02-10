package page.classes;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitTypes;

/**
 * public class CartPageFactory
 * @author John Steele
 * <b>Description</b> This class defines the web elements that represent items in the cart. This is where 
 * the nitty gritty work goes on for the ShopPageTestCase class which makes the code in the main
 * test case cleaner and easier to read and maintain. 
 */
public class CartPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(CartPageFactory.class);

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 */
	public CartPageFactory(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for CartPageFactory.");
	}

	@FindBy(xpath="//table[@class='items-list']/tbody//tr[@class='item']")
	WebElement cartItems;	
	
	/**
	 * numItemsInCart() This method evaluates how many items have been placed in the cart.
	 * @throws Exception
	 */
	public void numItemsInCart() throws Exception {
		log.info("In the numItemsInCart method.");
//		Thread.sleep(6000); // Give the page some time (6-7 seconds) to load the items into the cart due to Knockout
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement crtEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='total align-r']/span[@data-bind='currencyDisplay: finalPrice'][contains(text(),'$')]")));
		
		List<WebElement> allElements = driver.findElements(By.cssSelector("tr[class='item']"));
		log.info("The number of Cart items found are: " + allElements.size());
		
/*		for(int i=0; i < allElements.size(); i++) {
			System.out.println(allElements.toString());	// for debug purposes
			
		}
*/
		
	}
	
}
