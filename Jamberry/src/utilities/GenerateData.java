package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateData {

	public String generateRandomString(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public String generateCanadianZipCode(){

		String firstChar = "";
		String secondChar = "";
		String thirdChar = "";
		String fourthChar = " ";
		String fifthChar = "";
		String sixthChar = "";
		String seventhChar = "";
		String zipCA;
		
		firstChar = RandomStringUtils.randomAlphabetic(1);
		secondChar = RandomStringUtils.randomNumeric(1);
		thirdChar = RandomStringUtils.randomAlphabetic(1);
		fifthChar = RandomStringUtils.randomNumeric(1);
		sixthChar = RandomStringUtils.randomAlphabetic(1);
		seventhChar = RandomStringUtils.randomNumeric(1);
		
		zipCA = firstChar + String.valueOf(secondChar) + thirdChar + fourthChar + String.valueOf(fifthChar)
			+ sixthChar + String.valueOf(seventhChar);
		
		return zipCA;
	}
	
}
