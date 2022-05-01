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
	}


	public List<Actor> findActors(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT first_name, last_name";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor(); // Create the object
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
				lang = filmResult.getString("name");
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

			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate,"
					      + " length, replacement_cost, rating, special_features FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDesc(filmResult.getString("description"));
				film.setYear(filmResult.getString("release_year"));
				film.setLang_id(filmResult.getInt("language_id"));
				film.setRental_dur(filmResult.getInt("rental_duration"));
				film.setRental_rate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setRepl_cost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setFeat(filmResult.getString("special_features"));
				film.setLang(this.getLanguage(filmId));
				film.setActors(findActors(filmId));
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
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate," 
					     + " length, replacement_cost, rating, special_features FROM film WHERE title LIKE ? OR"
					     + " description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + searchWord + "%");
			stmt.setString(2, "%" + searchWord + "%");
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDesc(filmResult.getString("description"));
				film.setYear(filmResult.getString("release_year"));
				film.setLang_id(filmResult.getInt("language_id"));
				film.setRental_dur(filmResult.getInt("rental_duration"));
				film.setRental_rate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setRepl_cost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setFeat(filmResult.getString("special_features"));
				film.setLang(this.getLanguage(filmResult.getInt("id")));
				film.setActors(findActors(filmResult.getInt("id")));
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
	
	public Film getAll(int filmId) {
		
	}

}
