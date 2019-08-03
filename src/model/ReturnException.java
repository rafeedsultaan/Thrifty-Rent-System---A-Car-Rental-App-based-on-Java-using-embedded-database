/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;



/*
 *	ReturnException is a custom exception class and it is thrown when
 * a vehicle can not be returned. 
 */
@SuppressWarnings("serial")
public class ReturnException extends Exception {
	/*
	 * Constructor of the Return Exception
	 */
	public ReturnException(String errMsg) {
	      super(errMsg); 
	  } 
}