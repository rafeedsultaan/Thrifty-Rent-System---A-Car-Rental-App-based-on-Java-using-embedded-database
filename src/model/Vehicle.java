/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */

/*
The class Vehicle is implemented which encapsulates the all vehicle 
related information.
The Vehicle class is an abstract class because it contains some abstract 
methods i.e. methods like rent.returnVehicle,etc. that are not
implemented yet. They will be implemented by children of the vehicle class
 like Cars and Vans using the concept of inheritance,
and polymorphism.
 */
package model;


import java.util.ArrayList;
import java.util.Collections;


public abstract class Vehicle{ // made it abstract temporarily
    private String id; // vehicle id
    private int yearManufactured; // manufacture year
    private String make; // brand of the car
    private String model; // model of the brand
    private int numberOfSeats; //totals seats available in the car
    private String type; // vehicle  type as in car or van
    private String status;  // vehicle status
    private String imageName;

    
    private final int  MAXSIZE_RENTALRECORD =10;

    
    
    
    
    ArrayList<RentalRecord> mostRecentRented;
    // mostRecentRented contains 10 most recent records of a vehicle.
   


    /*
    Overloaded Constructor for Vehicle
     */
    protected Vehicle(String id,int yearManufactured,String make,String model,
    		int numberOfSeats,String type,String status){
        this.id = id;
        this.yearManufactured = yearManufactured;
        this.make = make;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
        this.status = status;
        mostRecentRented = new ArrayList<RentalRecord>();
    }


    public int getMostRecentRentedSize() {
    	return mostRecentRented.size();
    }
    
    public ArrayList<RentalRecord> getRentalRecordList(){
    	return mostRecentRented;
    }
    
    
    public String getImageName() {
    	return this.imageName;
    }
    
    public void setImageName(String imageName) {
    	this.imageName = imageName;
    }
    
   
    
    /*
    Accessor Method for ID
     */
    public String getId(){
        return this.id;
    }

    /*
    Accessor Method for Year Manufcatured
     */
    public int getYearManufactured(){
        return this.yearManufactured;
    }


    /*
    Accessor Method for Make
     */
    public String getMake(){
        return this.make;
    }


    /*
    Accessor Method for Model
     */
    public String getModel(){
        return this.model;
    }


    /*
    Accessor Method for Number of Seats
     */
    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }


    /*
    Accessor Method for Type
     */
    public String getType(){
        return this.type;
    }


    /*
    Accessor Method for Status
     */
    public String getStatus(){
        return this.status;
    }


    /*
    Mutator Method for Id
     */
    public void setId(String id){
        this.id = id;
    }


    /*
    Mutator Method for Year Manufactured
     */
    public void getYearManufactured(int yearManufactured){
        this.yearManufactured = yearManufactured;
    }


    /*
    Mutator Method for  Make
     */
    public void setMake(String make){
        this.make = make;
    }


    /*
    Mutator Method for model
     */
    public void setModel(String model){
        this.model = model;
    }


    /*
    Mutator Method for Number of Seats
     */
    public void setNumberOfSeats(int numberOfSeats){
            this.numberOfSeats = numberOfSeats;
    }


    /*
    Mutator Method for Type
     */
    public void setType(String type){
        this.type = type ;
    }


    /*
    Mutator Method for Status
     */
    public void setStatus(String status){
        this.status = status ;
    }



    /*
        The method "addRentalRecord" adds rental records for a specific vehicle id.
        The container mostRecentRecord is 10. So, once the capacity becomes full.
         We start deleting
        the oldest Rental Records. We only keep track of the 10 latest Rental Records.
    */
   public void addRentalRecord(RentalRecord rentalRecord){
        if(mostRecentRented.size()<MAXSIZE_RENTALRECORD){
            //mostRecentRented.(this.getRentalRecordLength()) = rentalRecord ;
            mostRecentRented.add(rentalRecord);
            Collections.sort(mostRecentRented);
        }
        else{
          
            mostRecentRented.remove(0);
            //mostRecentRented[mostRecentRented.length-1]= rentalRecord;
            mostRecentRented.add(rentalRecord);
            Collections.sort(mostRecentRented);
            
        }
   }



    /*
        The method "retrieveMostRecentRentalRecord" retrieves the latest rental
         record for a vehicle of a
        specific vehicle id.

    */
   public RentalRecord retrieveMostRecentRentalRecord(){
        if(mostRecentRented.size()<=0){
            return null;
        }
        else {
            return mostRecentRented.get(mostRecentRented.size()-1);
        }
   }

    /*
     The method "retrieveMostRecentRentalRecordIndex" retrieves the  rental 
     record located at a specific index,
      for a vehicle of a specific vehicle id.
    */
   public RentalRecord retrieveMostRecentRentalRecordAtIndex(int index){
        if(index>=0 && index<MAXSIZE_RENTALRECORD){
            return mostRecentRented.get(index);
        }
        else{
            return null;
        }
    }


    /*
     The method "updateMostRecentRentalRecord" updates the rental record
      of a vehicle,whose actualReturnDate, rentalFee and lateFee were missing
       before the vehicles was rented. After the vehicles is returned,
        we are provide with the information, actualReturnDate, rentalFee and lateFee.
      for a vehicle of a specific vehicle id.
    */
    public void updateMostRecentRentalRecord(DateTime actualReturnDate,double rentalFee,double lateFee){
        if(this.getMostRecentRentedSize()<=0){
            return;
        }
        else{    
            mostRecentRented.get(mostRecentRented.size()-1).setActualReturnDate(actualReturnDate);
            mostRecentRented.get(mostRecentRented.size()-1).setLateFee(lateFee);
            mostRecentRented.get(mostRecentRented.size()-1).setRentalFee(rentalFee);

            return;
        }
    }

    /*
     The abstract methods are created. So that they can be implemented by 
     classes, that will inherit them.The abstract methods are rent, 
     returnVehicle, performMaitenance and completeMaintenance.
    */
    public abstract void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException;
    public abstract void returnVehicle(DateTime returnDate) throws ReturnException;
    public abstract void performMaintenance()throws MaintenanceException;
    public abstract void completeMaintenance(DateTime completionDate) throws MaintenanceException;
    public String  getDetails() {
    	String details ="";
    	

         if (this.getMostRecentRentedSize()==0) {
             details = String.format("Vehicle ID:\t\t\t\t\t%s"
         			+"\nYear:\t\t\t\t\t\t\t%d"
         			+"\nMake:\t\t\t\t\t\t%s"
         			+"\nModel:\t\t\t\t\t\t%s"
         			+"\nNumber of seats:\t\t\t\t%d"
         			+"\nStatus:\t\t\t\t\t\t%s"
         			+"\nRENTAL RECORD"
         			+":\t\t\t\t\tempty\n"
         			,this.getId(),this.getYearManufactured(),this.getMake(),this.getModel()
         			,this.getNumberOfSeats(),this.getStatus());
         }else{
        	 details = String.format("Vehicle ID:\t\t\t\t\t%s"
         			+"\nYear:\t\t\t\t\t\t\t%d"
         			+"\nMake:\t\t\t\t\t\t%s"
         			+"\nModel:\t\t\t\t\t\t%s"
         			+"\nNumber of seats:\t\t\t\t%d"
         			+"\nStatus:\t\t\t\t\t\t%s"
         			+"\nRENTAL RECORD\n"
         			+"%s\n"
         			,this.getId(),this.getYearManufactured(),this.getMake(),this.getModel()
         			,this.getNumberOfSeats(),this.getStatus(),this.retrieveMostRecentRentalRecord().getDetails());
        	     
           
             for (int i = 0; i < this.getMostRecentRentedSize() - 1; i++) {
                 String dashedLine= "-----------------------------------------------\n";
                 details = String.format("%s%s",details,dashedLine);
                 details = String.format("%s%s",details,retrieveMostRecentRentalRecordAtIndex(i).getDetails());
             }
         }
         return details;
    }


    /*
     The method "toString" returns a string contains vehicle id, 
     yearManufactured, make, model, numberOfSeats and status
     of a vehicle.
    */
    public String toString() {
    	String details = this.getId() 
    	+":"+this.getYearManufactured()
    	+":"+this.getMake()
    	+":"+this.getModel()
        +":" + this.getNumberOfSeats() 
    	+":" +this.getStatus() ;
    	return details;

    }

}



