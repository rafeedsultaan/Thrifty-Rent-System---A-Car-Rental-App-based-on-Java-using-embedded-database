/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * The class CheckTableExist checks if the vehicle table
 * and rental records table exists in the database
 */
public class CheckTableExist {

	/*
	 * The method checkVehicleTableExist()checks if the vehicle table
	 * exists in the database. It throws exception if the 
	 * vehicle table does not exist.
	 */
	public static boolean checkVehicleTableExist() throws SQLException, DatabaseException {
		final String DB_NAME = "testDB";

		// IMPORTANT: table name is uppercase
		final String TABLE_NAME = "VEHICLE";
		
		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(tables != null) {
				if (tables.next()) {
					return true;
				}
			}
				
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
		return false;
	}

	/*
	 * The method checkRentalRecordTableExist()checks if the rental record table
	 * exists in the database. It throws exception if the 
	 * rental record table does not exist in the database.
	 */
	public static boolean checkRentalRecordTableExist() throws SQLException, DatabaseException{
		final String DB_NAME = "testDB";

		// IMPORTANT: table name is uppercase
		final String TABLE_NAME = "RENTALRECORD";
		
		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(tables != null) {
				if (tables.next()) {
					return true;
				}	
				tables.close();
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
		return false;
	}
}
