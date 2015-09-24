package org.jamberry;
import java.util.concurrent.TimeUnit;

import utilities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsingExcel {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testUsingExcel() throws Exception {
		// Navigation and click the header to see the page
		driver.get(Constants.URL);
		driver.findElement(By.id("tab-flight-tab")).click();
		Thread.sleep(2000);
		
		// Tell the code about the location of Excel file
		ExcelUtility.setExcelFile(Constants.File_Path + Constants.File_Name, "Sheet1");
		
		String origin = ExcelUtility.getCellData(1, 1);
		String destination = ExcelUtility.getCellData(1, 2);
	
		driver.findElement(By.id("flight-origin")).sendKeys(origin);
		driver.findElement(By.id("flight-destination")).sendKeys(destination);
		
		// Exercise - Read the departure and return date from excel and complete the code 
		
		// Set the test result in the Excel file
		ExcelUtility.setCellData("Pass", 1, 5);
	}
	
	@After
	public void tearDown() throws Exception {
	    //driver.quit();
	}
}
