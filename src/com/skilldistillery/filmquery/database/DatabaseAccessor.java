package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId) throws SQLException;

	public List<Film> findFilmByTerm(String searchWord) throws SQLException;

	public List<Actor> findActors(int filmId) throws SQLException;
}
