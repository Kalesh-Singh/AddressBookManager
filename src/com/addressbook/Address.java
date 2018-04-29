package com.addressbook;

import java.util.Scanner;

import java.util.InputMismatchException;

/**
*<h1>Address</h1>
<p>The Address class provides a convenient way of representing many different formats of addresses, regardless of the format of the address."</p>
*
* @author	Kaleshwar Singh
* @version	1.0
* @since	2018-04-28
*/

public class Address {
	private int houseNumber = -1;
	private String street = "";
	private String city = "";
	private String state = "";
	private String zipCode = "";

	private static Scanner sc = new Scanner(System.in);

	/**
	* This is the default constructor of the Address class.
	*/
	public Address () { }

	/**
	* This constructor creates and initializes an instance of the address.
	* @param houseNumber					An int representing the house number.
	* @param street							A String representing the street.
	* @param city							A String represnting the city.
	* @param state							A String representing the state.
	* @param zipCode						An String representing the zip code.
	* @exception InvalidAddressException	On invalid address entry.
	* @see InvaildAddressException
	*/
	public Address (int houseNumber, String street, String city, String state, String zipCode) throws InvalidAddressException {
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		validateAddress();		// validate the address data
	}
	
	/**
	* This method can be used to edit an instaance of the Address class.
	* @param houseNumber					An int representing the house number.
	* @param street							A String representing the street.
	* @param city							A String represnting the city.
	* @param state							A String representing the state.
	* @param zipCode						An String representing the zip code.
	* @exception InvalidAddressException	On invalid address entry.
	* @see InvaildAddressException
	*/
	public void editAddress() throws InvalidAddressException {
		try {
			System.out.print("Enter the house number: ");
			houseNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the street: ");
			street = sc.nextLine();
			System.out.print("Enter the city: ");
			city = sc.nextLine();
			System.out.print("Enter the state: ");
			state = sc.nextLine();
			System.out.print("Enter the zip code: ");
			zipCode = sc.nextLine();
			validateAddress();		// validate the address data
		} catch (InputMismatchException e) {
			throw new InvalidAddressException("House number must be a number", sc.nextLine(), e);
		} catch (Exception e){
			throw e;
		}
	}

	private void validateAddress () throws InvalidAddressException{
		if (houseNumber <  0)
			throw new InvalidAddressException("House number cannot be negative", Integer.toString(houseNumber));
		if (!isInteger(zipCode))
			throw new InvalidAddressException("Zip code must be a number", zipCode);
		if (Integer.parseInt(zipCode) < 0)
			throw new InvalidAddressException("Zip code cannot be a negative number", zipCode);
	}

	private boolean isInteger(String str) {
		try {
			int x = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	* Converts the Address to a comma-separated-format String representation of the address.
	* @return A String representation of the Address instance.
	*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);

		if (houseNumber >= 0)
			sb.append(Integer.toString(houseNumber));
		if (street.length() > 0)
			sb.append(" " + street);
		if (city.length() > 0)
			sb.append(", " + city);
		if (state.length() > 0)
			sb.append(", " + state);
		if (zipCode.length() > 0)
			sb.append(" " + zipCode);

		return sb.toString();
	}

}
