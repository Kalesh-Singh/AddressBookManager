package com.addressbook;

import java.util.Scanner;
import java.util.InputMismatchException;

public class AddressBookMenu {

	private AddressBook addressBook;
	
	public AddressBookMenu (AddressBook addressBook) {
		this.addressBook = addressBook;
	}

	public void displayMenu () {
		while (true) {
			int option = -1;
			Scanner sc = new Scanner(System.in);
			
			while ((option < 1) || (option > 8)) {
				System.out.println("\t\tADDRESS BOOK");
				System.out.println("1. Add new entry");
				System.out.println("2. Edit an entry");
				System.out.println("3. View entry details");
				System.out.println("4. Delete an entry");
				System.out.println("5. Sort address book by name");
				System.out.println("6. Sort address book by zip code");
				System.out.println("7. List all entries in mailing format");
				System.out.println("8. Done (Go back)");

				System.out.print("\nEnter the corresponding number to select an option: ");

				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();			// Clear the buffer
					System.out.println("\nERROR: Entered option must be a number from 1 to 8");
					System.out.println("Please try again.\n");
					continue;
				}
				if ((option < 1) || (option > 8)) {
					System.out.println("\nERROR: Entered option must be a number from 1 to 8");
					System.out.println("Please try again.\n");
				}
			}

			switch (option) {
				case 1:
					this.addEntry();
					break;
				case 2:
					this.editEntry();
					break;
				case 3:
					this.viewEntry();
					break;
				case 4:
					this.deleteEntry();
					break;
				case 5:
					this.sortAddressBookByName();
					break;
				case 6:
					this.sortAddressBookByZipCode();
					break;
				case 7:
					this.showAllEntries();
					break;
				case 8:
					System.out.println("Exiting Address Book Menu...");
					return;		// Exit AddresBookMenu
				default:
					System.out.println("\nERROR: Entered option must be a number from 1 to 6");
					System.out.println("Please try again.\n");
					break;
			}
		}
	}

	private void addEntry() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the first name of the person: ");
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

	private void deleteEntry() {
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

	private void sortAddressBookByName () {
		addressBook.sortByName();
		System.out.println("Sorted address book by name.");
	}

	private void sortAddressBookByZipCode () {
		addressBook.sortByZipCode();
		System.out.println("Sorted address book by zip code.");
	}

	public void showAllEntries () {
		System.out.println("\tEntries:\n");
		for (Person p : addressBook.getEntries()) {
			System.out.println(p);
			System.out.println();
		}
	}

	private void editEntry () {
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
				new EntryMenu(addressBook.getEntries().get(index)).displayMenu();
			}
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		}
	}

	private void viewEntry () {
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
				new EntryMenu(addressBook.getEntries().get(index)).viewEntry();
			}
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		}
	}


}

