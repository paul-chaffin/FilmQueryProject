# FilmQueryProject

## Description
This program extracts information from a database of films.

## How to use this program
On launch, the user is presented with three options: (1) locate a film by its numerical ID in the database; (2) locate a film by search term (in the title or description); (3) quit the program. For either of the first two options, the program returns the title, description, rating, release year, language, and cast for any film(s) matching the supplied criteria; the user is then presented with a submenu to print all remaining details for the returned film(s). The program will continue to prompt the user for menu selections until the user quits the program using the third menu option.

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
* Check SQL queries / prepared statements for dangling commas and missing whitespace
* When writing SQL queries, follow the breadcrumbs
* Access your database using one proper object and only that object
* Look at ERDs closely so you don't miss things you might need
