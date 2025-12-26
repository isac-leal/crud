package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.User;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<User> listUsers = new ArrayList<>();

		menu(sdf, listUsers);

		sc.close();
	}

	public static void line() {
		System.out.println("-------------------------");
	}

	public static void cleanConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void menu(SimpleDateFormat sdf, List<User> listUsers) throws ParseException {
		Scanner sc = new Scanner(System.in);
		int option;
		do {
			line();
			System.out.println("	  MENU");
			line();
			System.out.println("Select an option:");
			System.out.println("[1] - Create an user");
			System.out.println("[2] - Show a user data");
			System.out.println("[3] - Update a user data");
			System.out.println("[4] - Delete an user");
			System.out.println("[0] - Leave");
			System.out.print("Enter: ");
			option = sc.nextInt();
		} while (option < 0 || option > 4);

		sc.nextLine();

		switch (option) {
		case 1:
			createUser(sdf, listUsers);
			break;
		case 2:
			System.out.print("Enter the user name: ");
			String findName = sc.nextLine();
			showUserData(listUsers, findName);
			break;
		case 3:
			System.out.print("Enter the user id (1000 + user's year of birth): ");
			int idToFind = sc.nextInt();
			sc.nextLine();
			updateUserData(listUsers, idToFind, sdf);
			break;
		case 4:
			System.out.print("Enter the user id to find (1000 + user's year of birth): ");
			idToFind = sc.nextInt();
			sc.nextLine();
			deleteUser(listUsers, idToFind);
			System.out.print("Do you want turn back to the menu (y/n)? ");
			char answer = sc.nextLine().charAt(0);
			if (answer == 'y') {
				menu(sdf, listUsers);
			} else {
				line();
				System.out.println("	Goodbye!");
				line();
			}
			break;
		case 0:
			line();
			System.out.println("	Goodbye!");
			line();
			break;
		}
	}

	public static void createUser(SimpleDateFormat sdf, List<User> listUsers) throws ParseException {
		Scanner sc = new Scanner(System.in);
		line();
		System.out.println("     NEW USER DATA");
		line();
		System.out.print("Enter the user name: ");
		String name = sc.nextLine();
		System.out.print("Enter the user email: ");
		String email = sc.nextLine();
		System.out.print("Enter the user birth age date: ");
		Date date = sdf.parse(sc.nextLine());
		listUsers.add(new User(name, email, date));
		System.out.print("Do you want turn back to the menu (y/n)? ");
		char answer = sc.nextLine().charAt(0);
		if (answer == 'y') {
			menu(sdf, listUsers);
		} else {
			line();
			System.out.println("	Goodbye!");
			line();
		}
	}

	public static void showUserData(List<User> list, String User) {
		if (list.isEmpty()) {
			line();
			System.out.println("    Error! Empty list!");
			line();
			return;
		}
		for (User u : list) {
			if (User == u.getName()) {
				System.out.println(u);
			} else {
				System.out.println("User not found");
			}
		}
	}

	public static void updateUserData(List<User> list, int idToFind, SimpleDateFormat sdf) throws ParseException {
		if (list.isEmpty()) {
			line();
			System.out.println("    Error! Empty list!");
			line();
			return;
		}
		Scanner sc = new Scanner(System.in);
		for (User u : list) {
			if (idToFind == u.id()) {
				line();
				System.out.println("         UPDATE USER DATA:");
				line();
				System.out.println("Select the data you wish to update:");
				System.out.println("[1] - Name");
				System.out.println("[2] - E-mail");
				System.out.println("[3] - Birth Date");
				System.out.println("[0] - EXIT");
				System.out.print("Enter: ");
				int option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1:
					System.out.print("Enter the user's new name: ");
					String name = sc.nextLine();
					u.setName(name);
					break;
				case 2:
					System.out.print("Enter the user's new email: ");
					String email = sc.nextLine();
					u.setEmail(email);
					break;
				case 3:
					System.out.println("Enter the user's new date of birth");
					Date date = sdf.parse(sc.nextLine());
					u.setBirthDate(date);
					break;
				case 0:
					line();
					System.out.println("	Goodbye!");
					line();
					break;
				}
			}
		}
	}

	public static void deleteUser(List<User> list, int idToFind) {
		Scanner sc = new Scanner(System.in);
		if (list.isEmpty()) {
			line();
			System.out.println("    Error! Empty list!");
			line();
			return;
		}
		for (User u : list) {
			if (idToFind == u.id()) {
				int index = list.indexOf(u);
				System.out.println("You are sure about your decision (y/n)?");
				char answer = sc.nextLine().charAt(0);
				switch (answer) {
				case 'y':
					System.out.println("Deleting user...");
					list.remove(index);
					break;
				case 'n':
					line();
					System.out.println("Ok, Goodbye!");
					line();
					break;
				default:
					line();
					System.out.println("Invalid input, goodbye!");
					line();
					break;
				}
			}
		}
	}
}
