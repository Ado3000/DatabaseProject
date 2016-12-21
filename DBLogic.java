import java.sql.*;
import java.util.*;

/*
 * 
 * CLASS: DBLOGIC
 * AUTHOR: Adnan Alihodzic
 * DESCRIPTION: Performs the operations on the database and the logic between the UI and database
 * 
 * 
 */



public class DBLogic {

	 Connection myConn;
	 ArrayList<String> types = new ArrayList<String>();
	 ArrayList<Integer> genreIds = new ArrayList<Integer>();
	 ArrayList<String> actorNames = new ArrayList<String>();
	 ArrayList<Integer> actorIds = new ArrayList<Integer>();
	 ArrayList<String> directorNames = new ArrayList<String>();
	 ArrayList<Integer> directorIds = new ArrayList<Integer>();
	 ArrayList<String> movieTitles = new ArrayList<String>();
	 ArrayList<Integer> movieYears = new ArrayList<Integer>();
	 ArrayList<String> movieLength = new ArrayList<String>();
	 ArrayList<String> movieDescrip = new ArrayList<String>();
	 ArrayList<String>searchResults = new ArrayList<String>();
	 ArrayList<String>selectionList = new ArrayList<String>();
	 
	
	 public DBLogic() throws SQLException{
		 // Connection statement to the database
		 myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies","root","counterstrike2.");
	 }
	 
	 // Performs the operations for showing all the entries in the actors table
	 public void showActors() throws SQLException{
		 String actorName = null;
		 int actorBirthDate;
		 
		 Statement stmt = myConn.createStatement();
		 String query = "SELECT * FROM actor";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 while(rs.next()){
			 actorBirthDate = rs.getInt("id");
			 actorName = rs.getString("Name");
			 actorNames.add(actorName);
			 actorIds.add(actorBirthDate);
		 }
	 }
	 
	 // Performs the operations for showing all the entries in the directors table
	 public void showDirector() throws SQLException{
		 String directorName = null;
		 int directorBirthDate;
		 
		 Statement stmt = myConn.createStatement();
		 String query = "SELECT * FROM director";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 while(rs.next()){
			 directorBirthDate = rs.getInt("id");
			 directorName = rs.getString("Name");
			 directorNames.add(directorName);
			 directorIds.add(directorBirthDate);
		 }
	 }
	 
	 // Performs the operations for showing all the entries in the Genre table
	 public void showGenre() throws SQLException{
		 String genre = null;
		 int id;
		 
		 Statement stmt = myConn.createStatement();
		 String query = "SELECT * FROM genre";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 while(rs.next()){
			 id = rs.getInt("id");
			 genre = rs.getString("type");
			 types.add(genre);
			 genreIds.add(id);
		 }
		 
	 }
	 
	 // Performs the operations for showing all the entries in the Movies table
	 public void showMovies() throws SQLException{
		 String movieTitle = null;
		 int movieYear;
		 String movieTime = null;
		 String movieComment = null;
		 
		 Statement stmt = myConn.createStatement();
		 String query = "SELECT * FROM movie";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 while(rs.next()){
			 movieTitle = rs.getString("Title");
			 movieYear = rs.getInt("Year");
			 movieTime = rs.getString("RunningTime");
			 movieComment = rs.getString("Description");
			 movieTitles.add(movieTitle);
			 movieYears.add(movieYear);
			 movieLength.add(movieTime);
			 movieDescrip.add(movieComment);
		 }
	 }
	 
	 // Insertion logic for inserting movies into the Movie table
	 public void insertMovie(String movieTitle, int movieYear, String movieTime, String movieComment) throws SQLException{
		 
		 Statement stmt = myConn.createStatement();
		 String query = "INSERT INTO movie (Title, Year, RunningTime, Description) VALUES ('" + movieTitle + "', '" + movieYear +  "' , '" + movieTime + "', '" + movieComment +  "')"; 
		 stmt.executeUpdate(query);
		 
	 }

	 // Deletes movies from the Movies table
	 public void deleteMovie(String movieTitle, int movieYear) throws SQLException{
		 
		 Statement stmt = myConn.createStatement();
		 String query = "DELETE FROM movie WHERE Title = '" + movieTitle + "'AND Year ='" + movieYear +"'";
		 stmt.executeUpdate(query);
	 }
	 
	 // A selection function which shows all the movies a director has either directed
	 // an actor acted in or is included in a genre
	 public void selection(String type, String name) throws SQLException{
		 String result = null;
		 
		 if(type.equals("director")){
		 PreparedStatement stmt = myConn.prepareStatement("SELECT directs.movie_name FROM directs, director, movie WHERE director.Name = ? AND director.id = directs.director_id AND directs.movie_name = movie.Title");
		 stmt.setString(1, name);
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()){
			 result = rs.getString("movie_name");
			 selectionList.add(result);
		 }
	 }
		 else if(type.equals("actor")){
			 PreparedStatement stmt = myConn.prepareStatement("SELECT startsin.movie_name FROM startsin, actor, movie WHERE actor.Name = ? AND actor.id = startsin.actor_id AND startsin.movie_name = movie.Title ");
			 stmt.setString(1, name);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 result = rs.getString("movie_name");
				 selectionList.add(result);
			 }
		 }
		 else if(type.equals("genre")){
			 PreparedStatement stmt = myConn.prepareStatement("SELECT istypeof.movie_namel FROM istypeof, genre WHERE genre.type = ? AND genre.id = istypeof.genre_id");
			 stmt.setString(1, name);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 result = rs.getString("movie_namel");
				 selectionList.add(result);
			 }
			 
		 } 
		 
	}
	 
	 
	 // A function for editing different columns in the different tables
	 public void editData(String tableName, String columnName,String newValue, int id) throws SQLException{
		 
		 if(tableName.equals("director")){
		 PreparedStatement stmt = myConn.prepareStatement("UPDATE director SET Name = ? WHERE id = ?");
		 stmt.setString(1, newValue);
		 stmt.setInt(2, id);
		 stmt.executeUpdate();
		 }
		 else if(tableName.equals("actor")){
			 PreparedStatement stmt = myConn.prepareStatement("UPDATE actor SET Name = ? WHERE id = ?");
			 stmt.setString(1, newValue);
			 stmt.setInt(2, id);
			 stmt.executeUpdate();
		 }
		 else if(tableName.equals("genre")){
			 PreparedStatement stmt = myConn.prepareStatement("UPDATE genre SET type = ? WHERE id = ?");
			 stmt.setString(1, newValue);
			 stmt.setInt(2, id);
			 stmt.executeUpdate();
		 }
	 } 
		 
		 
	 
	 //Searches for all the actors and directors with the methods argument in their name
	 public void searchData(String searchString) throws SQLException{
		 String results = null;
		 PreparedStatement stmt = myConn.prepareStatement("SELECT Name FROM actor WHERE Name LIKE ? UNION SELECT Name FROM director WHERE Name LIKE ? ORDER BY Name ");
		 stmt.setString(1, "%" + searchString + "%");
		 stmt.setString(2, "%" + searchString + "%");
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()){
			 results = rs.getString("Name");
			 searchResults.add(results);
		 }
	 }

	 
}
