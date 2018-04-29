import java.util.Scanner;

import com.addressbook.PhoneNumber;
import com.addressbook.InvalidPhoneNumberException;
import com.addressbook.Address;
import com.addressbook.InvalidAddressException;
import com.addressbook.Person;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	public static void main (String[] args) {
		try {
			Person person = new Person("John", "Wick");
			for (int i = 0; i < 5; i++) {
				System.out.println("\n\nPerson\n" + person);
				System.out.println("\t\tEnter the address information:");
				person.editAddress();
				System.out.println("\n\nPerson\n" + person);
				System.out.println("Enter the phoneNumber: ");
				person.editPhoneNumber(scanner.nextLine());
				System.out.println("\n\nPerson\n" + person);
				System.out.println("\n\n\n");
			}
		} catch (InvalidAddressException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
