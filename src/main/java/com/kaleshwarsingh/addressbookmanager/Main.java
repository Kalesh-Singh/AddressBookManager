package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.io.IOException;

/**
 * Driver class for the address book manager application.
 */
public class Main {
	public static void main (String[] args) {
		new AddressBookManagerMenu(new AddressBookManager()).displayMenu();
	}
}
