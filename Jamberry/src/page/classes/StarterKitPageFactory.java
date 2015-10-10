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
	
	public void clickSelectYour3WrapsButton() {
		btnSelectYour3Wraps.click();
		log.info("Clicked on the Select Your 3 Wraps button");
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	
	public void select3Wraps() {
		WebElement element = driver.findElement(By.xpath("//div[@class='wraps-holder']")); // parent element
		
		List<WebElement> wraps = element.findElements(By.xpath("//div[@id='wraps-holder']/div[not(contains(@class,'wrap-outstock'))]"));
		
		log.info("The number of wraps available are " + wraps.size());
		
		int maxSize = wraps.size();
		int num1 = randInt(1,maxSize);
		int num2 = randInt(1,maxSize);
		int num3 = randInt(1,maxSize);
		
		

	}

}
