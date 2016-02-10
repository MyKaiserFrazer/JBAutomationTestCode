package utilities;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * <b>GenerateData:</b> This class contains 2 methods for generating random data. The first generateRandomString() 
 * creates a random Canadian format zip code of the form "a1a 1a1". The second randInt() returns a random number
 * between the tow params int min and int max.
 * @version 1.0
 * @author John Steele
 */
public class GenerateData {

	public String generateRandomString(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	/**
	 * generateCanadianZipCode() generates a zip code in the Canadian format of "a1a 1a1". Each position whether
	 * alphabetical or numeric is randomly selected. Alphabetic characters are limited in scope based on Candian
	 * zip code rules.
	 * @return string zipCA
	 */
	public String generateCanadianZipCode(){

		String firstChar = "";
		String secondChar = "";
		String thirdChar = "";
		String fourthChar = " ";
		String fifthChar = "";
		String sixthChar = "";
		String seventhChar = "";
		String zipCA;

		// no D,F,I,O,Q,U and W or Z in first position
		firstChar = RandomStringUtils.random(1, "abceghjklmnprstvxyABCEGHJKLMNPRSTVXY"); 
		secondChar = RandomStringUtils.randomNumeric(1);
		thirdChar = RandomStringUtils.random(1, "abceghjklmnprstvwxyzABCEGHJKLMNPRSTVWXYZ");
		fifthChar = RandomStringUtils.randomNumeric(1);
		sixthChar = RandomStringUtils.random(1, "abceghjklmnprstvwxyzABCEGHJKLMNPRSTVWXYZ");
		seventhChar = RandomStringUtils.randomNumeric(1);
		
		zipCA = firstChar + String.valueOf(secondChar) + thirdChar + fourthChar + String.valueOf(fifthChar)
			+ sixthChar + String.valueOf(seventhChar);
		
		return zipCA;
	}
	
	/**
	 * randInt() takes two int values min and max and returns a random int value between the two bounds. I use
	 * this mostly for getting the total number of nail wraps visible on the default shop page and then select 
	 * nail wraps based on that random number. 
	 * @param int min
	 * @param int max
	 * @return int randomNum
	 */
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum; 
	}
	
}
