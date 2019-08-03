/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
 * The class ConnectionTest contains a method that creates a connection with
 * the database.
 */
public class ConnectionTest {
	
	

	/*
	 * The method getConnection establishes a connection with the database.
	 */
	public static Connection getConnection(String dbName)
   				 throws SQLException, ClassNotFoundException {
		   	 //Registering the HSQLDB JDBC driver
		   	 Class.forName("org.hsqldb.jdbc.JDBCDriver");
		   		 
		   	 /* Database files will be created in the "database"
		   	  * folder in the project. If no username or password is
		   	  * specified, the default SA user and an empty password are used */
		   	 Connection con = DriverManager.getConnection
		   			 ("jdbc:hsqldb:file:database/" + dbName, "SA", "");
		   	 return con;
    }
}
