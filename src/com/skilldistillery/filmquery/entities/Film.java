package com.skilldistillery.filmquery.entities;

import java.util.Arrays;
import java.util.Objects;

public class Film {
	private int film_id;
	private String film_title;
	private String film_desc;
	private int film_year;
	private int film_lang;
	private int rental_dur;
	private double rental_rate;
	private int film_length;
	private double repl_cost;
	private String film_rating;
	private String spec_feat;
	
	public Film() {};
	
	public Film(int film_id, String film_title, String film_desc, int film_year, int film_lang, int rental_dur,
			double rental_rate, int film_length, double repl_cost, String film_rating, String spec_feat) {
		super();
		this.film_id = film_id;
		this.film_title = film_title;
		this.film_desc = film_desc;
		this.film_year = film_year;
		this.film_lang = film_lang;
		this.rental_dur = rental_dur;
		this.rental_rate = rental_rate;
		this.film_length = film_length;
		this.repl_cost = repl_cost;
		this.film_rating = film_rating;
		this.spec_feat = spec_feat;
	}

	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public String getFilm_title() {
		return film_title;
	}

	public void setFilm_title(String film_title) {
		this.film_title = film_title;
	}

	public String getFilm_desc() {
		return film_desc;
	}

	public void setFilm_desc(String film_desc) {
		this.film_desc = film_desc;
	}

	public int getFilm_year() {
		return film_year;
	}

	public void setFilm_year(int film_year) {
		this.film_year = film_year;
	}

	public int getFilm_lang() {
		return film_lang;
	}

	public void setFilm_lang(int film_lang) {
		this.film_lang = film_lang;
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

	public int getFilm_length() {
		return film_length;
	}

	public void setFilm_length(int film_length) {
		this.film_length = film_length;
	}

	public double getRepl_cost() {
		return repl_cost;
	}

	public void setRepl_cost(double repl_cost) {
		this.repl_cost = repl_cost;
	}

	public String getFilm_rating() {
		return film_rating;
	}

	public void setFilm_rating(String film_rating) {
		this.film_rating = film_rating;
	}

	public String[] getSpec_feat() {
		return spec_feat;
	}

	public void setSpec_feat(String[] spec_feat) {
		this.spec_feat = spec_feat;
	}

	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", film_title=" + film_title + ", film_desc=" + film_desc + ", film_year="
				+ film_year + ", film_lang=" + film_lang + ", rental_dur=" + rental_dur + ", rental_rate=" + rental_rate
				+ ", film_length=" + film_length + ", repl_cost=" + repl_cost + ", film_rating=" + film_rating
				+ ", spec_feat=" + Arrays.toString(spec_feat) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(spec_feat);
		result = prime * result + Objects.hash(film_desc, film_id, film_lang, film_length, film_rating, film_title,
				film_year, rental_dur, rental_rate, repl_cost);
		return result;
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
		return Objects.equals(film_desc, other.film_desc) && film_id == other.film_id && film_lang == other.film_lang
				&& film_length == other.film_length && Objects.equals(film_rating, other.film_rating)
				&& Objects.equals(film_title, other.film_title) && film_year == other.film_year
				&& rental_dur == other.rental_dur
				&& Double.doubleToLongBits(rental_rate) == Double.doubleToLongBits(other.rental_rate)
				&& Double.doubleToLongBits(repl_cost) == Double.doubleToLongBits(other.repl_cost)
				&& Arrays.equals(spec_feat, other.spec_feat);
	}
	
	
}
