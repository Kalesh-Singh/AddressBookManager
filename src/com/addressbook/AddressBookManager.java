package com.addressbook;

import java.util.Map;
import java.util.HashMap;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.InputMismatchException;

import java.util.Scanner;



public class AddressBookManager {
	private Map<String, AddressBook> addressBooks = new HashMap<String, AddressBook>();

	public AddressBook createAddressBook(String addressBookName) throws InvalidNameException {
		if (addressBookName.length() == 0)
			throw new InvalidNameException("Name of the address book cannot be empty", addressBookName);
		AddressBook addressbook = new AddressBook();
		addressBooks.put(addressBookName, addressbook);
		return addressbook;
	}

	public void saveAddressBook(String addressBookName) throws IOException {
		try {
			
			AddressBook addressBook = addressBooks.get(addressBookName);

			if (addressBook != null) {
				ObjectOutputStream objectStream =
					new ObjectOutputStream(
						Files.newOutputStream(
							Paths.get("." + addressBookName + ".dat")
						)
					);
				objectStream.writeObject(addressBook);
			} else
				System.out.println("No such address book is open: " + addressBookName);

		} catch (IOException e) {
			throw e;
		}
	}

	public AddressBook openAddressBook (String addressBookName) throws IOException, ClassNotFoundException {
		AddressBook addressBook = addressBooks.get(addressBookName);

		if (addressBook != null) {
		
			try {
				ObjectInputStream objectStream =
					new ObjectInputStream(
						Files.newInputStream(
							Paths.get("." + addressBookName + ".dat")
						)
					);
				addressBook = (AddressBook) objectStream.readObject();
				addressBooks.put(addressBookName, addressBook);
			} catch (IOException e) {
				throw e;
			} catch (ClassNotFoundException e) {
				throw e;
			}
		} else
			System.out.println(addressBookName + " is already open.");

		return addressBook;
	}

	public void closeAddressBook(String addressBookName) throws IOException {
	
		AddressBook addressBook = addressBooks.get(addressBookName);
		Scanner sc = new Scanner(System.in);
		int option = -1;

		if (addressBook != null) {
			while ((option != 1) || (option != 2)) {
				System.out.println("Do you want to save the address book before closing?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.println("Enter the corresponding number to select an option: ");
				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nERROR: The entered option must be a number from 1 to 3.");
					System.out.println("Please try again\n");
					sc.nextLine();			// Clear the buffer
				}
			}

			switch (option) {
				case 1:
					System.out.println("Saving changes to " + addressBookName + "...");
					this.saveAddressBook(addressBookName);
					break;
				case 2:
					System.out.println("Discarding changes to " + addressBookName + "...");
					break;
				default:
					break;
			}

			// Close the address book
			addressBooks.remove(addressBookName);


		} else
			System.out.println("No such address book is open: " +  addressBookName);
	}

	public void showOpenAddressBooks() {
		System.out.println("\tOpen Address Books:");
		for (String addressBookName : addressBooks.keySet()) {
			System.out.println(addressBookName);
		}
		System.out.println();
	}
	/*
	public void options() {
		Scanncer sc = new Scanner(System.in);
		int option = -1;
		while ((option < 1) || (option > 6)) {
			System.out.println("\n\n\t\tADDRESS BOOK MANAGER");
			System.out.println("Select an option by entering the corresponding number: ");
			System.out.println("1. Show all open address books.");
			System.out.println("2. Open an address book");
			System.out.println("3. Edit an open address book");
			System.out.println("4. Close an address book");
			System.out.println("5. Save an address book");
			System.out.println("6. Exit the program");
			try {
				option = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nERROR: The entered option must be a number from 1 to 6.");
				System.out.println("Please try again\n");
				sc.nextLine();			// Clear the buffer
			}
		}

		switch (option) {

	}
	*/
}

