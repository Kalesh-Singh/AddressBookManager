package com.kaleshwarsingh.addressbookmanager;

/**
* <h1>InvalidPhoneNumberException</h1>
* <p>An exception class that can be thrown if an invalid phone number format is entered.</p>
* @author	Kaleshwar Singh
* @version	1.0
* @since 	2018-04-28
*/
public class InvalidPhoneNumberException extends Exception{
	public InvalidPhoneNumberException(String reason, String statement) {
		super(reason + ": " + statement);
	}
}
