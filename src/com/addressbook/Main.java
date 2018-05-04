package com.addressbook;

import java.util.Scanner;
import java.io.IOException;

public class Main {
	public static void main (String[] args) {

	}

	public void addressBookMenu () {
		System.out.println("\t\tADDRESS BOOK");
		System.out.println("1. Add a New Entry");
		System.out.println("2. Edit an Entry");
		System.out.println("3. Delete an Entry");
		System.out.println("4. Sort Address Book by Name");
		System.out.println("5. Sort Address Book by Zip Code");
		System.out.println("6. Done");
	}

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
}
