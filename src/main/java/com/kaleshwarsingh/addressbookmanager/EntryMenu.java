package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Displays the options available to the user for a Person (entry) and takes input from the user, which specifies the desired option.
 */
public class EntryMenu  {

	private Person person;

	/**
	 * Constructs an EntryMenu for the Person specified by the parameter address.
	 * @param person	A reference to the Person for which the EntryMenu will be created.
	 */
	public EntryMenu (Person person) {
		this.person = person;
	}

	/**
	 *Continuously displays the entry menu. Gets the option selected from standard input and performs the appropriate action. Actions include viewing the entry, editing the address of the entry, editing the phone number of the entry, and exiting the entry menu.
	 */
	public void displayMenu () {
		while (true) {
			int option = -1;
			Scanner sc = new Scanner(System.in);
	
			while ((option < 1) || (option > 4)) {
				System.out.println("________________________________________________________________");
				System.out.println("                          ENTRY MENU");
				System.out.println("________________________________________________________________");
				System.out.println("1. View Entry");
				System.out.println("2. Edit Address");
				System.out.println("3. Edit Phone Number");
				System.out.println("4. Done (Go back)");

				System.out.println("\nEnter the corresponding number to select an option: ");

				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();				// Clear the buffer
					System.out.println("\nERROR: Entered option must be a number form 1 to 4");
					System.out.println("Please try again.\n");
					continue;
				}

				if ((option < 1) || (option > 4)) {
					System.out.println("\nERROR: Entered option must be a number form 1 to 4");
					System.out.println("Please try again.\n");
				}
			}
			System.out.println("________________________________________________________________");

			switch (option) {
				case 1:
					this.viewEntry();
					break;
				case 2:
					this.editAddress();
					break;
				case 3:
					this.editPhoneNumber();
					break;
				case 4:
					System.out.println("Exiting Entry Menu...");
					return;
				default:
					break;
			}
			System.out.println("________________________________________________________________");
		}
	}

	public void viewEntry() {
		System.out.println("\n\tEntry Details:");
		System.out.println(person);
	}

	private void editAddress() {
		new AddressMenu(person.getAddress()).displayMenu();
	}

	public void editPhoneNumber() {
		Scanner sc = new Scanner(System.in);
		String phoneNumber = null;
		
		try {
			System.out.print("Enter phone number: ");
			phoneNumber = sc.nextLine();
			person.getPhoneNumber().editPhoneNumber(phoneNumber);
		} catch (InvalidPhoneNumberException e) {
			System.out.println("ERROR:\n\t");
			System.out.println(e.getMessage());
		}
	}
}
