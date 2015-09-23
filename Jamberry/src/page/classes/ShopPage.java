package page.classes;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;



public class ShopPage {
	public static WebElement element = null;
	static Logger log = Logger.getLogger(ShopPage.class);
	
	// This method is to get to a known place (Shop page with nail wraps product displayed).
	public static void findReferencePage(WebDriver driver){
		System.out.println("Current URL is: " + driver.getCurrentUrl());
		if(!"https://www.jamberry.com/au/en/shop/?collection=collection%3A%2F%2F1120".equals(driver.getCurrentUrl())) {
			driver.get("https://www.jamberry.com/au/en/shop/");			
			element = driver.findElement(By.xpath(".//*[@id='mainnav-collapse']/ul/li[2]/a"));
			element.click();
			log.info("Just clicked on Shop menu item");

			element = driver.findElement(By.xpath(".//*[@id='nail-wraps']/a"));
			element.click();
			log.info("Just clicked on the Nail Wraps menu item");		
		}
	}
}
