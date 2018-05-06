package com.kaleshwarsingh.addressbookmanager;

/**
* <h1>InvalidAddressException</h1>
* <p>An exception class that can be thrown if an invalid address format is entered.</p>
* @author	Kaleshwar Singh
* @version	1.0
* @since	2018-04-28
*/
public class InvalidAddressException extends Exception {
	public InvalidAddressException (String reason, String statement) {
		super(reason + ": " + statement);
	}

	public InvalidAddressException (String reason, String statement, Throwable cause) {
		super(reason + ": " + statement, cause);
	}
}
