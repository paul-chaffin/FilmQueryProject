package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		JOHN: while (true) {
			System.out.println("Please make a selection from the options below:");
			System.out.println("1. Find a film by its ID");
			System.out.println("2. Find a film by keyword");
			System.out.println("3. Quit");
			System.out.print("Selection > ");
			int choice = input.nextInt();
			int filmid;
			String searchWord;

			switch (choice) {
			case 1:
				input.nextLine();
				System.out.print("Enter the film ID > ");
				filmid = input.nextInt();
				if (db.findFilmById(filmid) == null) {
					System.out.println("Film not found, sorry!");
				} else {
					System.out.println(db.findFilmById(filmid));
				}
				input.nextLine();
				IDSEARCH: while (true) {
					System.out.println("\nFrom here you can:");
					System.out.println("1. Show all details for this film");
					System.out.println("2. Return to the main menu");
					System.out.print("Selection > ");
					switch (input.nextInt()) {
					case 1:
						System.out.println(db.findFilmById(filmid).toStringAll());
						break IDSEARCH;
					case 2:
						break IDSEARCH;
					default:
						System.out.println("\t**Please make a valid selection.**\n");
					}
				}
				break;

			case 2:
				input.nextLine();
				System.out.print("Enter the search word: ");
				searchWord = input.next();
				if (db.findFilmByTerm(searchWord).isEmpty()) {
					System.out.println("\n\tKeyword not found in any films, sorry!\n");
				} else {
					System.out.println(db.findFilmByTerm(searchWord));
					WORDSEARCH: while (true) {
						System.out.println("\nFrom here you can:");
						System.out.println("1. Show all details for one of these films");
						System.out.println("2. Return to the main menu");
						System.out.print("Selection > ");
						switch (input.nextInt()) {
						case 1:
							input.nextLine();
							System.out.print("Enter the film ID: ");
							filmid = input.nextInt();
							if (db.findFilmById(filmid) == null) {
								System.out.println("Film not found, sorry!");
							} else {
								System.out.println(db.findFilmById(filmid).toStringAll());
								input.nextLine();
									}
							continue WORDSEARCH;
						case 2:
							break WORDSEARCH;
						default:
							System.out.println("\t**Please make a valid selection.**\n");
						}
					}
				}
				break;
			case 3:
				System.out.println("Goodbye!");
				break JOHN;
			default:
				System.out.println("\t**Please make a valid selection.**\n");
				break;

			}

}}

}
