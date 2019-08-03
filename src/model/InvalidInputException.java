/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;

/*
 *	InvalidInputException class is a custom exception and it is thrown when
 * user's input is not valid. 
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception{
	/*
	 * Constructor of the InvalidInputException
	 */
	public InvalidInputException(String message) {
		super(message);
	}
}
