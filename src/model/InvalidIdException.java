/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;


/*
 *	InvalidIdException is a custom exception class and it is thrown when
 *  when the vehicle id is not a valid id or if the vehicle
 *  already exists in the database
 */
@SuppressWarnings("serial")
public class InvalidIdException extends Exception{	
	/*
	 * Constructor of the InvalidIdException
	 */
	public InvalidIdException(String message) {
		super(message); 
	}

	
}
