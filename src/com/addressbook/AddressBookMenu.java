package com.addressbook;

import java.util.Scanner;

public class AddressBookMenu {

	private AddressBook addressBook;
	
	public AddressBookMenu (AddressBook addressBook) {
		this.addressBook = addressBook;
	}

	public void displayMenu () {
		System.out.println("\t\tADDRESS BOOK");
		System.out.println("1. Add a New Entry");
		System.out.println("2. Edit an Entry");
		System.out.println("3. Delete an Entry");
		System.out.println("4. Sort Address Book by Name");
		System.out.println("5. Sort Address Book by Zip Code");
		System.out.println("6. Done");

		//TODO: Implement options from input.
	}

	public void addEntry() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the firt name of the person: ");
		String firstName = sc.nextLine();
		System.out.print("Enter the last name of the person: ");
		String lastName = sc.nextLine();
		try {
			addressBook.addEntry(firstName, lastName);
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		}
	}

	public void deleteEntry() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the first name of the person: ");
		String firstName = sc.nextLine();
		System.out.print("Enter the last name of the person: ");
		String lastName = sc.nextLine();
		try {
			int index = addressBook.findEntry(firstName, lastName);
			if (index < 0)
				System.out.println("Entry not in address book.");
			else {
				addressBook.deleteEntry(index);
				System.out.println("Deleted entry.");
			}
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		}
	}

	public void sortAddressBookByName () {
		addressBook.sortByName();
		System.out.println("Sorted address book by name.");
	}

	public void sortAddressBookByZipCode () {
		addressBook.sortByZipCode();
		System.out.println("Sorted address book by zip code.");
	}

	public void editAddressBook () {
		// TODO: Implement this.
	}
}

