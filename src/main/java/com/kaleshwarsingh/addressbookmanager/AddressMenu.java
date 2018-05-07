package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * Display the options available to the user for an Address and takes input from the user, which specifies the desired option.
 */
public class AddressMenu {
	private Address address;

	/**
	 * Constructs an AddressMenu for the Address specified by the parameter address.
	 * @param address		A reference to the Address for which the AddressMenu will be created.
	 */
	public AddressMenu(Address address) {
		this.address = address;
	}

	/**
	 *  Continuouse displays the address menu. Gets the option selected from standard input and performs the appropriate action. Actions include viewing the address, editing the entire address, editing a specific field of the address, and exiting the address menu.
	 */
	public void displayMenu() {
		while (true) {
			int option = -1;
			Scanner sc = new Scanner(System.in);
			while ((option < 1) || (option > 8)) {
				System.out.println("________________________________________________________________");
				System.out.println("                          ADDRESS MENU");
				System.out.println("________________________________________________________________");
				System.out.println("1. View address");
				System.out.println("2. Edit address");
				System.out.println("3. Edit house number");
				System.out.println("4. Edit street");
				System.out.println("5. Edit city");
				System.out.println("6. Edit state");
				System.out.println("7. Edit zip code");
				System.out.println("8. Done (Go back)");

				System.out.print("\nEnter the corresponding number to select an option: ");

				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();			// Clear the buffer
					System.out.println("\nERROR: Entered option must be a number from 1 to 8");
					System.out.println("Please try again.\n");
					continue;
				}
				if ((option < 1) || (option > 8)) {
					System.out.println("\nERROR: Entered option must be a number from 1 to 8");
					System.out.println("Please try again.\n");
				}
			}

			System.out.println("________________________________________________________________");

			switch (option) {
				case 1:
					if (address.toString().length() > 0) {
						System.out.println("\n\tAddress:");
						System.out.println(address);
					} else 
						System.out.println("No address available for this entry.");
					System.out.println();
					break;
				case 2:
					this.editAddress();
					break;
				case 3:
					this.editHouseNumber();
					break;
				case 4:
					this.editStreet();
					break;
				case 5:
					this.editCity();
					break;
				case 6:
					this.editState();
					break;
				case 7:
					this.editZipCode();
					break;
				case 8:
					System.out.println("Exiting address menu...");
					return;
				default:
					break;
			}

			System.out.println("________________________________________________________________");
		}
	}

	private int editHouseNumber () {
		Scanner sc = new Scanner(System.in);
		int houseNumber = -1;

		try {
			System.out.print("Enter the house number: ");
			houseNumber = sc.nextInt();
			address.editHouseNumber(houseNumber);
		} catch (InputMismatchException e) {
			sc.nextLine();		// Clear the buffer
			System.out.println("ERROR: House number must be a number.");
			return -1;
		} catch (InvalidAddressException e) {
			System.out.println("ERROR:\n\t");
			System.out.println(e.getMessage());
			return -2;
		}
		return 0;
	}
	
	private void editStreet () {
		Scanner sc = new Scanner(System.in);
		String street = null;
		System.out.print("Enter the street: ");
		street = sc.nextLine();
		address.editStreet(street);
	}

	private void editCity () {
		Scanner sc = new Scanner(System.in);
		String city = null;
		System.out.print("Enter the city: ");
		city = sc.nextLine();
		address.editCity(city);
	}

	private void editState () {
		Scanner sc = new Scanner(System.in);
		String state = null;
		System.out.print("Enter the state: ");
		state = sc.nextLine();
		address.editState(state);
	}

	private int editZipCode () {
		Scanner sc = new Scanner(System.in);
		String zipCode = null;
		try {
			System.out.print("Enter the zip code: ");
			zipCode = sc.nextLine();
			address.editZipCode(zipCode);
		} catch (InvalidAddressException e) {
			System.out.println("ERROR:\n\t");
			System.out.println(e.getMessage());
			return -1;
		}
		return 0;
	}
	
	/** 
	 * Gets data from standard input for and updates the address fields accordingly.
	 */
	public void editAddress () {
		if (this.editHouseNumber() < 0)
			return;
		this.editStreet();
		this.editCity();
		this.editState();
		if (this.editZipCode() < 0)
			return;
	}
}
