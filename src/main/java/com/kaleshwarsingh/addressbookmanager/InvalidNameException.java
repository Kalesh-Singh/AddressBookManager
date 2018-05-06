package com.kaleshwarsingh.addressbookmanager;

/**
* <h1>InvalidNameException</h1>
* <p>An exception class that can be thrown if an invalid name (no characters) is entered.</p>
* @author	Kaleshwar singh
* @version	1.0
* @since	2018-04-30
*/

public class InvalidNameException extends Exception {
	public InvalidNameException (String reason, String statement) {
		super(reason + ": " + statement);
	}

	public InvalidNameException (String reason, String statement, Throwable cause) {
		super(reason + ": " + statement, cause);
	}
}

