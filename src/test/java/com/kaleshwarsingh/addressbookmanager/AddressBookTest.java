package com.kaleshwarsingh.addressbookmanager;

import org.junit.Test;
import org.junit.Assert;

public class AddressBookTest {
	@Test
	public void canAddEntry () throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		AddressBook addressBook = new AddressBook();
		Address address = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber = new PhoneNumber("+19293550415");
		Person person = new Person("Kaleshwar", "Singh", address, phoneNumber);

		addressBook.addEntry(person);

		Assert.assertEquals(1,addressBook.size());
	}

	@Test
	public void canDeleteEntry () throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		AddressBook addressBook = new AddressBook();
		Address address = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber = new PhoneNumber("+19293550415");
		Person person = new Person("Kaleshwar", "Singh", address, phoneNumber);
		addressBook.addEntry(person);

		addressBook.deleteEntry(addressBook.findEntry("Kaleshwar", "Singh"));

		Assert.assertEquals(0, addressBook.size());
	}

	@Test
	public void canSortAddressBookByName () throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		AddressBook addressBook = new AddressBook();
		
		Address address1 = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber1 = new PhoneNumber("+19293550415");
		Person person1 = new Person("Kaleshwar", "Singh", address1, phoneNumber1);
		addressBook.addEntry(person1);

		Address address2 = new Address(14620, "115th Ave", "Jamaica", "NY", "11436");
		PhoneNumber phoneNumber2 = new PhoneNumber("+19298631234");
		Person person2 = new Person("Joe", "Singh", address2, phoneNumber2);
		addressBook.addEntry(person2);

		Address address3 = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber3 = new PhoneNumber("2028567432");
		Person person3 = new Person("Delaney", "Ramhalo", address3, phoneNumber3);
		addressBook.addEntry(person3);
		
		Address address4 = new Address(1065, "Belle West H/S", "W.B.D", "Guyana", "05923");
		PhoneNumber phoneNumber4 = new PhoneNumber("5926771252");
		Person person4 = new Person("John", "Doe", address4, phoneNumber4);
		addressBook.addEntry(person4);

		addressBook.sortByName();

		Assert.assertEquals(addressBook.getEntry(0), person4);
		Assert.assertEquals(addressBook.getEntry(1), person3);
		Assert.assertEquals(addressBook.getEntry(2), person2);
		Assert.assertEquals(addressBook.getEntry(3), person1);
	}

	@Test
	public void canSortAddressBookByZipCode () throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		AddressBook addressBook = new AddressBook();
		
		Address address1 = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber1 = new PhoneNumber("+19293550415");
		Person person1 = new Person("Kaleshwar", "Singh", address1, phoneNumber1);
		addressBook.addEntry(person1);

		Address address2 = new Address(14620, "115th Ave", "Jamaica", "NY", "11436");
		PhoneNumber phoneNumber2 = new PhoneNumber("+19298631234");
		Person person2 = new Person("Joe", "Singh", address2, phoneNumber2);
		addressBook.addEntry(person2);

		Address address3 = new Address(2205, "4th St NW", "Washington", "DC", "20059");
		PhoneNumber phoneNumber3 = new PhoneNumber("2028567432");
		Person person3 = new Person("Delaney", "Ramhalo", address3, phoneNumber3);
		addressBook.addEntry(person3);
		
		Address address4 = new Address(1065, "Belle West H/S", "W.B.D", "Guyana", "05923");
		PhoneNumber phoneNumber4 = new PhoneNumber("5926771252");
		Person person4 = new Person("John", "Doe", address4, phoneNumber4);
		addressBook.addEntry(person4);

		addressBook.sortByZipCode();

		Assert.assertEquals(addressBook.getEntry(0), person4);
		Assert.assertEquals(addressBook.getEntry(1), person2);
		Assert.assertEquals(addressBook.getEntry(2), person3);
		Assert.assertEquals(addressBook.getEntry(3), person1);
	}
}
