/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;


/*
 *	MaintenanceException is a custom exception and it is thrown when
 * we can not perform maintenance or complete maintenance of a vehicle. 
 */
@SuppressWarnings("serial")
public class MaintenanceException extends Exception  {
	/*
	 * Constructor of the MaintenanceException
	 */
	public MaintenanceException(String errMsg) {
		      super(errMsg); 
		  } 
}

