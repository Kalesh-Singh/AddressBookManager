package com.addressbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneNumber {
	private String countryCode = null;
	private String areaCode = null;
	private String exchangeNum = null;
	private String subscriberNum = null;
	private String extension = null;

	/*
	^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$
	
	It would match the following examples and much more:

	18005551234
	1 800 555 1234
	+1 800 555-1234
	+86 800 555 1234
	1-800-555-1234
	1 (800) 555-1234
	(800)555-1234
	(800) 555-1234
	(800)5551234
	800-555-1234
	800.555.1234
	800 555 1234x5678
	8005551234 x5678
	1    800    555-1234
	1----800----555-1234
	Regardless of the way the phone number is entered, the capture groups can be used to breakdown the phone number so it can be processed it in the code.

	Group1: Country Code (ex: 1 or 86)
	Group2: Area Code (ex: 800)
	Group3: Exchange (ex: 555)
	Group4: Subscriber Number (ex: 1234)
	Group5: Extension (ex: 5678)

	Here is a breakdown of the expressio:

	^\s*                #Line start, match any whitespaces at the beginning if any.
	(?:\+?(\d{1,3}))?   #GROUP 1: The country code. Optional.
	[-. (]*             #Allow certain non numeric characters that may appear between the Country Code and the Area Code.
	(\d{3})             #GROUP 2: The Area Code. Required.
	[-. )]*             #Allow certain non numeric characters that may appear between the Area Code and the Exchange number.
	(\d{3})             #GROUP 3: The Exchange number. Required.
	[-. ]*              #Allow certain non numeric characters that may appear between the Exchange number and the Subscriber number.
	(\d{4})             #Group 4: The Subscriber Number. Required.
	(?: *x(\d+))?       #Group 5: The Extension number. Optional.
	\s*$                #Match any ending whitespaces if any and the end of string.
	*/

	private static final Pattern p 
	= Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$");

	
	private Matcher m;

	public PhoneNumber() { }							// Default constructor

	public PhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {		
		parsePhoneNumber(phoneNumber);
	}

	public void editPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{	// Mutator
		parsePhoneNumber(phoneNumber);
	}
	
	private void parsePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		m = p.matcher(phoneNumber);
		if (m.find()) {
			countryCode = m.group(1);
			areaCode = m.group(2);
			exchangeNum = m.group(3);
			subscriberNum = m.group(4);
			extension = m.group(5);
		} else {
			throw new InvalidPhoneNumberException("Incorrect phone number format", phoneNumber);	
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(20);

		if (countryCode != null)
			sb.append("+" + countryCode);
		if (areaCode != null)
			sb.append(" (" + areaCode + ")");
		if (exchangeNum != null)
			sb.append(" " + exchangeNum);
		if (subscriberNum != null)
			sb.append("-" + subscriberNum);
		if (extension != null)
			sb.append(", Ext. " + extension);
		
		return sb.toString();
	}
	
}
