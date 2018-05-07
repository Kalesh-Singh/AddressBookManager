package com.kaleshwarsingh.addressbookmanager;

/**
 * Thrown by a PhoneNumber to indicate that the phone number received does not match the pattern of a valid phone number.
 * @author	Kaleshwar Singh
 * @version	1.0
 * @since 	2018-04-28
 * @see		PhoneNumber
 */
public class InvalidPhoneNumberException extends Exception{

	/**
	 * Constructs an InvalidPhoneNumberException, saving a reference to the error message strings reason and statement for later retrieval by the getMessage method.
	 */
	public InvalidPhoneNumberException(String reason, String statement) {
		super(reason + ": " + statement);
	}
}
