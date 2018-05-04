package com.addressbook;

import java.util.Scanner;
import java.util.InputMismatchException;

public class EntryMenu  {

	private Person person;

	public EntryMenu (Person person) {
		this.person = person;
	}

	public void displayMenu () {
		while (true) {
			int option = -1;
			Scanner sc = new Scanner(System.in);
	
			while ((option < 1) || (option > 4)) {
				System.out.println("\t\tENTRY");
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
		}
	}

	public void viewEntry() {
		System.out.println("Entry Details:");
		System.out.println(person);
	}

	public void editAddress() {
		// TODO: Implement the address menu
	}

	public void editPhoneNumber() {
		Scanner sc = new Scanner(System.in);
		String phoneNumber = null;
		
		try {
			System.out.print("Enter the phone number: ");
			phoneNumber = sc.nextLine();
			person.getPhoneNumber().editPhoneNumber(phoneNumber);
		} catch (InvalidPhoneNumberException e) {
			System.out.println("ERROR:\n\t");
			System.out.println(e.getMessage());
		}
	}
}







