/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The FileOperations class contains methods to read from and write the text file
 * to the database.
 */
public class FileOperations {
	
	/*
	 * The exportToFile method is used to write the data from the 
	 * ThriftyRentSystem Application to the text file export.txt
	 * 
	 */
	public static void exportToFile(ThriftyRentSystem trs,String path) throws Exception{
		
		
		PrintWriter writer = null;

	
		writer =new PrintWriter(path);
			
		for(int index =0;index<trs.getVehiclesSize();index++) {	
			Vehicle tempVehicle = trs.getVehiclesList().get(index);
			writer.println(tempVehicle.toString());
			for(int recordIndex=tempVehicle.getMostRecentRentedSize()-1;recordIndex>=0;recordIndex--) {
				RentalRecord tempRecord = tempVehicle.getRentalRecordList().get(recordIndex);
				writer.println(tempRecord.toString());
			}
		} 
		writer.close();// flushes the stream.

	}
	
	/*
	 * The importFromFile method is used to read the data from the 
	 *  text file to the ThriftyRentSystem model.
	 * 
	 */
	public static void importFromFile(ThriftyRentSystem trs,String path)
			throws FileNotFoundException{
		ArrayList<String> vehicleRecords = new ArrayList<String>();
		ArrayList<String> rentalRecords = new ArrayList<String>();
		Scanner input = null;
		String line = null;

		trs.getVehiclesList().clear();
		input = new Scanner(new FileInputStream(path));
		while (input.hasNextLine()) {
			line = input.nextLine();			
			String[] tokens =  line.split(":");
			String rentalIdTokens[] = tokens[0].split("_");
		
			// if the the first token of the tokens can
			// be divided into 2 token with a delimiter '_'
			// then it is ,a vehicle record
			if(rentalIdTokens.length ==2) {
				vehicleRecords.add(line);
			}
			//Otherwise, it is a rental record.
			else {
				rentalRecords.add(line);
			}
		}

		//readVehicleRecords method processes the vehicleRecords
		readVehicleRecords(trs,vehicleRecords);
		//readRentalRecords method processes the rentalRecords
		readRentalRecords(trs,rentalRecords);
		input.close();
	}
	
	
	
	/*
	 * The readVehicleRecords method is a helper method
	 * that processes vehicleRecords
	 * created inside the importFromFile into the ThriftyRentSytem
	 * model.
	 * 
	 */
	private static void readVehicleRecords(ThriftyRentSystem trs,
			ArrayList<String> vehicleRecords) {
		for(int i=0;i<vehicleRecords.size();i++) {
			String line = vehicleRecords.get(i);
			String[] tokens =  line.split(":");

			String inputId = tokens[0];
			String inputYearManufactured= tokens[1];
			String inputMake=tokens[2];
			String inputModel=tokens[3];
			String inputNumberOfSeats=tokens[4];
			String inputStatus= tokens[5];

			//Van records have 8 tokens
			//Car records have 7 tokens
			if(tokens.length==8) {
				String inputLastMaintenanceDate=tokens[6];
				String[] dateTokens = inputLastMaintenanceDate.split("/");
	            DateTime lastMaintenanceDate = new DateTime(Integer.parseInt(dateTokens[0]),
						Integer.parseInt(dateTokens[1]),Integer.parseInt(dateTokens[2]));
	            Van v = new Van(inputId,Integer.parseInt(inputYearManufactured),inputMake,inputModel,
	            		Integer.parseInt(inputNumberOfSeats),"Van",inputStatus,lastMaintenanceDate);
	            String imageName = tokens[7];
	            v.setImageName(imageName);
				trs.getVehiclesList().add(v);
			}
			else {
				String imageName = tokens[6];
				Car c = new Car(inputId,Integer.parseInt(inputYearManufactured),inputMake,
						inputModel,Integer.parseInt(inputNumberOfSeats),"Car",inputStatus);
				c.setImageName(imageName);
				trs.getVehiclesList().add(c);
			}
		}
	}
	
	
	

	/*
	 * The readRentalRecords method is a helper method
	 * that processes rentalRecords created inside the 
	 * importFromFile into the ThriftyRentSytem
	 * model.
	 * 
	 */
	private static void readRentalRecords(ThriftyRentSystem model,
			ArrayList<String> rentalRecords) {
		for(int i =rentalRecords.size()-1;i>=0;i--){
			String line = rentalRecords.get(i);
			String[] tokens =  line.split(":");
			String rentalIdTokens[] = tokens[0].split("_");
			String vehicleId = rentalIdTokens[0]+"_"+ rentalIdTokens[1];
			String customerId = rentalIdTokens[2];
			String rentDateString = tokens[1]; 
			String estimatedReturnDateString = tokens[2];
			String actualReturnDateString = tokens[3];
			String rentFeeString = tokens[4];
			String lateFeeString = tokens[5];
			String dateTokens[]= rentDateString.split("/");
			DateTime rentDate = new DateTime(Integer.parseInt(dateTokens[0]),
					Integer.parseInt(dateTokens[1]),Integer.parseInt(dateTokens[2]));
			dateTokens= estimatedReturnDateString.split("/");
			DateTime estimatedReturnDate = new DateTime(Integer.parseInt(dateTokens[0]),
					Integer.parseInt(dateTokens[1]),Integer.parseInt(dateTokens[2]));
			DateTime actualReturnDate;
			Double rentFee;
			Double lateFee;
			// if the actual return date is none in the file, then initialize 
			// actualReturnDate,rentFee and lateFee with appropriate values
			if(actualReturnDateString.equals("none")) {
				actualReturnDate= null;
				rentFee=0.0;
				lateFee = 0.0;	
			}
			else {
				dateTokens= estimatedReturnDateString.split("/");
				actualReturnDate = new DateTime(Integer.parseInt(dateTokens[0]),
						Integer.parseInt(dateTokens[1]),Integer.parseInt(dateTokens[2]));
				rentFee = Double.parseDouble(rentFeeString);
				lateFee = Double.parseDouble(lateFeeString);
			}		
			int vehicleKey = model.searchIndex(vehicleId);
			RentalRecord r1 = new RentalRecord(vehicleId, customerId, rentDate, 
					estimatedReturnDate,actualReturnDate,rentFee,lateFee);
			model.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
			
		}
	}
	
}
