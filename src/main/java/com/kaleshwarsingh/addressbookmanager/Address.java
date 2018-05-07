package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Serializable;

/**
 *Provides a convenient way of representing an address, regardless of the format."
 *
 * @author	Kaleshwar Singh
 * @version	1.0
 * @since	2018-04-28
 * @see		InvalidAddressException
 */

public class Address implements Serializable {
	private int houseNumber = -1;
	private String street = "";
	private String city = "";
	private String state = "";
	private String zipCode = "";

	private static Scanner sc = new Scanner(System.in);

	/**
	 * Constructs an empty Address.
	 */
	public Address () { }

	/**
	 * Constructs and initializes an Address to the address represented by the integer houseNumber and the strings street, city, state, and zipCode.
	 * @param houseNumber					An int representing the house number.
	 * @param street						A String representing the street.
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
	 * Returns the zip code of the adddress.
	 * @return A String representation of the zip code of the address.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Edits the house number of the address by replacing the old house number with that indicated by the integer houseNumber.
	 * @param houseNumber					An int representing the house number.
	 * @exception InvalidAddressException	On invalid address entry.
	 * @see InvaildAddressException
	 */
	public void editHouseNumber(int houseNumber) throws InvalidAddressException {
		if (houseNumber < 0)
			throw new InvalidAddressException("House number cannot be negative", Integer.toString(houseNumber));
		this.houseNumber = houseNumber;
	}

	/**
	 * Edits the street of the address by replacing the old street with that indicated by the string street.
	 * @param street						A String representing the street.
	 * @exception InvalidAddressException	On invalid address entry.
	 * @see InvaildAddressException
	 */
	public void editStreet(String street) {
		this.street = street;
	}

	/**
	 * Edits the city of the address by replacing the old city with that indicated by the string city.
	 * @param city							A String represnting the city.
	 * @exception InvalidAddressException	On invalid address entry.
	 * @see InvaildAddressException
	 */
	public void editCity(String city) {
		this.city = city;
	}

	/**
	 * Edits the state of the address by replacing the old state with that indicated by the stringcity.
	 * @param state							A String representing the state.
	 * @exception InvalidAddressException	On invalid address entry.
	 * @see InvaildAddressException
	 */
	public void editState(String state) {
		this.state = state;
	}
	
	/**
	 * Edits the zip code of the address by replacing the old zip code with that indicted by the string zipCode.
	 * @param zipCode						An String representing the zip code.
	 * @exception InvalidAddressException	On invalid address entry.
	 * @see InvaildAddressException
	 */
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
	 * Converts the Address to a String representation in the format:<br>
	 * &emsp;House Number Street<br>
	 * &emsp;City<br>
	 * &emsp;State<br>
	 * &emsp;Zip code<br>
	 * @return A String representation of the address.
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
	 * Compares this address to the specified object o.
	 * @param o An object to be compared with for equality.
	 * @return A boolean indicating whether the address and object are equal.
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
