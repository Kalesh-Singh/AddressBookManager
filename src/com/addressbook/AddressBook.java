package com.addressbook;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Comparator;

public class AddressBook implements Iterable<Person>, Serializable {
	private List<Person> entries = new ArrayList<Person>();

	/**
	 * Appends the specified entry to the end of the address book.
	 */
	public void addEntry (Person person) {
		entries.add(person);
	}

	/**
	 * Inserts the specified entry at the specified position in the address book.
	 */
	public void addEntry(int index, Person person) {
		entries.add(index, person);
	}

	/**
	 * Returns the index of the first occurrence of the specified entry in the address book, or -1 if the address book does not contain the entry.
	 */
	public int indexOf (Person person) {
		return entries.indexOf(person);
	}

	/**
	 * Returns the index of the last occurrence of the specified entry in the address book, or -1 if the address book does not contain the entry.
	 */
	public int lastIndexOf (Person person) {
		return entries.lastIndexOf(person);
	}

	/**
	 * Returns the entry at the specified position in the address book.
	 */
	public Person getEntry(int index) {
		return entries.get(index);
	}

	/**
	 * Deletes the first occurrence of person in the address book.
 	 */
	public void deleteEntry (Person person) {
		entries.remove(person);
	}

	/**
	 * Deletes the entry at the specified index in the address book.
	 */
	public void deleteEntry (int index) {
		entries.remove(index);
	}

		
	@Override
	public Iterator<Person> iterator() {
		return entries.iterator();
	}
}

/**
 * Allows you to sort the address book based on the last name of the person with ties broken by last name if necessary.
 */
class SortByName implements Comparator<Person> {
	public int compare (Person x, Person y) {
		int v = x.getLastName().compareTo(y.getLastName());
		return (v == 0) ? x.getFirstName().compareTo(y.getFirstName()) : v;
	}
}



