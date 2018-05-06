package com.kaleshwarsingh.addressbookmanager;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FilenameFilter;

public class AddressBookManagerMenu {

	private AddressBookManager addressBookManager;

	// Regular expression to validate file name (address book name)
	private static final Pattern p = Pattern.compile("^[0-9a-zA-Z_\\-.]+$");
	private Matcher m;

	public AddressBookManagerMenu (AddressBookManager addressBookManager) {
		this.addressBookManager = addressBookManager;
	}
	
	public void displayMenu () {
		while (true) {		
			int option = -1;
			Scanner sc = new Scanner(System.in);
	
			while ((option < 1) || (option > 11)) {	
				System.out.println("________________________________________________________________");
				System.out.println("                    ADDRESS BOOK MANAGER MENU");
				System.out.println("________________________________________________________________");

				System.out.println(" 1. New");
				System.out.println(" 2. Open");
				System.out.println(" 3. View");
				System.out.println(" 4. Edit");
				System.out.println(" 5. Sort by name");
				System.out.println(" 6. Sort by zip code");
				System.out.println(" 7. Save");
				System.out.println(" 8. Save As");
				System.out.println(" 9. Close");
				System.out.println("10. Close All");
				System.out.println("11. Exit program"); 
				
				System.out.print("\nEnter the corresponding number to select an option: ");
				try {
					option = sc.nextInt();
				} catch (InputMismatchException e) {
					sc.nextLine();			// Clear the buffer
					System.out.println("\nERROR: Entered option must be a number from 1 to 11");
					System.out.println("Please try again.\n");
					continue;
				}

				if ((option < 1) || (option > 11)) {
					System.out.println("\nERROR: Entered option must be a number from 1 to 11");
					System.out.println("Please try again.\n");
				}

			}
			
			System.out.println("________________________________________________________________");

			switch (option) {
				case 1:
					this.createAddressBook();
					break;
				case 2:
					this.openAddressBook();
					break;
				case 3:
					this.viewAddressBook();
					break;
				case 4:
					this.editAddressBook();
					break;
				case 5:
					this.sortAddressBookByName();
					break;
				case 6:
					this.sortAddressBookByZipCode();
					break;
				case 7:
					this.saveAddressBook();
					break;
				case 8:
					this.saveAddressBookAs();
					break;
				case 9:
					this.closeAddressBook();
					break;
				case 10:
					this.closeAll();
					break;
				case 11:
					this.closeAll();
					System.out.println("Program terminated successfully.");
					System.exit(0);
					break;
				default:
					System.out.println("\nERROR: Entered option must be a number from 1 to 11");
					System.out.println("Please try again.\n");
					break;
			}
			System.out.println("________________________________________________________________");

		}
	}		

	private void createAddressBook () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name of the new address book: ");
		String addressBookName = null;
		try {
			addressBookName = sc.nextLine();
			m = p.matcher(addressBookName);
			if ((m.find()) && (!addressBookName.equals(".")) && (!addressBookName.equals(".."))) {
				addressBookManager.createAddressBook(addressBookName);
				System.out.println("Created new address book, " + addressBookName);
			} else {
				System.out.println("ERROR:");
				if (addressBookName.equals("."))
					System.out.println("\tAddress book name cannot be \".\"");
				else if (addressBookName.equals(".."))
					System.out.println("\tAddress book name cannot be \"..\"");
				else
					System.out.println("\tAddress book name can only contain the characters 0-9a-zA-Z_-.");
				System.out.println("\tPlease try again.\n");
				this.createAddressBook();
			}
		} catch (InvalidNameException e) {
			System.out.print("\nERROR: Failed to create new address book, " + addressBookName);
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	private void openAddressBook () {
		
		File dir = new File(".");
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".dat");
			}
		});


		if (files.length > 0) {
			System.out.println("Saved Address Books:");
			for (File datFile : files) {
				String fileName = datFile.getName();
				System.out.println("\t" + fileName.substring(1, fileName.length()-4));
			}

			Scanner sc = new Scanner(System.in);
			System.out.print("Enter name of the address book you want to open: ");
			String addressBookName = null;
			try {
				addressBookName = sc.nextLine();
				addressBookManager.openAddressBook(addressBookName);
			} catch (ClassNotFoundException e) {
				System.out.println("ERROR: Deserialized object does not match class.");
				System.out.println(e.getMessage());
				System.out.println();
			} catch (IOException e) {
				System.out.println("ERROR: No data found for, " + addressBookName);
				System.out.println(e.getMessage());
				System.out.println();
			}

		} else
			System.out.println("No Saved Address Books");
		System.out.println();
	}

	private void saveAddressBook() {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			String addressBookName = null;
			try {
				System.out.print("Enter name of the address book you want to save: ");
				addressBookName = sc.nextLine();
				addressBookManager.saveAddressBook(addressBookName);
			} catch (IOException e) {
				System.out.print("ERROR: Could not save, " + addressBookName);
				System.out.println(e.getMessage());
				System.out.println();
			} catch (InvalidNameException e) {
				System.out.println("ERROR:");
				System.out.println("\t" + e.getMessage());
			}
		}
	}
	
	private void saveAddressBookAs() {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			String addressBookName = null;
			String fileName = null;
			try {
				System.out.print("Enter name of the address book you want to save: ");
				addressBookName = sc.nextLine();
				System.out.print("Enter the name you want to save the address book as: ");
				fileName = sc.nextLine();
				addressBookManager.saveAddressBook(addressBookName, fileName);
				// Add the new save as to the open address books and close the previous book
				AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
				addressBookManager.getAddressBooks().put(fileName, addressBook);
				addressBookManager.getAddressBooks().remove(addressBookName);

			} catch (IOException e) {
				System.out.print("ERROR: Could not save, " + addressBookName);
				System.out.println(e.getMessage());
				System.out.println();
			} catch (InvalidNameException e) {
				System.out.println("ERROR:");
				System.out.println("\t" + e.getMessage());
			}
		}
	}

	private void closeAddressBook () {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			String addressBookName = null;
			int option = -1;
			try {
				System.out.print("Enter name of the address book you want to close: ");
				addressBookName = sc.nextLine();
				while ((option < 1) || (option > 2)) {
					try {
						System.out.println("Do you want to save any unsaved changes before closing?");
						System.out.println("1. Yes");
						System.out.println("2. No");
						System.out.print("\nEnter the corresponding number to select an option: ");
						option = sc.nextInt();
					} catch (InputMismatchException e) {
						sc.nextLine();			// Clear the buffer
						System.out.println("\nERROR: Entered option must be a number from 1 to 2");
						System.out.println("Please try again.\n");
						continue;
					}
					if ((option < 1) || (option > 2)) {
						System.out.println("\nERROR: Entered option must be a number from 1 to 2");
						System.out.println("Please try again.\n");
					}
				}
				switch (option) {
					case 1:
						addressBookManager.closeAndSaveAddressBook(addressBookName);
						System.out.println("Address Book, " + addressBookName + " saved and closed.");
						break;
					case 2:
						addressBookManager.closeAddressBookWithoutSaving(addressBookName);
						System.out.println("Address Book, " + addressBookName + " closed and changes discarded");
						break;
					default:
						break;
				}

			} catch (InvalidNameException e) {
				System.out.println("ERROR: " + e.getMessage());
				System.out.println();
			} catch (IOException e) {
				System.out.print("ERROR: Could not close and save address book, " + addressBookName);
				System.out.println(e.getMessage());
				System.out.println();
			} 
		}
	}

	private void closeAll () {
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			int option = -1;
			try {
				while ((option < 1) || (option > 2)) {
					try {
						System.out.println("Do you want to save any unsaved changes before closing?");
						System.out.println("1. Yes");
						System.out.println("2. No");
						System.out.print("\nEnter the corresponding number to select an option: ");
						option = sc.nextInt();
					} catch (InputMismatchException e) {
						sc.nextLine();			// Clear the buffer
						System.out.println("\nERROR: Entered option must be a number from 1 to 2");
						System.out.println("Please try again.\n");
						continue;
					}
					if ((option < 1) || (option > 2)) {
						System.out.println("\nERROR: Entered option must be a number from 1 to 2");
						System.out.println("Please try again.\n");
					}
				}
				switch (option) {
					case 1:
						addressBookManager.closeAndSaveAll();
						System.out.println("All address books saved and closed.");
						break;
					case 2:
						addressBookManager.closeAllWithoutSaving();
						System.out.println("All address book  closed and changes discarded");
						break;
					default:
						break;
				}

			} catch (InvalidNameException e) {
				System.out.println("ERROR: " + e.getMessage());
				System.out.println();
			} catch (IOException e) {
				System.out.print("ERROR: Could not close (and save) all address book");
				System.out.println(e.getMessage());
				System.out.println();
			} 
		} else {
			System.out.println("No address books are open.");
			System.out.println();
		}
	}

	private void sortAddressBookByName () {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);		
			System.out.print("Enter name of the address book to sort by name: ");
			String addressBookName = sc.nextLine();
			AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
			if (addressBook == null)
				System.out.println(addressBookName + ", is not open.");
			else {
				addressBook.sortByName();
				System.out.println(addressBookName + " sorted by name.");
			}
		}				
	}

	private void sortAddressBookByZipCode () {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);		
			System.out.print("Enter name of the address book to sort by zip code: ");
			String addressBookName = sc.nextLine();
			AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
			if (addressBook == null)
				System.out.println(addressBookName + ", is not open.");
			else {
				addressBook.sortByZipCode();
				System.out.println(addressBookName + " sorted by zip code.");
			}	
		}			
	}

	private void editAddressBook () {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter name of the address book you want to edit: ");
			String addressBookName = sc.nextLine();
			AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
			if (addressBook == null)
				System.out.println(addressBookName + ", is not open.");
			else {
				System.out.println("Editing " + addressBookName + " : ");
				new AddressBookMenu(addressBook).displayMenu();
			}
		}
	}

	private void viewAddressBook () {
		addressBookManager.showOpenAddressBooks();
		if (addressBookManager.getAddressBooks().size() > 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter name of the address book you want to view: ");
			String addressBookName = sc.nextLine();
			AddressBook addressBook = addressBookManager.getAddressBooks().get(addressBookName);
			if (addressBook == null)
				System.out.println("No such address book is open: " + addressBookName);
			else {
				System.out.println();
				System.out.println("ADDRESS BOOK: " + addressBookName);
				System.out.println();
				new AddressBookMenu(addressBook).showAllEntries();
			}
		}
	}

}
