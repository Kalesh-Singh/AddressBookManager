package com.kaleshwarsingh.addressbookmanager;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Collections;

/**
 *Resizable-array implementation of an address book. Permits elements of the type Person. This class also provides methods to add a new entry, delete an entry, edit an entry, find an entry, and sort the address book by name or zip code.
 */
public class AddressBook implements Iterable<Person>, Serializable {
	private List<Person> entries;

	/**
	 *Constructs an empty AddressBook.
	 */
	public AddressBook() {
		entries = new ArrayList<Person>();
	}

	/**
	 * Creates a new Person entry using the strngs firstName and lastName and adds it to the address book.
	 *@param firstName					A String representation of the first name of the new entry.
	 *@param lastName					A String representation of the last name of the new entry.
	 *@exception InvalidNameException 	On empty first name received.
	 */
	public void addEntry (String firstName, String lastName) throws InvalidNameException {
		if (firstName.length() == 0)
			throw new InvalidNameException("First name cannot be empty", firstName);
		entries.add(new Person(firstName, lastName));
	}

	/**
	 *Adds person as a new entry in the address book.
	 *@param person		A reference to the entry to be added to the address book.
	 */

	public void addEntry (Person person) {
		entries.add(person);
	}

	/**
	 * Returns the index of the first occurrence of the entry with matching first name and last name in the address book, or -1 if no matching entry is found.
	 * @param firstName					A String representing the first name of the entry to be found.
	 * @param lastName					A String representing the last name of the entry to be found.
	 * @exception InvalidNameException	On empty first name received.
	 */
	public int findEntry (String firstName, String lastName) throws InvalidNameException {
		Person p = new Person(firstName, lastName);
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).equals(p))
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the index of the last occurrence of the entry with matching first name and last name in the address book, or -1 if no matching entry is found.
	 * @param firstName					A String representing the first name of the entry to be found.
	 * @param lastName					A String representing the last name of the entry to be found.
	 * @exception InvalidNameException	On empty first name received.
 */
	public int findLastEntry (String firstName, String lastName) throws InvalidNameException {
		Person p = new Person(firstName, lastName);
		for (int i = entries.size() - 1; i <= 0; i--) {
			if (entries.get(i).equals(p))
				return i;
		}
		return -1;
	}

	/**
	 * Returns a list of all entries in the address book.
	 * @return A List of all the entries in the address book.
	 */
	public List<Person> getEntries() {
		return entries;
	}

	/**
	 * Deletes the entry at the specified index in the address book.
	 */
	public void deleteEntry (int index) {
		entries.remove(index);
	}

	/**
	 * Sorts the address book entries by last name (ignoring cases), with ties broken by first name if necessary.
	 */
	public void sortByName() {
		Collections.sort(entries, new NameSort());
	}

	/**
	 * Sorts the address book entries by address zip codes, with ties broken by name if necessary.
	 */
	public void sortByZipCode() {
		Collections.sort(entries, new ZipCodeSort());
	}
		
	/** 
	 * Returns an iterator that can be used to iterate over the members of the address book.
	 * @return An interator of type Interator<Person>
	 */
	@Override
	public Iterator<Person> iterator() {
		return entries.iterator();
	}

	/**
	 * Returns a string representation of the address book.
	 * @return A string representation of the address book
	 */
	@Override
	public String toString() {
		return entries.toString();
	}

	public int size() {
		return entries.size();
	}
	
}

/**
 * Allows you to sort the address book based on the last name of the person in the entries, with ties broken by last name if necessary. 
 * Note: Case is ignored.
 */
class NameSort implements Comparator<Person> {
	public int compare (Person x, Person y) {
		int v = x.getLastName().toLowerCase()
		.compareTo(y.getLastName().toLowerCase());

		return (v == 0) ? x.getFirstName().toLowerCase()
		.compareTo(y.getFirstName().toLowerCase()) : v;
	}
}

/**
 * Allows you to sort the address book based on the zip code of the address in the entries, with ties broken by name if necessary.
 * Note: Case is ignored.
 */
class ZipCodeSort implements Comparator<Person> {
	public int compare (Person x, Person y) {
		int v1 = x.getAddress().getZipCode()
		.compareTo(y.getAddress().getZipCode());

		int v2 = (v1 == 0) ? x.getLastName().toLowerCase()
		.compareTo(y.getLastName().toLowerCase()) : v1;

		return (v2 ==0) ? x.getFirstName().toLowerCase()
		.compareTo(y.getFirstName().toLowerCase()) : v2;
	}
}
