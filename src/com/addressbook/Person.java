package com.addressbook;

public class Person {
	private String firstName = null;
	private String lastName = null;
	Address address = null;
	PhoneNumber phoneNumber = null;

	public Person () {
		address = new Address();
		phoneNumber = new PhoneNumber();
	}

	public Person (String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person (String firstName, String lastName, Address address) {
		this(firstName, lastName);
		this.address = address;
	}

	public Person (String firstName, String lastName, PhoneNumber phoneNumber) {
		this(firstName, lastName);
		this.phoneNumber = phoneNumber;
	}

	public Person (String firstName, String lastName, Address address, PhoneNumber phoneNumber) {
		this(firstName, lastName, address);
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName () {
		return firstName;
	}

	public String getLastName () {
		return lastName;
	}

	public Address getAddress () {
		return address;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void editAddress() throws InvalidAddressException {
		address.editAddress();
	}

	public void editPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		this.phoneNumber.editPhoneNumber(phoneNumber);
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder(200);

		if (firstName != null)
			sb.append("Name: " + firstName);
		if (lastName != null)
			sb.append(" " + lastName);
		if (address.toString().length() > 0)
			sb.append("\nAddress: " + address.toString());
		if (phoneNumber.toString().length() > 0)
			sb.append("\nPhone Number: " + phoneNumber.toString());

		return sb.toString();
	}
}
