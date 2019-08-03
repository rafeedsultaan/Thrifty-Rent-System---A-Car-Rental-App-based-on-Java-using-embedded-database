/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * The class  CreateTable is used to create Vehicle and Rental Record
 * table.
 */
public class CreateTable {
	

	/*
	 * The class createVehicleTable() is used to create Vehicle Table in the database
	 * table.It throws exception DatabaseException if the table can not be created
	 */
	public static void createVehicleTable() throws SQLException, DatabaseException {
		
		
		final String DB_NAME = "testDB";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE vehicle ("
										+ "id VARCHAR(20) NOT NULL,"
										+ "year VARCHAR(20) NOT NULL," 
										+ "make VARCHAR(20) NOT NULL,"
										+ "model VARCHAR(20) NOT NULL,"
										+ "numberofseats VARCHAR(20) NOT NULL,"
										+ "type VARCHAR(20) NOT NULL,"
										+ "status VARCHAR(30) NOT NULL,"
										+ "lastmaintenancedate VARCHAR(20) NOT NULL,"
										+ "imagename VARCHAR(20),"
										+ "PRIMARY KEY (id))");

			
			if(result!=0){
				throw new Exception();
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
	}
	
	
	
	/*
	 * The class createRentalRecordTable() is used to create Rental Record
	 *  Table in the database. It throws exception DatabaseException if the 
	 *  table can not be created
	 */
	public static void createRentalRecordTable() throws SQLException, DatabaseException {
		
		
		final String DB_NAME = "testDB";
		
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE rentalrecord ("
										+ "id VARCHAR(50) NOT NULL,"
										+ "rentdate VARCHAR(20) NOT NULL,"
										+ "estimatedreturndate VARCHAR(20) NOT NULL,"
										+ "actualreturndate VARCHAR(20) ,"
										+ "rentalfee VARCHAR(20) ,"
										+ "latefee VARCHAR(20) ,"
										+ "PRIMARY KEY (id))");

			if(result!=0){
				throw new Exception();
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: Could not write to Database.");
		}
	}
	
	
}
