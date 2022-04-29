package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	public DatabaseAccessorObject() {
		super();
//		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	}

	public List<Actor> setActors() {
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

	public String getLanguage(int filmId) {
		String user = "student";
		String pass = "student";
		String lang = "";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT name FROM language JOIN film ON language.id = film.language_id ";
			sql += "WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				lang = filmResult.getString(1);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lang;
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT title, description, release_year, rating FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setTitle(filmResult.getString(1));
				film.setDesc(filmResult.getString(2));
				film.setYear(filmResult.getString(3));
				film.setRating(filmResult.getString(4));
				film.setLang(this.getLanguage(filmId));
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Film> findFilmByTerm(String searchWord) throws SQLException {
		List<Film> films = new ArrayList<Film>();
		Film film = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT title, description, release_year, rating, id FROM film";
			sql += " WHERE title LIKE ? OR description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + searchWord + "%");
			stmt.setString(2, "%" + searchWord + "%");
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				film = new Film();
				film.setTitle(filmResult.getString(1));
				film.setDesc(filmResult.getString(2));
				film.setYear(filmResult.getString(3));
				film.setRating(filmResult.getString(4));
				film.setLang(this.getLanguage(filmResult.getInt(5)));
				films.add(film);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(url, user, pass);

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setActor_id(actorResult.getInt(1));
			actor.setActor_f_name(actorResult.getString(2));
			actor.setActor_l_name(actorResult.getString(3));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		if (actor.equals(null)) {
			System.out.println("Actor not found");
		}
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT first_name, last_name ";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setActor_f_name(actorResult.getString(1));
				actor.setActor_l_name(actorResult.getString(2));
				actors.add(actor);
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (actors.isEmpty()) {
			System.out.println("Film not found");
		}
		return actors;
	}

}
