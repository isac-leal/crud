package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int option;
		do {
			line();
			System.out.println("         MENU         ");
			line();
			System.out.println("Select an option:");
			System.out.println("[1] - Create an user");
			System.out.println("[2] - Show a user data");
			System.out.println("[3] - Update a user data");
			System.out.println("[4] - Remove an user");
			System.out.println("[5] - Leave");
			System.out.println("Enter: ");
			option = sc.nextInt();
			sc.nextLine();
		} while (option < 1 || option > 5);

	}

	public static void line() {
		System.out.println("----------------------");
	}
}
