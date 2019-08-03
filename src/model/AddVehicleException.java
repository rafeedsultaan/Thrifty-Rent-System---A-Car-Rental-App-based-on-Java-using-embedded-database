/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;

/*
 *	AddVehicleException is a custom exception class and it is thrown when
 * a vehicle can not be added to the database. 
 */
@SuppressWarnings("serial")
public class AddVehicleException extends Exception{
	/*
	 * Constructor of the AddVehicleException
	 */
	public AddVehicleException(String errorMessage) {
		super(errorMessage);
	}
}
