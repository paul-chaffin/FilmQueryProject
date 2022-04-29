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
	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountaind";

	public DatabaseAccessorObject() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {};
	

	
	@Override
	public Film findFilmById(int filmId) throws SQLException{
		
		
		Film film = new Film(film_id, film_title, film_desc, film_year, film_lang,
                rental_dur, rental_rate, film_length, repl_cost, film_rating, spec_feat);
		return film;
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
		
		return actor;
	}
	
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException{
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql = "SELECT id, first_name, last_name, ";
		              sql +=  " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
		               + " WHERE film_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet actorResult = stmt.executeQuery();
		    while (rs.next()) {
		    	if (actorResult.next()) {
					actor = new Actor(); // Create the object
					// Here is our mapping of query columns to our object fields:
					actor.setActor_id(actorResult.getInt(1));
					actor.setActor_f_name(actorResult.getString(2));
					actor.setActor_l_name(actorResult.getString(3));
				}
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
	}

}
