package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateData {

	public String generateRandomString(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
}
