package com.addressbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

/**
* <h1>PhoneNumber</h1>
* <p>The PhoneNumber class provides convenient way of representing standard 10-digit phone numbers, regardless of the way the phone number is entered.</p>
*
* @author	Kaleshwar Singh
* @version	1.0
* @since	2014-03-28
*/


public class PhoneNumber implements Serializable {
	private String countryCode = null;
	private String areaCode = null;
	private String exchangeNum = null;
	private String subscriberNum = null;
	private String extension = null;


	private static final Pattern p 
	= Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$");

		
	private Matcher m;
	
	/**
	* This constructor is used to create and initialize instance of the PhoneNumber class.
	*/
	public PhoneNumber() { }							// Default constructor

	/**
	* This constructor is used to create and initialize instance of the PhoneNumber class.
	*
	* It would accept the following examples and much more,
	* regardless of the way the phone number is entered:
	* <ul> 
	* 	<li>18005551234</li>
	* 	<li>1 800 555 1234</li>
	* 	<li>+1 800 555-1234</li>
	* 	<li>+86 800 555 1234</li>
	* 	<li>1-800-555-1234</li>
	* 	<li>1 (800) 555-1234</li>
	* 	<li>(800)555-1234</li>
	* 	<li>(800) 555-1234</li>
	* 	<li>(800)5551234</li>
	* 	<li>800-555-1234</li>
	* 	<li>800.555.1234</li>
	* 	<li>800 555 1234x5678</li>
	* 	<li>8005551234 x5678</li>
	* 	<li>1    800    555-1234</li>
	* 	<li>1----800----555-1234</li>
	*</ul>
	* @param phoneNumber						A String representation of the phone number.
	* @exception InvalidPhoneNumberException	On invalid phoneNumber format.
	* @see InvalidPhoneNumberException
	*/
	public PhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {		
		parsePhoneNumber(phoneNumber);
	}

	/**
	* This method is used to edit an intance of the PhoneNumber class.
	*
	* It would accept the following examples and much more, 
	* regardless of the way the phone number is entered:
	* <ul> 
	* 	<li>18005551234</li>
	* 	<li>1 800 555 1234</li>
	* 	<li>+1 800 555-1234</li>
	* 	<li>+86 800 555 1234</li>
	* 	<li>1-800-555-1234</li>
	* 	<li>1 (800) 555-1234</li>
	* 	<li>(800)555-1234</li>
	* 	<li>(800) 555-1234</li>
	* 	<li>(800)5551234</li>
	* 	<li>800-555-1234</li>
	* 	<li>800.555.1234</li>
	* 	<li>800 555 1234x5678</li>
	* 	<li>8005551234 x5678</li>
	* 	<li>1    800    555-1234</li>
	* 	<li>1----800----555-1234</li>
	*</ul>
	*
	* @param phoneNumber						A String representation of the phone number.
	* @exception InvalidPhoneNumberException	On invalid phone number format.
	* @see InvalidPhoneNumberException
	*/

	public void editPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
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
		} else
			throw new InvalidPhoneNumberException("Incorrect phone number format", phoneNumber);
	}

	/**
	* Converts the phone number to a String in the format +x (xxx) xxx-xxxx.
	* @return A String representation of the PhoneNumber instance.
	*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(20);

		if (countryCode != null)
			sb.append("+" + countryCode + " ");
		if (areaCode != null)
			sb.append("(" + areaCode + ")");
		if (exchangeNum != null)
			sb.append(" " + exchangeNum);
		if (subscriberNum != null)
			sb.append("-" + subscriberNum);
		if (extension != null)
			sb.append(", Ext. " + extension);
		
		return sb.toString();
	}

	/**
	* This method can be used to check whether 2 instances of PhoneNumber are equal.
	* @return A boolean of indicating whether the phone numbers are equal or not.
	*/
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PhoneNumber))
			return false;
		PhoneNumber other = (PhoneNumber) o;
		return 
			(this.areaCode.equals(other.areaCode)) &&
			(this.exchangeNum.equals(other.exchangeNum)) &&
			(this.subscriberNum.equals(other.subscriberNum));
	}
}
