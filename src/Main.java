import java.util.Scanner;

import com.addressbook.PhoneNumber;
import com.addressbook.InvalidPhoneNumberException;
import com.addressbook.Address;
import com.addressbook.InvalidAddressException;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	public static void main (String[] args) {
		try {
			Address myAddress = new Address();
			for (int i = 0; i < 5; i++) {
				myAddress.editAddress();
				System.out.println("My address is " + myAddress);
			}
		} catch (InvalidAddressException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
