/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */

/*
The class Van is implemented for Vehicles who are Vans.
 */
package model;

public class Van extends Vehicle{
    private DateTime lastMaintenanceDate;


    /*
        Constructor for Van
    */
    public Van(String id, int yearManufactured, String make, String model, int numberOfSeats,
    		String type, String status, DateTime lastMaintenanceDate) {
        super(id, yearManufactured, make, model, numberOfSeats, type, status);
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.setStatus("Available");
    }

    /*
        Mutator method for Last Maintenance Date
    */
    public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }


    
    /*
        Accessor method for Last Maintenance Date
    */
    public DateTime getLastMaintenanceDate() {
        return this.lastMaintenanceDate;
    }



    /*
     *The method "rent" decides if the Vehicle can be rent.
     *If the method can not rent vehicle,it throws RentException.
     */
    public void rent(String customerId, DateTime rentDate, int numOfRentDay)
    		throws RentException {
        if (!this.getStatus().equals("Available")) {
            throw new RentException("Error: This vehicle is already being rented.");
        }else if (this.getStatus().equals("Maintenance")) {
            throw new RentException("Error: This vehicle is under maintenance."
            		+ " Therefore it can not be rented");
        }

        this.setStatus("Available");
        DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);
        
        DateTime estimatedMaintenanceDate = new DateTime(this.lastMaintenanceDate, 12); 
        // estimated Maintenance date is 12 days later according to the constraint
        int diffDaysMaintenanceRent = DateTime.diffDays(rentDate, this.getLastMaintenanceDate());

        if(diffDaysMaintenanceRent<1){ 
        	throw new RentException("Error: Request Rejected. Rent Date can not "
        			+ "be prior or on the same date as"
        			+ " Last Maintenance Date");
        }
        int diffDaysMaintenanceRentEstimated = DateTime.diffDays(estimatedReturnDate,
        		estimatedMaintenanceDate); 
        // Calculate the interval between the estimated maintenance date and estimatedReturnDate

        if (numOfRentDay < 1) {
            throw new RentException("Error: The rental period must be at least 1 day.");
        }

        if (diffDaysMaintenanceRentEstimated > 0) {   
        	// Assuming return date and estimated maintenance do not overlap
            throw new RentException("Error: Request Rejected. Estimated Maintenance Date:" 
        	+ estimatedMaintenanceDate.toString()+" conflicts with rental period");
        }
     
        RentalRecord r1 =
        		new RentalRecord(this.getId(), customerId, rentDate, 
        				estimatedReturnDate, null, 0, 0);
        this.addRentalRecord(r1);
        this.setStatus("Rented"); // Set the status of the vehicle to Available
    }

    /*
     * The method "returnVehicle" decides if the Vehicle can be returned.
     * If the method can not return vehicle throws ReturnException.
     */

    public void returnVehicle(DateTime returnDate) throws ReturnException {
       
    	if (this.getStatus().equals("Available")) {
    		throw new ReturnException("Error: This vehicle is not rented.");
        }
        if (this.getStatus().equals("Maintenance")) {
            throw new ReturnException("Error: This vehicle is under maintenance."
            		+ " Therefore it can not be rented");
        }
    	
    	
    	RentalRecord mostRecentRecord = retrieveMostRecentRentalRecord();

        if (mostRecentRecord == null) {
            throw new ReturnException("Error: No Records Found");
        }
        int differenceDays = DateTime.diffDays(returnDate, mostRecentRecord.getRentDate());

        if (differenceDays < 0) { //return date can not be prior to rent date
            throw new ReturnException("Error: Return Date Can Not Be Prior to Rent Date");
        }
        if(differenceDays==0){ // Assuming return date and rent date can not overlap
        	throw new ReturnException("Error: Return Date can not be on the same date as Rent Date");
        }

        int differenceDaysRent 
        		= DateTime.diffDays(mostRecentRecord.getEstimatedReturnDate(),
        				mostRecentRecord.getRentDate());
        int differenceDaysLate 
        		= DateTime.diffDays(returnDate, mostRecentRecord.getEstimatedReturnDate());
        double rentalFee = 0, lateFee = 0;

        // Calculation of the Rental Fees and the Late fees
        if (this.getType().equals("Van")) {
            rentalFee = 235 * differenceDaysRent;
            if (differenceDaysLate > 0) {
                lateFee = 299;
            } else {
                lateFee = 0;
            }
        }
        this.setStatus("Available"); // The status of the vehicle is set to "Available"
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
        } else {
            this.setStatus("Maintenance"); // Status of the vehicle set to Maintenance
        }
    }


    /*
   The method "completeMaintenance" decides if we can complete the Maintenance on the vehicle.
  	If the method can not complete maintenance, it throws MaintenanceException.
    */
    public void completeMaintenance(DateTime completionDate) throws MaintenanceException {
        int diffDaysMaintenance = DateTime.diffDays(completionDate, this.getLastMaintenanceDate());

        
        if (diffDaysMaintenance > 12) {
            /*
            Assuming you can not return the vehicle if the 12 day maintenance period is crossed. 
            Since there is a strict restriction that maintenance period must be done 
            no more than every 12 days.
            */    
        	throw new MaintenanceException(
        			"Error: The maintenance of the vehicle must be completed "
        			+ "no more than 12 days after the last maintenance date.");

        }

        if (this.getStatus().equals("Rented")) {
            throw new MaintenanceException("Error : This vehicle is being rented at the moment");
        } else if (this.getStatus().equals("Available")) {
            throw new MaintenanceException("Error: This vehicle was not under maintenance.");
        } else {
            this.setLastMaintenanceDate(completionDate);
            this.setStatus("Available"); // Set status to Available
        }

    }
    
    

    /*
    The method "toString" creates a string of the vans attributes including the image name
     */
    public String toString() {
    	String details = super.toString()+":" +this.getLastMaintenanceDate().toString()
    					+":"+this.getImageName();
    	return details;
    }



    /*
    The method "getDetails" returns a string all Vehicle Related Infos ,e.g. Vehicle Id, year,etc.
    It also returns the Rental Record information of all Vehicles.
    */
    public String getDetails() {
    	String details ="";
    	

        if (this.getMostRecentRentedSize()==0) {
            details = String.format("Vehicle ID:\t\t\t\t\t%s"
        			+"\nYear:\t\t\t\t\t\t\t%d"
        			+"\nMake:\t\t\t\t\t\t%s"
        			+"\nModel:\t\t\t\t\t\t%s"
        			+"\nNumber of seats:\t\t\t\t%d"
        			+"\nLast Maintenance Date:\t\t\t%s"
        			+"\nStatus:\t\t\t\t\t\t%s"
        			+"\nRENTAL RECORD"
        			+":\t\t\t\t\tempty\n"
        			,this.getId(),this.getYearManufactured(),this.getMake(),this.getModel()
        			,this.getNumberOfSeats(),
        			this.getLastMaintenanceDate().toString(),this.getStatus());
        }else{
       	 details = String.format("Vehicle ID:\t\t\t\t\t%s"
        			+"\nYear:\t\t\t\t\t\t\t%d"
        			+"\nMake:\t\t\t\t\t\t%s"
        			+"\nModel:\t\t\t\t\t\t%s"
        			+"\nNumber of seats:\t\t\t\t%d"
        			+"\nLast Maintenance Date:\t\t\t%s"
        			+"\nStatus:\t\t\t\t\t\t%s"
        			+"\nRENTAL RECORD\n"
        			+"%s\n"
        			,this.getId(),this.getYearManufactured(),this.getMake(),this.getModel()
        			,this.getNumberOfSeats(),this.getLastMaintenanceDate().toString(),
        			this.getStatus()
        			,this.retrieveMostRecentRentalRecord().getDetails());
       	     
          
            for (int i = 0; i < this.getMostRecentRentedSize() - 1; i++) {
                String dashedLine= "-----------------------------------------------\n";
                details = String.format("%s%s",details,dashedLine);
                details = String.format("%s%s",details,
                		retrieveMostRecentRentalRecordAtIndex(i).getDetails());
            }
        }
        return details;
    }
    
    
    
    
    
}