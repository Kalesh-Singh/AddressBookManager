package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.io.IOException;

public class Main {
	public static void main (String[] args) {
		new AddressBookManagerMenu(new AddressBookManager()).displayMenu();
	}
}
