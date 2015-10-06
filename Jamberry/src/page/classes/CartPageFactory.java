package page.classes;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(CartPageFactory.class);
	
	public CartPageFactory(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for CartPageFactory.");
	}

	@FindBy(xpath="//table[@class='items-list']/tbody//tr[@class='item']")
	WebElement cartItems;	
	
	public void numItemsInCart() throws Exception {
		Thread.sleep(3000); // Give the page some time (3 seconds) to load the items into the cart due to Knockout
		List<WebElement> allElements = driver.findElements(By.cssSelector("tr[class='item']"));
		log.info("The number of Cart items found are: " + allElements.size());
		
		for(int i=0; i < allElements.size(); i++) {
			System.out.println(allElements.toString());
		}
		
	}

}
