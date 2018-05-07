package com.kaleshwarsingh.addressbookmanager;

/**
 * Thrown by an Address to indicate that the address received does not match the pattern for the expected type.</p>
 * @author	Kaleshwar Singh
 * @version	1.0
 * @since	2018-04-28
 * @see 		Address
 */
public class InvalidAddressException extends Exception {
	/**
	 * Constructs an InvalidAddressException, saving a reference to the strings reason and statement for later retrieval by the getMessage method.
	 */
	public InvalidAddressException (String reason, String statement) {
		super(reason + ": " + statement);
	}

	/**
	 * Constructs an InvalidAddressException, saving a reference to the strings reason and statement, and the throwable cause for later retrieval by the getMessage method.
	 */
	public InvalidAddressException (String reason, String statement, Throwable cause) {
		super(reason + ": " + statement, cause);
	}
}
