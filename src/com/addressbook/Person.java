package com.addressbook;

import java.io.Serializable;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
<h1>Person</h1>
<p>The Person provides a convenient way of representing a Person with first and last names, address and phone number.</p>
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
	 * This method can be used to edit the person's information.
	 * @exception 	InvalidAddressException
	 * @exception 	InvalidPhoneNumberException
	 * @see			InvalidAddressException
	 * @see			InvalidPhoneNumberException
	 */
	public void editPerson () throws InvalidAddressException, InvalidPhoneNumberException {
		int option = -1;
		Scanner sc = new Scanner(System.in);
		while ((option < 1) || (option > 3)) {
			System.out.println("Select the field you'd like to edit:");
			System.out.println("1. Address");
			System.out.println("2. Phone number");
			System.out.println("3. Done");
			System.out.print("Enter an option (1 - 3): ");
			try {
				option = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nERROR: The entered option must be a number from 1 to 3.");
				System.out.println("Please try again\n");
				sc.nextLine();		// Clear the buffer
			}
			System.out.println();
		}

		switch (option) {
			case 1:
				System.out.println("Editing " + firstName + " " + lastName + "'s address:"); 
				address.editAddress();
				this.editPerson();
				break;
			case 2:
				System.out.println("Editing " + firstName + " " + lastName + "'s phoneNumber");
				System.out.print("Please enter the new phone number: ");
				sc.nextLine();		// Clearing the buffer.
				phoneNumber.editPhoneNumber(sc.nextLine());
				this.editPerson();
				break;
			case 3:
				System.out.println("Changes accepted..\n");
				break;
			default:
				break;
		}
	}

	/**
	* Convert the Person into its mailing format String  representation.
	* @return A String representation of the Person instance.
	*/
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder(200);

		if (firstName != null)
			sb.append("\n\nName: " + firstName);
		if (lastName != null)
			sb.append(" " + lastName);
		if (address.toString().length() > 0)
			sb.append("\nAddress: " + address.toString());
		if (phoneNumber.toString().length() > 0)
			sb.append("\nPhone Number: " + phoneNumber.toString());

		return sb.toString();
	}

	/* This method provides a way of comparing 2 person's to check if they are the same.
	 * @return A boolean indicating whether the persons are the same.
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
