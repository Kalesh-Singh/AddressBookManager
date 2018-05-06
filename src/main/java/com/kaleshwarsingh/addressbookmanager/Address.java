package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Serializable;

/**
*<h1>Address</h1>
<p>The Address class provides a convenient way of representing many different formats of addresses, regardless of the format of the address."</p>
*
* @author	Kaleshwar Singh
* @version	1.0
* @since	2018-04-28
*/

public class Address implements Serializable {
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
		this.editHouseNumber(houseNumber);
		this.editStreet(street);
		this.editCity(city);
		this.editState(state);
		this.editZipCode(zipCode);
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
	
	/**
	 * Returns the person's zip code.
	 * @return A String representation of the person's zip code.
	 */
	public String getZipCode() {
		return zipCode;
	}

	public void editHouseNumber(int houseNumber) throws InvalidAddressException {
		if (houseNumber < 0)
			throw new InvalidAddressException("House number cannot be negative", Integer.toString(houseNumber));
		this.houseNumber = houseNumber;
	}

	public void editStreet(String street) {
		this.street = street;
	}

	public void editCity(String city) {
		this.city = city;
	}

	public void editState(String state) {
		this.state = state;
	}

	public void editZipCode(String zipCode) throws InvalidAddressException {
		if (!isInteger(zipCode))
			throw new InvalidAddressException("Zip code must be a number", zipCode);
		if (Integer.parseInt(zipCode) < 0)
			throw new InvalidAddressException("Zip code cannot be a negative number", zipCode);

		this.zipCode = zipCode;
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
			sb.append("\n" + city);
		if (state.length() > 0)
			sb.append("\n" + state);
		if (zipCode.length() > 0)
			sb.append("\n" + zipCode);

		return sb.toString();
	}
	
	/**
	* This method provides a way to compare Addresses for equality.
	* @return A boolean indicating whether the addresses are equal or not.
	*/
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Address))
			return false;
		Address other = (Address) o;
		return 
			(this.houseNumber == other.houseNumber) &&
			(this.street.equals(other.street)) &&
			(this.city.equals(other.city)) &&
			(this.state.equals(other.state)) &&
			(this.zipCode.equals(other.zipCode));
	}
}
