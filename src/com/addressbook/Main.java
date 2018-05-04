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

/*
	public void entryMenu () {
		System.out.println("\t\tENTRY");
		System.out.println("1. Edit Address");
		System.out.println("2. Edit Phone Number");
		System.out.println("3. Done");
	}

	public void addressMenu () {
		System.out.println("\t\tADDRESS");
		System.out.println("1. Edit House Number");
		System.out.println("2. Edit Street");
		System.out.println("3. Edit City");
		System.out.println("4. Edit State");
		System.out.println("5. Edit Zip Code");
		System.out.println("6. Edit entire address");
		System.out.println("7. Done");
	}
	*/
}
