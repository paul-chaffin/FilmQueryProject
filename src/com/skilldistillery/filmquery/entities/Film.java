package com.skilldistillery.filmquery.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String desc;
	private int year;
	private int lang;
	private int rental_dur;
	private double rental_rate;
	private int length;
	private double repl_cost;
	private String rating;
	private String feat;
	private List<Actor> actors;
	
	public Film() {}
	
	public Film(int id, String title, String desc, int year) {
		
	}

	public Film(int id, String title, String desc, int year, int lang, int rental_dur, double rental_rate, int length,
			double repl_cost, String rating, String feat) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.year = year;
		this.lang = lang;
		this.rental_dur = rental_dur;
		this.rental_rate = rental_rate;
		this.length = length;
		this.repl_cost = repl_cost;
		this.rating = rating;
		this.feat = feat;
		setActors();
	}
	
	public List<Actor> setActors() {
		actors = new ArrayList<Actor>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, first_name, last_name, ";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.id);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				if (actorResult.next()) {
					actor = new Actor(); // Create the object
					// Here is our mapping of query columns to our object fields:
					actor.setActor_id(actorResult.getInt(1));
					actor.setActor_f_name(actorResult.getString(2));
					actor.setActor_l_name(actorResult.getString(3));
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getLang() {
		return lang;
	}

	public void setLang(int lang) {
		this.lang = lang;
	}

	public int getRental_dur() {
		return rental_dur;
	}

	public void setRental_dur(int rental_dur) {
		this.rental_dur = rental_dur;
	}

	public double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getRepl_cost() {
		return repl_cost;
	}

	public void setRepl_cost(double repl_cost) {
		this.repl_cost = repl_cost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeat() {
		return feat;
	}

	public void setFeat(String feat) {
		this.feat = feat;
	}

	@Override
	public String toString() {
		return "\nTitle: " + title + "\nYear: " + year + "\nRating: " + rating + "\nDescription: " + desc + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, feat, id, lang, length, rating, rental_dur, rental_rate, repl_cost, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(feat, other.feat) && id == other.id
				&& lang == other.lang && length == other.length && Objects.equals(rating, other.rating)
				&& rental_dur == other.rental_dur
				&& Double.doubleToLongBits(rental_rate) == Double.doubleToLongBits(other.rental_rate)
				&& Double.doubleToLongBits(repl_cost) == Double.doubleToLongBits(other.repl_cost)
				&& Objects.equals(title, other.title) && year == other.year;
	};
	
	
	
	
}
