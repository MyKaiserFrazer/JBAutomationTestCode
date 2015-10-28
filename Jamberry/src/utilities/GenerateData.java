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
		
		firstChar = RandomStringUtils.random(1, "abceghjklmnprstvxyABCEGHJKLMNPRSTVXY"); // no D,F,I,O,Q,U and W or Z in first position
		secondChar = RandomStringUtils.randomNumeric(1);
		thirdChar = RandomStringUtils.random(1, "abceghjklmnprstvwxyzABCEGHJKLMNPRSTVWXYZ");
		fifthChar = RandomStringUtils.randomNumeric(1);
		sixthChar = RandomStringUtils.random(1, "abceghjklmnprstvwxyzABCEGHJKLMNPRSTVWXYZ");
		seventhChar = RandomStringUtils.randomNumeric(1);
		
		zipCA = firstChar + String.valueOf(secondChar) + thirdChar + fourthChar + String.valueOf(fifthChar)
			+ sixthChar + String.valueOf(seventhChar);
		
		return zipCA;
	}
	
}
