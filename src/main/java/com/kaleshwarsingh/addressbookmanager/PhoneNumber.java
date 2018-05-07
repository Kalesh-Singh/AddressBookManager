package com.kaleshwarsingh.addressbookmanager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

/**
 * Provides convenient way of representing a standard 10-digit phone number, regardless of the way the phone number is entered.</p>
 *
 * @author	Kaleshwar Singh
 * @version	1.0
 * @since	2014-03-28
 * @see		InvalidPhoneNumberException
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
	* Constructs an empty PhoneNumber.
	*/
	public PhoneNumber() { }							// Default constructor

	/**
	* Constructs and intializes a PhoneNumber to that indicated by the string phoneNumber.
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
	* Edits a PhoneNumber by replacing the old phone number with the phone number represented by the string phoneNumber.
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
	 * Returns a string representation of the phone number in the format +x (xxx) xxx-xxxx.
	 * @return A String representation of the phone number. 
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
	 * Compares this phone number to the specified object o.
	 * @param o An object to be compared with for equality.
	 * @return A boolean indicating whether the phone number and object are equal.
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
