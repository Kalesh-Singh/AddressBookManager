package com.kaleshwarsingh.addressbookmanager;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class AddressBookManagerTest {
	@Test
	public void canCreateAddressBook () throws InvalidNameException {
		AddressBookManager addressBookManager = new AddressBookManager();
		
		AddressBook addressBook = addressBookManager.createAddressBook("TestAddressBook");

		Assert.assertEquals(0, addressBook.getEntries().size());
	}

	@Test(expected = InvalidNameException.class)
	public void createdAddressBooksNameCannotBeEmpty () throws InvalidNameException {
		AddressBookManager addressBookManager = new AddressBookManager();
		
		addressBookManager.createAddressBook("");
	}

	@Test
	public void canSaveAddressBook () throws InvalidNameException, IOException {
		AddressBookManager addressBookManager = new AddressBookManager();
		String addressBookName = "TestAddressBook";
		addressBookManager.createAddressBook(addressBookName);

		File dir = new File(".");
		File[] files = dir.listFiles();
		Path path = Paths.get(".", "." + addressBookName + ".dat");
		if (!Files.deleteIfExists(path))
			System.out.println("ERROR: Failed to deleted already existing address book.");
		addressBookManager.saveAddressBook(addressBookName);
		System.out.println("Address book saved in " + System.getProperty("user.dir"));

		Assert.assertTrue(Files.exists(path));
	}

	@Test(expected = InvalidNameException.class)
	public void addressBookMustBeOpenBeforeSaving () throws InvalidNameException, IOException {
		AddressBookManager addressBookManager = new AddressBookManager();
		addressBookManager.createAddressBook("AddressBook1");

		addressBookManager.saveAddressBook("AddressBook2");
	}
	
	@Test
	public void canOpenSavedAddressBook () throws InvalidNameException, IOException, ClassNotFoundException {
		AddressBookManager addressBookManager = new AddressBookManager();
		addressBookManager.createAddressBook("addressBookToOpen");
		addressBookManager.closeAndSaveAddressBook("addressBookToOpen");

		addressBookManager.openAddressBook("addressBookToOpen");

		Assert.assertNotEquals(null, addressBookManager.getAddressBooks().get("addressBookToOpen"));
	}
}
