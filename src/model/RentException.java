/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;



/*
 *	RentException is a custom exception class and it is thrown when
 * a vehicle can not be rented. 
 */
@SuppressWarnings("serial")
public class RentException extends Exception {
	
	/*
	 * Constructor of the RenException
	 */
	public RentException(String errMsg) {
	      super(errMsg); 
	  } 
}
