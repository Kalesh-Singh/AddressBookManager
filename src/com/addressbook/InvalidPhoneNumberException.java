package com.addressbook;

public class InvalidPhoneNumberException extends Exception{
	public InvalidPhoneNumberException(String reason, String statement) {
		super(reason + ": " + statement);
	}
}
