/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;


/*
 *	InvalidDateFormatExceptio is a custom exception class and it is thrown when
 *  when input date does not have  a valid date format. The valid date format is
 *   DD/MM/YYYY
 */
@SuppressWarnings("serial")
public class InvalidDateFormatException extends Exception{
	/*
	 * Constructor of the InvalidDateFormatException
	 */
	public InvalidDateFormatException(String message) {
		super(message);
	}
}
