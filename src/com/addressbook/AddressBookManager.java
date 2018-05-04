package com.addressbook;

import java.util.Map;
import java.util.HashMap;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class AddressBookManager {
	private Map<String, AddressBook> addressBooks = new HashMap<String, AddressBook>();

	public AddressBook createAddressBook(String addressBookName) throws InvalidNameException {
		if (addressBookName.length() == 0)
			throw new InvalidNameException("Name of the address book cannot be empty", addressBookName);
		AddressBook addressbook = new AddressBook();
		addressBooks.put(addressBookName, addressbook);
		return addressbook;
	}

	public Map<String, AddressBook> getAddressBooks () {
		return addressBooks;
	}

	public AddressBook openAddressBook (String addressBookName) throws IOException, ClassNotFoundException {
		//AddressBook addressBook = addressBooks.get(addressBookName);
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

	public void closeAndSaveAddressBook (String addressBookName) throws InvalidNameException, IOException {
		this.saveAddressBook(addressBookName);
		addressBooks.remove(addressBookName);
	}

	public void closeAndSaveAll () throws InvalidNameException, IOException {
		for (String name : addressBooks.keySet()) {
			this.saveAddressBook(name);
		}

		addressBooks.clear();
	}

	public void closeAddressBookWithoutSaving (String addressBookName) throws InvalidNameException {
		if (addressBooks.containsKey(addressBookName)) {
			addressBooks.remove(addressBookName);
		} else
			throw new InvalidNameException("No such address book is open", addressBookName);
	}

	public void closeAllWithoutSaving () throws InvalidNameException {
		for (String name : addressBooks.keySet()) {
			this.closeAddressBookWithoutSaving (name);
		}
	}
	
	public void showOpenAddressBooks() {
		System.out.println("Open Address Books: ");
		for (String addressBookName : addressBooks.keySet()) {
			System.out.println("\t" + addressBookName);
		}
		System.out.println();
	}
}
