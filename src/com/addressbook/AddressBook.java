package com.addressbook;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Collections;

public class AddressBook implements Iterable<Person>, Serializable {
	private List<Person> entries;

	public AddressBook() {
		entries = new ArrayList<Person>();
	}

	/**
	 * Appends the specified entry to the end of the address book.
	 */
	public void createEntry () throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		String firstName = "";
		String lastName = "";
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the first name of the person: ");
		firstName = sc.nextLine();
		if (firstName.length() == 0)
			throw new InvalidNameException("First name cannot be empty", firstName);
		System.out.print("Enter the last name of the person: ");
		lastName = sc.nextLine();
		Person p = new Person(firstName, lastName);
		p.editPerson();
		entries.add(p);
	}

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
	public int findEntry (Person person) {
		return entries.indexOf(person);
	}

	/**
	 * Returns the index of the first occurrence of the specified entry in the address book, or -1 if the address book does not contain the entry.
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
	 * Returns the index of the last occurrence of the specified entry in the address book, or -1 if the address book does not contain the entry.
	 */
	public int findLastEntry (Person person) {
		return entries.lastIndexOf(person);
	}
	
	/**
	 * Returns the index of the last occurrence of the specified entry in the address book, or -1 if the address book does not contain the entry.
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
	 * Returns the entry at the specified position in the address book.
	 */
	public Person getEntry(int index) {
		return entries.get(index);
	}

	public void editEntry() throws InvalidNameException, InvalidAddressException, InvalidPhoneNumberException {
		String firstName = "";
		String lastName = "";
		int entryIndex;
		Scanner sc = new Scanner(System.in);

		System.out.println("Which person's information do you want to change?");
		System.out.print("Enter their first name: ");
		firstName = sc.nextLine();
		if (firstName.length() == 0)
			throw new InvalidNameException("First name cannot be empty", firstName);
		System.out.print("Enter their last name: ");
		lastName = sc.nextLine();

		entryIndex = this.findEntry(firstName, lastName);

		if (entryIndex < 0)
			System.out.println("ERROR: No such entry found.");
		else {
			System.out.println("Editing " + firstName + " " + lastName);
			entries.get(entryIndex).editPerson();
		}
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

	/**
	 * Sorts the address book entries by last name (ignoring cases), with ties broken by first name if necessary.
	 */
	public void sortByName() {
		Collections.sort(entries, new NameSort());
	}

	/**
	 * Sorts the address book entries by the zip codes, with ties being broken by name if necessary.
	 */
	public void sortByZipCode() {
		Collections.sort(entries, new ZipCodeSort());
	}
		
	/** 
	 * Returns an iterator that can be used to iterate over the members of the address book.
	 */
	@Override
	public Iterator<Person> iterator() {
		return entries.iterator();
	}

	/**
	 * Returns a string representation of the address book.
	 */
	@Override
	public String toString() {
		return entries.toString();
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



