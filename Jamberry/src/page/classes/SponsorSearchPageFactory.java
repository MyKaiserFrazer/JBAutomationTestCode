package page.classes;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SponsorSearchPageFactory {
	WebDriver driver;
	static Logger log = Logger.getLogger(SponsorSearchPageFactory.class);

	/**
	 * constructor, also initializes the @FindBy elements with Selenium PageFactory class
	 * @param driver
	 * @throws Exception 
	 */
	public SponsorSearchPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("Just called PageFactory.initElements for SponsorSearchPageFactory.");
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum;
	}
	
	@FindBy(xpath="//input[@id='name-sponsor-search']")
	WebElement ctlNameSponsorSearch;
	
	@FindBy(xpath="//a[@class='btn btn-info']")
	WebElement btnChooseDifferentSponsor;
	
	@FindBy(xpath="//div[@class='sponsor-confirm-toolbar']/a[@class='btn btn-primary']")
	WebElement btnSponsorConfirm;

	
	public void clickNameSponsorControl() {
		ctlNameSponsorSearch.clear();
		ctlNameSponsorSearch.click();
		ctlNameSponsorSearch.sendKeys("Eli");
		ctlNameSponsorSearch.sendKeys(Keys.ENTER);
		log.info("Just entered the search string \"Eli\" and clicked the NameSponsorSearch button." );
	}
	
	public void clickChooseDifferentSponsor() {
		btnChooseDifferentSponsor.click();
	}

	public void selSponsors() {
		log.info("In the selSponsors method");
		int sizeMax = -1;
		
		/**
		 * Find the parent container that has the search results of sponsors.
		 */
		WebElement element = driver.findElement(By.xpath("//div[@id='search-results']")); // parent element
		
		/**
		 * Locate the Select Sponsor buttons on each of the search results
		 */
		List<WebElement> rows = element.findElements(By.xpath("//div[@id='search-results']/div[@class='col-sm-12 sponsor-info-container']"
				+ "/div[@class='sponsor-info']//div[@class='row']/div[@class='col-lg-2 col-xs-12']/a[@class='btn btn-sm btn-primary select-sponsor']"));
		
/*		for(int i=0; i<rows.size(); i++) {
			log.info("Inside the For loop");
			log.info(rows.get(i));
		}
*/		
		sizeMax =rows.size(); // get the count of search results on the sponsor-search page
		int randomIndex = randInt(0,sizeMax); // 
		log.info("The randomIndex value is: " + randomIndex);
		
		rows.get(randomIndex).click();
	}
	
	public void clickSponsorConfirmButton() {
		btnSponsorConfirm.click();
	}
	
}

