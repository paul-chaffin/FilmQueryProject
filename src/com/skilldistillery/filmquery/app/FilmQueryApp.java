package com.skilldistillery.filmquery.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
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

			}

		}
	}

	private List<Actor> setActors() {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT first_name, last_name, ";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.id);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				if (actorResult.next()) {
					actor = new Actor(); // Create the object
					// Here is our mapping of query columns to our object fields:
					actor.setActor_f_name(actorResult.getString(1));
					actor.setActor_l_name(actorResult.getString(2));
					actors.add(actor);
				}
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

}
