import java.util.Scanner;
import com.addressbook.*;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	public static void main (String[] args) {
		AddressBook addrbk = new AddressBook();
		for (int i = 0; i < 5; i++) {
			try {
				addrbk.createEntry();
				System.out.println(addrbk);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
		}

		addrbk.sortByName();
		System.out.println(addrbk);
		System.out.println("\n");

		addrbk.sortByZipCode();
		System.out.println(addrbk);
		System.out.println("\n");
	}
}
