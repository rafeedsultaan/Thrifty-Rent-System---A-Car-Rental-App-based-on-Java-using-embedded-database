/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */




/*
The class Cars is implemented for Vehicles who are Cars.
 */

package model;

public class Car extends Vehicle {

    /*
    Constructor for Car
     */
    public Car(String id, int yearManufactured, String make, String model,
    		int numberOfSeats, String type, String status) {
        super(id, yearManufactured, make, model, numberOfSeats, "Car",status);
    }



    /*
     *The method "rent" decides if the Vehicle can be rent.
     *If the method can not rent vehicle, throws RentException.
     */
    public void rent(String customerId, DateTime rentDate, int numOfRentDay)
    		throws RentException {
        DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);

        if (this.getStatus().equals("Rented")) {
            throw new RentException("Error: This vehicle is already being rented.");
        }
        if (this.getStatus().equals("Maintenance")) {
        	throw new RentException("Error: This vehicle is under maintenance."
        			+ " Therefore it can not be rented");
        }


        if (numOfRentDay > 14) {
        	throw new RentException("Error: Maximum Number of Days for Rent is 14 days.");
        }

        if (((rentDate.getNameOfDay().equals("Friday") || 
        		rentDate.getNameOfDay().equals("Saturday")) && numOfRentDay < 3)) {
        	throw new RentException("Error: A minimum of 3 days if the rental day "
        			+ "is Friday or Saturday.");
            
        } else if ((!rentDate.getNameOfDay().equals("Friday") && 
        		!rentDate.getNameOfDay().equals("Saturday")) && numOfRentDay < 1) {
        	throw new RentException("Error: A minimum of 2 days if the rental is"
        			+ " between Sunday and Thursday inclusively.");
        }

        this.setStatus("Rented");
        RentalRecord r1 = new RentalRecord(this.getId(), customerId, rentDate,
        		estimatedReturnDate, null, 0, 0);
        this.addRentalRecord(r1);


        
    }


    /*
     * The method "returnVehicle" decides if the Vehicle can be returned.
     * If the method can not return vehicle, it throws ReturnException.
     */
    public void returnVehicle(DateTime returnDate) throws ReturnException{

    	if (this.getStatus().equals("Available")) {
            throw new ReturnException("Error: This vehicle is not rented.");
        }
        if (this.getStatus().equals("Maintenance")) {
            throw new ReturnException("Error: This vehicle is under maintenance. "
            		+ "Therefore it can not be rented");
        }
        
        double rentalFee = 0, lateFee = 0;
     // Retrieving the mostRecentRentalRecord of a vehicle.
        RentalRecord mostRecentRecord = retrieveMostRecentRentalRecord(); 
        

        if (mostRecentRecord == null) {
            throw new ReturnException("Error: No Records Found");
        }
        int differenceDays = DateTime.diffDays(returnDate, mostRecentRecord.getRentDate());


        if (differenceDays < 0) {
            throw new ReturnException("Error: Return Date Can Not Be Prior to Rent Date");
        }

        int differenceDaysRent = DateTime.diffDays(mostRecentRecord.getEstimatedReturnDate(), 
        		mostRecentRecord.getRentDate());
        int differenceDaysLate = DateTime.diffDays(returnDate, 
        		mostRecentRecord.getEstimatedReturnDate());


        if (this.getNumberOfSeats() == 4) {
            rentalFee = 78 * differenceDaysRent;
            if (differenceDaysLate <= 0) {
                lateFee = 0;
            } else {
                lateFee = differenceDaysLate * 78 * 1.25;
            }
        } else if (this.getNumberOfSeats() == 7) {
            rentalFee = 113 * differenceDaysRent;
            if (differenceDaysLate <= 0) {
                lateFee = 0;
            } else {
                lateFee = differenceDaysLate * 113 * 1.25;
            }
        }

        this.setStatus("Available"); // The status of the car is set to "Available
        this.updateMostRecentRentalRecord(returnDate, rentalFee, lateFee);
    }



    /*
    The method "performMaintenance" decides if we can perform Maintenance on the vehicle.
    If the method can not perform maintenance, it throws MaintenanceException.
   */
    public void performMaintenance() throws MaintenanceException {
        if (this.getStatus().equals("Rented")) {
            throw new MaintenanceException("Error: The vehicle is being rented at the moment");
        } else if (this.getStatus().equals("Maintenance")) {
            throw new MaintenanceException("Error: The vehicle is already under maintenance.");
        }
        this.setStatus("Maintenance"); // The status of the vehicle is set to Under Maintenance.
    }




    /*
    The method "completeMaintenance" decides if we can complete the Maintenance on the vehicle.
   	If the method can not complete maintenance, it throws MaintenanceException.
     */
    public void completeMaintenance(DateTime completionDate) throws MaintenanceException{

        if (this.getStatus().equals("Rented")) {
            throw new MaintenanceException("Error : This vehicle is being rented at the moment");
        } else if (this.getStatus().equals("Available")) {
            throw new MaintenanceException("Error: This vehicle was not under maintenance.");
        }

        this.setStatus("Available"); // The status of the vehicle is set to "Available"        
    }

    
    /*
    The method "toString" creates a string of the car details including the image name
     */
    public String toString() {
    	String details = super.toString()+":"+this.getImageName();
    	return details;
    }
    
}