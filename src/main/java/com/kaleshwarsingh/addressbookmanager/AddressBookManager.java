package com.kaleshwarsingh.addressbookmanager;

import java.util.Map;
import java.util.HashMap;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.ClassNotFoundException;

/**
 * This class allows you to create and manage multiple address books. It provides methods to create, edit, save, open, and sort address books.
 */
public class AddressBookManager {
	private Map<String, AddressBook> addressBooks = new HashMap<String, AddressBook>();

	/**
	 * Creates and opens an address book with the name specified by the string addressBookName.
	 * @param addressBookName	A String representing the name of the new address book.
	 * @exception InvalidNameException	On empty address book name received.
	 * @return A reference to the new AddressBook.
	 * @see InvalidNameException
	 */
	public AddressBook createAddressBook(String addressBookName) throws InvalidNameException {
		if (addressBookName.length() == 0)
			throw new InvalidNameException("Name of the address book cannot be empty", addressBookName);
		AddressBook addressbook = new AddressBook();
		addressBooks.put(addressBookName, addressbook);
		return addressbook;
	}

 	/**
	 * Returns a reference to all open address books.
	 * @return A Map containing all open address books.
	 */
	public Map<String, AddressBook> getAddressBooks () {
		return addressBooks;
	}

	/**
	 * Opens a saved address book specified by the string addressBookName.
	 * @param addressBookName				A String representing the name of the saved address book to open.
	 * @exception ClassNotFoundException	On receiving a saved object that does not match the AddressBook class.
	 * @exception IOException				On failure to open the file specified by addressBookName.
	 *@return A reference to the opened address book.
	 */
	public AddressBook openAddressBook (String addressBookName) throws IOException, ClassNotFoundException {
		if (!addressBooks.containsKey(addressBookName)) {
			try {
				ObjectInputStream objectStream =
					new ObjectInputStream(
						Files.newInputStream(
							Paths.get("." + addressBookName + ".dat")
						)
					);
				AddressBook addressBook = (AddressBook) objectStream.readObject();
				addressBooks.put(addressBookName, addressBook);
				System.out.println(addressBookName + " opened.");
				return addressBook;
			} catch (IOException e) {
				throw e;
			} catch (ClassNotFoundException e) {
				throw e;
			}
		} else
			System.out.println(addressBookName + " is already open.");
		return addressBooks.get(addressBookName);
	}
	
	/**
	 * Saves an open address book specified by the string addressBookName. The address book is saved to a file with the filename being the string addressBookName. If the optional parameter fileName is specified the filename is that specified by the string fileName[0].
	 * @param addressBookName		A String representing the name of the address book to save.
	 * @param fileName				An optional String parameter representing the filename of the file to which the address book will be saved.
	 * @exception InvalidNameException	On attempt to save an address book that is not open.
	 * @see InvalidNameException
	 */
	public void saveAddressBook(String addressBookName, String... fileName) throws InvalidNameException, IOException {
		String saveName = null;
		if (fileName.length > 0)
			saveName = "." + fileName[0] + ".dat";
		else
			saveName = "." + addressBookName + ".dat";
		try {
			AddressBook addressBook = addressBooks.get(addressBookName);
			if (addressBook != null) {
				ObjectOutputStream objectStream =
					new ObjectOutputStream(
						Files.newOutputStream(
							Paths.get(saveName)
						)
					);
				objectStream.writeObject(addressBook);
			} else 
				throw new InvalidNameException("No such address book is open", addressBookName);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Closes and saves the address book specified by the string addressBookName.
	 * @param addressBookName	A String representing the name of the address book to close and save.
	 * @exception InvalidNameException	On attempt to close and save an address book that is not open.
	 * @exception IOException			On failure to open the file to save the address book to.
	 * @see IOException
	 * @see InvalidNameException
	 */
	public void closeAndSaveAddressBook (String addressBookName) throws InvalidNameException, IOException {
		this.saveAddressBook(addressBookName);
		addressBooks.remove(addressBookName);
	}

	/**
	 * Closes and saves all open address books.
	 * @exception InvalidNameException	On attempt to close and save an address book that is not open.
	 * @exception IOException			On failure to open the file(s) to save the address books to.
	 * @see IOException
	 * @see InvalidNameException
	 */
	public void closeAndSaveAll () throws InvalidNameException, IOException {
		for (String name : addressBooks.keySet()) {
			this.saveAddressBook(name);
		}

		addressBooks.clear();
	}

	/**
	 * Closes the address book specified by the string addressBookName without saving.
	 * @param addressBookName				A String representing the name of the address book to close.
	 * @exception InvalidNameException		On attempt to close and address book that is not open.
	 * @see InvalidNameException
	 */
	public void closeAddressBookWithoutSaving (String addressBookName) throws InvalidNameException {
		if (addressBooks.containsKey(addressBookName)) {
			addressBooks.remove(addressBookName);
		} else
			throw new InvalidNameException("No such address book is open", addressBookName);
	}

	/**
	 * Closes all open address books without saving.
	 * @exception InvalidNameException		On attempt to close an address book that is not open.
	 */
	public void closeAllWithoutSaving () throws InvalidNameException {
		for (String name : addressBooks.keySet()) {
			this.closeAddressBookWithoutSaving (name);
		}
	}
	
	/**
	 * Prints a list of the names of all open address books.
	 */
	public void showOpenAddressBooks() {
		if (addressBooks.keySet().size() > 0) {
			System.out.println("Open Address Books: ");
			for (String addressBookName : addressBooks.keySet()) {
				System.out.println("\t" + addressBookName);
			}
		} else
			System.out.println("No address books are open.");
		System.out.println();
	}
}
