package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.User;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		List<User> listUsers = new ArrayList<>();

		menu(listUsers, sdf, sc);

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

	public static void menu(List<User> listUsers, SimpleDateFormat sdf, Scanner sc) {

		int option;
		boolean validInput;
		do {
			validInput = true;
			try {
				// exibir o menu e ler opções
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

					if (option < 0 || option > 4) {
						System.out.println("Invalid option!");
					}
				} while (option < 0 || option > 4);
			} catch (InputMismatchException e) {
				// informa o erro e atribui -1 ao option para repetir o menu
				cleanConsole();
				line();
				System.out.println("Error: Invalid input!");
				option = -1;
				validInput = false;
				line();
			}
			sc.nextLine();

			switch (option) {
			case 1:
				cleanConsole();
				createUser(listUsers, sdf, sc);
				break;
			case 2:
				if (listUsers.isEmpty()) {
					line();
					System.out.println("    Error! Empty list!");
					line();
					break;
				}
				System.out.print("Enter the user id: ");
				int idToFind = sc.nextInt();
				sc.nextLine();
				showUserData(listUsers, idToFind);
				break;
			case 3:
				if (listUsers.isEmpty()) {
					line();
					System.out.println("    Error! Empty list!");
					line();
					break;
				}
				System.out.print("Enter the user id: ");
				idToFind = sc.nextInt();
				sc.nextLine();
				updateUserData(listUsers, idToFind, sdf, sc);
				break;
			case 4:
				if (listUsers.isEmpty()) {
					line();
					System.out.println("    Error! Empty list!");
					line();
					break;
				}
				System.out.print("Enter the user id: ");
				idToFind = sc.nextInt();
				sc.nextLine();
				deleteUser(listUsers, idToFind, sc);
				break;
			case 0:
				cleanConsole();
				line();
				System.out.println("	Goodbye!");
				line();
				break;
			}
		} while (!validInput || (option != 0 && turnBack(sc)));
	}

	public static void createUser(List<User> listUsers, SimpleDateFormat sdf, Scanner sc) {

		boolean validDate = false;

		line();
		System.out.println("     NEW USER DATA");
		line();
		System.out.print("Enter the user name: ");
		String name = sc.nextLine();
		System.out.print("Enter the user email: ");
		String email = sc.nextLine();
		do {
			try {
				System.out.print("Enter the user birth age date (dd/MM/yyyy): ");
				Date date = sdf.parse(sc.nextLine());
				validDate = true;
				listUsers.add(new User(name, email, date));
			} catch (ParseException e) {
				System.out.println("Error! Invalid date input");
			}
		} while (!validDate);

	}

	public static void showUserData(List<User> listUsers, int idToFind) {

		User user = findUserById(idToFind, listUsers);

		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("ID not found!");
		}
	}

	public static void updateUserData(List<User> listUsers, int idToFind, SimpleDateFormat sdf, Scanner sc) {

		User u = findUserById(idToFind, listUsers);

		if (u != null) {
			int option = -1;
			do {
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

				if (option < 0 || option > 3) {
					System.out.println("Invalid option!");
				}

			} while (option < 0 || option > 3);

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
				boolean validDate = false;
				do {
					try {
						System.out.println("Enter the user's new date of birth (dd/MM/yyyy):");
						Date date = sdf.parse(sc.nextLine());
						validDate = true;
						u.setBirthDate(date);
					} catch (ParseException e) {
						System.out.println("Error! Invalid date input");
					}

				} while (!validDate);
				break;
			case 0:
				cleanConsole();
				line();
				System.out.println("	Goodbye!");
				line();
				break;
			}
		} else {
			System.out.println("ID not found to update data!");
		}

	}

	public static void deleteUser(List<User> listUsers, int idToFind, Scanner sc) {

		User u = findUserById(idToFind, listUsers);

		if (u != null) {
			System.out.print("You are sure about your decision (y/n)? ");
			char answer = sc.nextLine().charAt(0);
			answer = Character.toLowerCase(answer);
			switch (answer) {
			case 'y':
				System.out.println("Deleting user...");
				listUsers.remove(u);
				break;
			case 'n':
				cleanConsole();
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
		} else {
			System.out.println("ID not found to delete!");
		}

	}

	public static boolean turnBack(Scanner sc) {
		System.out.print("Do you want turn back to the menu (y/n)? ");
		char answer = sc.nextLine().charAt(0);
		answer = Character.toLowerCase(answer);
		return answer == 'y';
	}

	public static User findUserById(int id, List<User> listUsers) {

		for (User u : listUsers) {
			if (u.getId().equals(id)) {
				return u;
			}
		}

		return null;
	}
}
