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
 * The InsertRow class  is used to insert records to the Vehicle Table and Rental Record
 * Table in the database.
 */
public class InsertRow {

	/*
	 * The insertRowVehicleTable method  is used to insert records to the Vehicle Table and Rental Record
	 * Table from the database.
	 */
	
	public static void insertRowVehicleTable(String id,String year,String make,String model,
			String numberOfSeats, String type, String status,
			String lastMaintenanceDate,String imageName) throws DatabaseException{
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLE";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + TABLE_NAME 
					+" VALUES ("
					+"'"+id+"'"+","
					+"'"+year+"'"+","
					+"'"+make+"'"+","
					+"'"+model+"'"+","
					+"'"+numberOfSeats+"'"+","
					+"'"+type+"'"+","
					+"'"+status+"'"+","
					+"'"+lastMaintenanceDate+"'"+","
					+"'"+imageName+"'"+")";
			
			stmt.executeUpdate(query);
			
			con.commit();
			


		} catch (Exception e) {

			throw new DatabaseException("Error: Could not write to Database.");
		}

	}
	
	
	/*
	 * The insertRowRentalRecordTable method  is used to insert records to the Rental Record
	 * Table inside the database.
	 */
	public static void insertRowRentalRecordTable(String id,String rentDate,
			String estimatedReturnDate,String actualReturnDate,String rentalFee,
			String lateFee) throws DatabaseException {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RENTALRECORD";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + TABLE_NAME 
					+" VALUES ("
					+"'"+id+"'"+","
					+"'"+rentDate+"'"+","
					+"'"+estimatedReturnDate+"'"+","
					+"'"+actualReturnDate+"'"+","
					+"'"+rentalFee+"'"+","
					+"'"+lateFee+"'"+")";
			
			stmt.executeUpdate(query);
			
			con.commit();
			

		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
	}
	
}
