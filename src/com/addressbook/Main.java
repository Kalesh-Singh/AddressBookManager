package com.addressbook;

import java.util.Scanner;
import java.io.IOException;

public class Main {
	public static void main (String[] args) {
		AddressBookManager addressBookManager = new AddressBookManager();
		AddressBookManagerMenu addressBookManagerMenu 
			= new AddressBookManagerMenu(addressBookManager);
		addressBookManagerMenu.displayMenu();
	}
}
