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
			System.out.println("What would you like to do??");
			System.out.println("1. Find a film by its ID");
			System.out.println("2. Find a film by keyword");
			System.out.println("3. Quit");
			int choice = input.nextInt();
			int filmid;
			String searchWord;

			switch (choice) {
			case 1:
				input.nextLine();
				System.out.print("Enter the film ID: ");
				filmid = input.nextInt();
				if (db.findFilmById(filmid) == null) {
					System.out.println("Film not found, sorry!");
				} else {
					System.out.println(db.findFilmById(filmid));
				}
				;
				break;
			case 2:
				input.nextLine();
				System.out.print("Enter the search word: ");
				searchWord = input.next();
				if (db.findFilmByTerm(searchWord) == null) {
					System.out.println("Keyword not found in any films, sorry!");
				} else {
					System.out.println(db.findFilmByTerm(searchWord));
				}
				break;
			case 3:
				System.out.println("Goodbye!");
				break JOHN;
			default:
				System.out.println("\t**PLease make a valid selection.**\n");
				break;

			}

		}
	}

}
