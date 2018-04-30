package com.addressbook;

/**
<h1>Person</h1>
<p>The Person provides a convenient way of representing a Person with first and last names, address and phone number.</p>
*
* @author	Kaleshwar Singh
* @version	1.0
* @since	2018-04-30
*/

public class Person {
	private String firstName = null;
	private String lastName = null;
	Address address = null;
	PhoneNumber phoneNumber = null;

	/**
	* This is the default constructor of the Person class.
	*/
	public Person () {
		address = new Address();
		phoneNumber = new PhoneNumber();
	}
	
	/**
	* This constructor creates and initializes an instance of the Person class.
	* @param firstName					A String representing the person's first name.
	* @param lastName					A String representing the person's last name.
	* @exception InvalidNameException	On empty first name entry.
	* @see								InvalidNameException
	*/
	public Person (String firstName, String lastName) throws InvalidNameException {
		this();
		if (firstName.length() == 0)
			throw new InvalidNameException("First name cannot be empty", firstName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	* This constructor creates and initializes an instance of the Person class.
	* @param firstName					A String representing the person's first name.
	* @param lastName					A String representing the person's last name.
	* @param address	`				An instance of Address representing the person's address.
	* @exception InvalidNameException	On empty first name entry.
	* @see								InvalidNameException
	*/
	public Person (String firstName, String lastName, Address address) throws InvalidNameException {
		this(firstName, lastName);
		this.address = address;
	}

	/**
	* This constructor creates and initializes an instance of the Person class.
	* @param firstName					A String representing the person's first name.
	* @param lastName					A String representing the person's last name.
	* @param phoneNumber				An instance of PhoneNumber represesnting the person's phone number.
	* @exception InvalidNameException	On empty first name entry.
	* @see								InvalidNameException
	*/
	public Person (String firstName, String lastName, PhoneNumber phoneNumber) throws InvalidNameException {
		this(firstName, lastName);
		this.phoneNumber = phoneNumber;
	}

	/**
	* This constructor creates and initializes an instance of the Person class.
	* @param firstName					A String representing the person's first name.
	* @param lastName					A String representing the person's last name.
	* @param address	`				An instance of Address representing the person's address.
	* @param phoneNumber				An instance of PhoneNumber representing the person's phone number.
	* @exception InvalidNameException	On empty first name entry.
	* @see								InvalidNameException
	*/
	public Person (String firstName, String lastName, Address address, PhoneNumber phoneNumber) throws InvalidNameException {
		this(firstName, lastName, address);
		this.phoneNumber = phoneNumber;
	}

	/**
	* Returns the person's first name.
	*/
	public String getFirstName () {
		return firstName;
	}
	
	/**
	* Returns the person's last name.
	*/
	public String getLastName () {
		return lastName;
	}

	/**
	* Returns the person's address.
	*/
	public Address getAddress () {
		return address;
	}

	/**
	* Returns the person's phone number.
	*/
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	/**
	* This method can be used to edit the person's address.
	* @exception 	InvalidAddressException
	* @see			InvalidAddressException
	*/
	public void editAddress() throws InvalidAddressException {
		address.editAddress();
	}

	/**
	* This method can be used to edit the person's phone number.
	* @param phoneNumber	A string represrentation of the person's new phone number.
	* @exception 			InvalidPhoneNumberException
	* @see					InvalidPhoneNumberException
	*/
	public void editPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		this.phoneNumber.editPhoneNumber(phoneNumber);
	}

	/**
	* Convert the Person into its mailing format String  representation.
	* @return A String representation of the Person instance.
	*/
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
