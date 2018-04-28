import com.addressbook.PhoneNumber;
import com.addressbook.InvalidPhoneNumberException;

import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);

	public static void main (String[] args) {
		try {
			PhoneNumber myNumber = new PhoneNumber("1-929-355-0415");
			for (int i = 0; i < 15; i++) {
				System.out.println("My phone number is " + myNumber);
				System.out.print("Enter your phone number : ");
				myNumber.editPhoneNumber(scanner.nextLine());
			}
		} catch (InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
