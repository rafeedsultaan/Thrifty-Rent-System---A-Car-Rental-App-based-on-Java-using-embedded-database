/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;

/*
 * DatabaseException is a custom exception class and it is thrown when
 * a database operation can not be performed. 
 */
@SuppressWarnings("serial")
public class DatabaseException extends Exception{
	/*
	 * Constructor for the database exception
	 */
	public DatabaseException(String message) {
		super(message);
	}
}
