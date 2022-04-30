package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String desc;
	private String year;
	private String lang;
	private int rental_dur;
	private double rental_rate;
	private int length;
	private double repl_cost;
	private String rating;
	private String feat;
	private List<Actor> actors;

	public Film() {
	}

	public Film(int id, String title, String desc, int year) {

	}

	public Film(int id, String title, String desc, String year, String lang, int rental_dur, double rental_rate,
			int length, double repl_cost, String rating, String feat) {
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
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {

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
		return "\nTitle: " + title + "\nYear: " + year + "\nRating: " + rating + "\nDescription: " + desc
				+ "\nLanguage: " + lang + "\nCast: " + actors + "\n";
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
