/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Car;
import model.DateTime;
import model.RentalRecord;
import model.ThriftyRentSystem;
import model.Van;


/*
 * The SelectQuery class  is used to read records from the Vehicle Table and Rental Record
 * Table in the database.
 */
public class SelectQuery {



	/*
	 * The readVehiclesFromDatabase method  is used to read records from the 
	 * Vehicle Table in the database.It throws DatabaseException if the read operation
	 * is not successful
	 */
	public static void readVehiclesFromDatabase(ThriftyRentSystem trs) throws DatabaseException{
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "VEHICLE";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
				
					String id = resultSet.getString("id");
					String year = resultSet.getString("year");
					String make = resultSet.getString("make");
					String model = resultSet.getString("model");
					String numberOfSeats = resultSet.getString("numberofseats");
					String type = resultSet.getString("type");
					String status = resultSet.getString("status");
					String inputLastMaintenanceDate = resultSet.getString("lastMaintenanceDate");
					String imageName = resultSet.getString("imagename");
					
					
					if(type.equals("Van")) {
							String[] dateTokens = inputLastMaintenanceDate.split("/");
							int tempDay = Integer.parseInt(dateTokens[0]);
				            int tempMonth = Integer.parseInt(dateTokens[1]);
				            int tempYear = Integer.parseInt(dateTokens[2]);
				            DateTime lastMaintenanceDate = new DateTime(tempDay,tempMonth,tempYear);
							Van v = new Van(id,Integer.parseInt(year),make,model,Integer.parseInt(numberOfSeats),type,status,lastMaintenanceDate);
							v.setImageName(imageName);
							trs.getVehiclesList().add(v);
					}
					else {
						Car c = new Car(id,Integer.parseInt(year),make,model,Integer.parseInt(numberOfSeats),type,status);
						c.setImageName(imageName);
						trs.getVehiclesList().add(c);
					}
				}
			} catch (SQLException e) {
				throw new DatabaseException("Error: Could not read from Database.");
			}

		} catch (Exception e) {
			throw new DatabaseException("Error: Could not read from Database.");
		}
	}


	
	
	/*
	 * The readRentalRecordsFromDatabase method  is used to read records from the 
	 * Vehicle Table in the database.It throws DatabaseException if the read operation
	 * is not successful
	 */
	public static void readRentalRecordsFromDatabase(ThriftyRentSystem trs) throws DatabaseException{
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RENTALRECORD";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {					
					String id = resultSet.getString("id");
					String rentDateString = resultSet.getString("rentdate");
					String estimatedReturnDateString = resultSet.getString("estimatedreturndate");
					String actualReturnDateString = resultSet.getString("actualreturndate");
					String rentFeeString = resultSet.getString("rentalfee");
					String lateFeeString = resultSet.getString("latefee");
					String rentalIdTokens[] = id.split("_");	
					String vehicleId = rentalIdTokens[0]+"_"+ rentalIdTokens[1];
					String customerId = rentalIdTokens[2];
		
					String dateTokens[]= rentDateString.split("/");
					int tempDay = Integer.parseInt(dateTokens[0]);
					int tempMonth = Integer.parseInt(dateTokens[1]);
					int tempYear = Integer.parseInt(dateTokens[2]);
					DateTime rentDate = new DateTime(tempDay,tempMonth,tempYear);
					dateTokens= estimatedReturnDateString.split("/");
					tempDay = Integer.parseInt(dateTokens[0]);
					tempMonth = Integer.parseInt(dateTokens[1]);
					tempYear = Integer.parseInt(dateTokens[2]);
					DateTime estimatedReturnDate = new DateTime(tempDay,tempMonth,tempYear);
					DateTime actualReturnDate;
					Double rentFee;
					Double lateFee;
					if(actualReturnDateString.equals("none")) {
						actualReturnDate= null;
						rentFee=0.0;
						lateFee = 0.0;
						
					}
					else {
						dateTokens= estimatedReturnDateString.split("/");
						tempDay = Integer.parseInt(dateTokens[0]);
						tempMonth = Integer.parseInt(dateTokens[1]);
						tempYear = Integer.parseInt(dateTokens[2]);
						actualReturnDate = new DateTime(tempDay,tempMonth,tempYear);
						rentFee = Double.parseDouble(rentFeeString);
						lateFee = Double.parseDouble(lateFeeString);
						
					}
						
					int vehicleKey = trs.searchIndex(vehicleId);
					RentalRecord r1 = new RentalRecord(vehicleId, customerId, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
					trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);; 
					
				}
			} catch (SQLException e) {
				throw new DatabaseException("Error: Could not read from Database.");
			}

		} catch (Exception e) {
			throw new DatabaseException("Error: Could not read from Database.");
		}
	}
	
	
	
}
