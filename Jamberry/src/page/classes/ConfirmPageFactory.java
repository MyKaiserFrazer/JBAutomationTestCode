package page.classes;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

public class ConfirmPageFactory {
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor)driver;
	static Logger log = Logger.getLogger(ConfirmPageFactory.class);

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 * @throws Exception 
	 */
	public ConfirmPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for confirmPageFactory.");
	}
	
	@FindBy(xpath=".//*[@id='btnConfirm']")
	WebElement btnConfirm;

	public void clickConfirmButton() {
/*		// get the handle to the window
		String parentHandle = driver.getWindowHandle();
		// get the handle to all the windows
		Set<String> handles = driver.getWindowHandles();
		
		for (String handle: handles) {
			if(handle.equals(parentHandle)) {
				// do what is necessary
				log.info("In the For loop of clickConfirmButton()");
				btnConfirm.click();
				log.info("Clicked the Confirm button");
			
				// switch back to parent window
				driver.switchTo().window(parentHandle);
			} else {
				log.info("Not on the right window for some reason...");
			}
		}*/
		
		log.info("In the clickConfirmButton() method");
		String strTitle=driver.getTitle();
		log.info("The title of this window is : " + strTitle);
		
		log.info("About to call the checkPageIsReady() method");
		checkPageIsReady();
		log.info("Returned back from the checkPageIsReady() method");

		String strTitle1=driver.getTitle();
		log.info("The title of this window is now: " + strTitle);

//		WaitTypes.clickWhenReady(driver, By.id("btnConfirm"), 1);
		btnConfirm.click();
//		WaitTypes.clickWhenReady(driver, By.xpath("//div[@class='sponsor-confirm-toolbar']/a[@id='btnConfirm']"), 10);
		

	}
	
	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		// Initially below given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			   log.info("Page Is loaded.");
			   return; 
			  }
		
		// This loop will rotate for 25 times to check If page Is ready after every 1 second.
		// You can replace your value with 25 If you wants to Increase or decrease wait time.
		for(int i=0; i<25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			    break; 
			}
		}
	}
	
}
