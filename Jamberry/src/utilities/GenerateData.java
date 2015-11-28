package utilities;

import java.util.Random;

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
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max - min);

	    return randomNum; 
	}
	
	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for(int i=0; i<length; i++) {
			int index = (int)(Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
	
	public static String getRandomInt(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "0123456789";
		for(int i=0; i<length; i++) {
			int index = (int)(Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
		
}
