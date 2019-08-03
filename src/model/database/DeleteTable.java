/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;
import java.sql.Connection;
import java.sql.Statement;

/*
 * The DeleteTable class  is used to delete the Vehicle Table and Rental Record
 * Table from the database.
 */
public class DeleteTable {
	
	/*
	 * The deleteVehicleTable method is used to delete the Vehicle
	 * Table from the database.
	 */
	public static void deleteVehicleTable() throws DatabaseException {
		
		final String DB_NAME = "testDB";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("DROP TABLE vehicle");
			

			if(result!=0){
				throw new Exception();
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
	}
	
	/*
	 * The deleteRentalRecordTable method is used to delete the Rental Record
	 * Table from the database.
	 */
	public static void deleteRentalRecordTable() throws DatabaseException {
		
		final String DB_NAME = "testDB";
	
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("DROP TABLE rentalrecord");
			

			if(result!=0) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
	}
	
	
	
	
}
