/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model.database;

import model.RentalRecord;
import model.ThriftyRentSystem;
import model.Van;
import model.Vehicle;

/*
 * The DatabaseOperation class contains methods to read from and write
 * to the database.
 */
public class DatabaseOperations {
	
	/*
	 * The writeToDatabase method is used to write the data from the 
	 * ThriftyRentSystem Application to the database.
	 * 
	 */
	public static void writeToDatabase(ThriftyRentSystem trs) throws Exception{
	
		if(CheckTableExist.checkVehicleTableExist()) {
			DeleteTable.deleteVehicleTable();
		}
		if(CheckTableExist.checkRentalRecordTableExist()) {
			DeleteTable.deleteRentalRecordTable();
		}
		CreateTable.createVehicleTable();
		CreateTable.createRentalRecordTable();
		
		for(int index = 0;index<trs.getVehiclesSize();index++) {
			Vehicle vehicle = trs.getVehiclesList().get(index);
			Integer year = vehicle.getYearManufactured();
			Integer numberOfSeats = vehicle.getNumberOfSeats();
			
			if(vehicle.getType().equals("Van")) {
				InsertRow.insertRowVehicleTable(vehicle.getId(),year.toString(),vehicle.getMake(),vehicle.getModel()
						,numberOfSeats.toString(),vehicle.getType(),vehicle.getStatus(),
						((Van)vehicle).getLastMaintenanceDate().toString(),vehicle.getImageName());
			}
			else {
				InsertRow.insertRowVehicleTable(vehicle.getId(),year.toString(),vehicle.getMake(),
						vehicle.getModel(),numberOfSeats.toString(),vehicle.getType(),vehicle.getStatus(),
						"n/a",vehicle.getImageName());
			}
			for(int j=0;j<vehicle.getMostRecentRentedSize();j++) {
				RentalRecord record = vehicle.getRentalRecordList().get(j);
				if(record.getActualReturnDate()==null) {
					InsertRow.insertRowRentalRecordTable(record.getId(),record.getRentDate().toString(),
							record.getEstimatedReturnDate().toString(),"none","none","none");
				}
				else {
					Double rf = record.getRentalFee();
					Double lf = record.getLateFee();
					InsertRow.insertRowRentalRecordTable(record.getId(),record.getRentDate().toString(),
							record.getEstimatedReturnDate().toString(),record.getActualReturnDate().toString(),
							rf.toString(),lf.toString());
				}
				
			}
		}
	}
	

	/*
	 * The readFromDatabase method is used to read the data from the 
	 *  database to the ThriftyRentSystem.
	 * 
	 */
	public static void readFromDatabase(ThriftyRentSystem trs) throws Exception {
		// Checks if the vehicle table exists before reading from the database
		if(CheckTableExist.checkVehicleTableExist()) {
			SelectQuery.readVehiclesFromDatabase(trs);
		}
		// Checks if the rental record table exists before reading from the database
		if(CheckTableExist.checkRentalRecordTableExist()) {
			SelectQuery.readRentalRecordsFromDatabase(trs);
		}
	}
}
