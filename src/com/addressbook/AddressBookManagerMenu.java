package com.addressbook;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class AddressBookManagerMenu {

	private AddressBookManager addressBookManager;

	public AddressBookManagerMenu (AddressBookManager addressBookManager) {
		this.addressBookManager = addressBookManager;
	}
	
	public void displayMenu () {
		int option;
		do {		
			option = -1;
			Scanner sc = new Scanner(System.in);
	
			while ((option < 1) || (option > 12)) {	
				System.out.println("\t\tADDRESS BOOK MANAGER");
				System.out.println(" 1. Create Address Book");
				System.out.println(" 2. Open Address Book");
				System.out.println(" 3. Save Address Book");
				System.out.println(" 4. Edit Address Book");
				System.out.println(" 5. Close and Save Address Book");
				System.out.println(" 6. Close and Save ALL Address Books");
				System.out.println(" 7. Close Address Book without Saving");
				System.out.println(" 8. Close ALL Address Books without Saving");
				System.out.println(" 9. Show all Open Address Books");
				System.out.println("10. Sort Address Book by Name");
				System.out.println("11. Sort Address Book by Zip Code");
				System.out.println("12. Exit Program"); 

				System.out.print("\nEnter the corresponding number to select an option: ");
				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();			// Clear the buffer
					System.out.println("\nERROR: Entered option must be a number from 1 to 10");
					System.out.println("Please try again.\n");
				}
			}

			switch (option) {
				case 1:
					this.createAddressBook();
					break;
				case 2:
					this.openAddressBook();
					break;
				case 3:
					this.saveAddressBook();
					break;
				case 4:
					// TODO: this.editAddressBook();
					break;
				case 5:
					this.closeAndSaveAddressBook();
					break;
				case 6:
					this.closeAndSaveAll();
				case 7:
					this.closeAddressBookWithoutSaving();
					break;
				case 8:
					this.closeAllWithoutSaving();
					break;
				case 9:
					addressBookManager.showOpenAddressBooks();
					break;
				case 10:
					this.sortAddressBookByName();
					break;
				case 11:
					this.sortAddressBookByZipCode();
					break;
				case 12:
					System.out.println("Exiting program...");
					System.exit(0);
					break;
				default:
					break;
			}
		} while (option != 12);		// While not done
	}		

	public void createAddressBook () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the New Address Book: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			addressBookManager.createAddressBook(addressBookName);
			System.out.println("Created address book, " + addressBookName);
		} catch (InvalidNameException e) {
			System.out.print("\nERROR: Failed to create address book, " + addressBookName);
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	public void openAddressBook () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the Address Book you want to open: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			addressBookManager.openAddressBook(addressBookName);
			System.out.println("Address Book, " + addressBookName + " opened.");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: Deserialized object does not match class.");
			System.out.println(e.getMessage());
			System.out.println();
		} catch (IOException e) {
			System.out.println("ERROR: No data found for address book, " + addressBookName);
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	public void saveAddressBook () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the Address Book you want to save: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			addressBookManager.saveAddressBook(addressBookName);
			System.out.println("Address Book, " + addressBookName + " saved.");
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		} catch (IOException e) {
			System.out.print("ERROR: Could not save address book, " + addressBookName);
			System.out.println(e.getMessage());
			System.out.println();
		} 
	}

	public void closeAndSaveAddressBook () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the Address Book you want to save and close: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			addressBookManager.closeAndSaveAddressBook(addressBookName);
			System.out.println("Address Book, " + addressBookName + " saved and closed.");
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		} catch (IOException e) {
			System.out.print("ERROR: Could not close and save address book, " + addressBookName);
			System.out.println(e.getMessage());
			System.out.println();
		} 
	}

	public void closeAddressBookWithoutSaving () {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the Address Book you want to close: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			addressBookManager.closeAddressBookWithoutSaving(addressBookName);
			System.out.println("Closed address book, " + addressBookName + " without saving.");
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
			System.out.println();
		}
	}

	public void closeAndSaveAll () {
		try {
			addressBookManager.closeAndSaveAll();
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void closeAllWithoutSaving () {
		try {
			addressBookManager.closeAllWithoutSaving();
		} catch (InvalidNameException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void sortAddressBookByName () {
		Scanner sc = new Scanner(System.in);		
		System.out.print("Enter the name of the Address Book to sort by name: ");
		String addressBookName = sc.nextLine();
		AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
		if (addressBook == null)
			System.out.println("No such address book is open: " + addressBookName);
		else {
			addressBook.sortByName();
			System.out.println("Address book, " + addressBookName + " sorted by name.");
		}				
	}

	public void sortAddressBookByZipCode () {
		Scanner sc = new Scanner(System.in);		
		System.out.print("Enter the name of the Address Book to sort by zip code: ");
		String addressBookName = sc.nextLine();
		AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
		if (addressBook == null)
			System.out.println("No such address book is open: " + addressBookName);
		else {
			addressBook.sortByZipCode();
			System.out.println("Address book, " + addressBookName + " sorted by zip code.");
		}				
	}
}
