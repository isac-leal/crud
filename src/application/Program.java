package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.User;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<User> listUsers = new ArrayList<>();

		int option = menu();
		cleanConsole();
		createUser(option, sdf, listUsers);

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

	public static int menu() {
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
		return option;
	}

	public static void createUser(int option, SimpleDateFormat sdf, List<User> listUsers) throws ParseException {
		Scanner sc = new Scanner(System.in);
		switch (option) {
		case 1:
			line();
			System.out.println("     NEW USER DATA");
			line();
			System.out.print("Enter the user name: ");
			String name = sc.nextLine();
			System.out.print("Enter the user email: ");
			String email = sc.nextLine();
			System.out.print("Enter the user birth age date: ");
			Date date = sdf.parse(sc.nextLine());
			System.out.print("Do you want turn back to the menu (y/n)? ");
			char answer = sc.nextLine().charAt(0);
			if (answer == 'y') {
				menu();
				createUser(option, sdf, listUsers);
			} else {
				line();
				System.out.println("	Goodbye!");
				line();
			}
			listUsers.add(new User(name, email, date));
			break;
		case 2:
			if (listUsers.isEmpty()) {
				line();
				System.out.println("    Error! Empty list!");
				line();
				break;
			} else {
				System.out.print("Enter the user name: ");
				String findName = sc.nextLine();

				// CHECAR SE O NOME ESTÁ NA LISTA
				for (User u : listUsers) {
					if (findName == u.getName()) { // PROBLEMA: CHECA SOMENTE A PRIMEIRA OCORRÊNCIA DO NOME
						System.out.println(u);
						break;
					}
				}
			}
			break;
		case 3:
			if (listUsers.isEmpty()) {
				line();
				System.out.println("    Error! Empty list!");
				line();
				break;
			} else {
				System.out.print("Enter the user id (1000 + user's year of birth): ");
				int id = sc.nextInt();
				sc.nextLine();

				for (User u : listUsers) {
					if (id == u.id()) {
						line();
						System.out.println("         UPDATE USER DATA:");
						line();
						System.out.println("Select the data you wish to update:");
						System.out.println("[1] - Name");
						System.out.println("[2] - E-mail");
						System.out.println("[3] - Birth Date");
						System.out.println("[0] - EXIT");
						System.out.print("Enter: ");
						option = sc.nextInt();
						sc.nextLine();

						switch (option) {
						case 1:
							System.out.print("Enter the user's new name: ");
							name = sc.nextLine();
							u.setName(name);
							break;
						case 2:
							System.out.print("Enter the user's new email: ");
							email = sc.nextLine();
							u.setEmail(email);
							break;
						case 3:
							System.out.println("Enter the user's new date of birth");
							date = sdf.parse(sc.nextLine());
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
				break;
			}

		case 4:
			if (listUsers.isEmpty()) {
				line();
				System.out.println("    Error! Empty list!");
				line();
				break;
			} else {
				System.out.print("Enter the user id (1000 + user's year of birth): ");
				int id = sc.nextInt();

				for (User u : listUsers) {
					if (id == u.id()) {
						line();
						char decision;
						do {
							System.out.print("Are you sure about your decision (y/n)? ");
							decision = sc.nextLine().charAt(0);
						} while (decision != 'y' || decision != 'n');

						if (decision == 'y') {
							System.out.println("Deleting the user...");
						}
					}
				}
				break;
			}
		case 0:
			line();
			System.out.println("	Goodbye!");
			line();
			break;
		}
	}
}
