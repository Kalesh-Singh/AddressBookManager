package com.kaleshwarsingh.addressbookmanager;

import java.io.Serializable;

/**
 *Provides a convenient way of representing a Person with first and last names, address and phone number.
 *
 * @author	Kaleshwar Singh
 * @version	1.0
 * @since	2018-04-30
 */

public class Person implements Serializable {
	private String firstName = null;
	private String lastName = null;
	Address address = null;
	PhoneNumber phoneNumber = null;

	/**
	* Constructs an empty Person.
	*/
	public Person () {
		address = new Address();
		phoneNumber = new PhoneNumber();
	}
	
	/**
	 * Constructs and initializes a Person to the person represented by the strings firstName and lastName.
	 * @param firstName					A String representing the person's first name.
	 * @param lastName					A String representing the person's last name.
	 * @exception InvalidNameException	On empty first name received.
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
	 * Constructs and initializes a Person to the person represented by the strings firstName and lastName and the Address address.
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
	 * Constructs and initializes a Person to the person represented by the strings firstName and lastName and the PhoneNumber phoneNumber.
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
	 * Constructs and initializes a Person to the person represented by the strings firstName and lastName, the Address address, and the PhoneNumber phoneNumber.
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
	 * Returns the first name of the Person.
	 * @return A string representation of the Person's first name.
	 */
	public String getFirstName () {
		return firstName;
	}
	
	/**
	 * Returns the last name of the Person.
	 * @return A string representation of the Person's last name.
	 */
	public String getLastName () {
		return lastName;
	}

	/**
	 * Returns the address of the Person.
	 * @return An Address representation of the Person's address.
	 */
	public Address getAddress () {
		return address;
	}

	/**
	 * Returns the phone number of the Person.
	 * @return A PhoneNumber representation of the Person's phone number.
	 */
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
 	 * Converts the Person in to a String representation in the format:<br>
	 * &emsp;Firstname Lastname<br>
	 * &emsp;House Number<br>
	 * &emsp;City<br>
	 * &emsp;State<br>
	 * &emsp;Zip code<br>
	 * @return A String representation of the Person.
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder(200);

		if (firstName != null)
			sb.append(firstName);
		if (lastName != null)
			sb.append(" " + lastName);
		if (address.toString().length() > 0)
			sb.append("\n" + address.toString());
		if (phoneNumber.toString().length() > 0)
			sb.append("\n" + phoneNumber.toString());

		return sb.toString();
	}

	/**
	 * Compares this person to the specified object o.
	 * @param o An object to be compared with for equality.
	 * @return A boolean indicating whether the person and object are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Person))
			return false;
		Person other = (Person) o;
		return
			(this.firstName.equals(other.firstName)) &&
			(this.lastName.equals(other.lastName));
	}	
}
