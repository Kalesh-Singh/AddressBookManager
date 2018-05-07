package com.kaleshwarsingh.addressbookmanager;

/**

 * Thrown by a Person if the first name received is empty.
 * @author	Kaleshwar singh
 * @version	1.0
 * @since	2018-04-30
 */
public class InvalidNameException extends Exception {
	/**
	 * Constructs an InvalidPhoneNumberException, saving a reference to the error message strings reason and statement for later retrieval by the getMessage method.
	 */
	public InvalidNameException (String reason, String statement) {
		super(reason + ": " + statement);
	}

	/**
	 * Constructs an InvalidPhoneNumberException, saving a reference to the error message strings reason and statement, and the throwable cause for later retrieval by the getMessage method.
	 */
	public InvalidNameException (String reason, String statement, Throwable cause) {
		super(reason + ": " + statement, cause);
	}
}

