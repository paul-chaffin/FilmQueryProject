# FilmQueryProject

## Description
This program extracts information from a database of films.

## How to use this program
On launch, the user is presented with three options: (1) locate a film by its numerical ID in the database; (2) locate a film by search term (in the title or description); (3) quit the program. For either of the first two options, the program returns the title, description, rating, release year, language, and cast for any film(s) matching the supplied criteria. The program will continue to take user choices until the user quits the program using the third menu option.

##  Technologies Used
* SQL
* Java
* ArrayList
* Custom objects: Actor, Film, DataBaseAccessorObject
* Scanner
* DriverManager / Connection objects
* PreparedStatement objects
* ResultSet objects
* Interface

## Lessons Learned
* Check SQL queries for dangling commas
* When writing SQL queries, just follow the breadcrumbs
* Access your database using the proper object and only that object
* Look at ERDs closely so you don't miss things you might need, like, I don't know, a table called "language"
