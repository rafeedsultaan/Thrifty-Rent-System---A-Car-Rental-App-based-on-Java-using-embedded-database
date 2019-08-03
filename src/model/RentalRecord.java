/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */

/*
The class Rental Record is implemented to record all rental record 
information for a vehicle
of specific id.
 */
package model;


public class RentalRecord implements Comparable<RentalRecord>{
    private String id; // Rental Record Id
    private DateTime rentDate; // Return Date
    private  DateTime estimatedReturnDate; // Estimated Return Date
    private  DateTime actualReturnDate; // Actual Return Date
    private double rentalFee; // Rental Fee
    private double lateFee; // Late Fee


    /*
    Constructor for Rental Record
     */
    public RentalRecord(String vehicleId,String customerId,DateTime rentDate,
    		DateTime estimatedReturnDate){
        this.id = vehicleId +"_"+ customerId +"_"+ rentDate.getEightDigitDate();
        this.rentDate = rentDate;
        this.estimatedReturnDate = estimatedReturnDate;
        this.actualReturnDate = null;
        this.rentalFee = 0;
        this.lateFee = 0;
    }


    /*
    Constructor for Rental Record
     */
    public RentalRecord(String vehicleId,String customerId,DateTime rentDate,
    		DateTime estimatedReturnDate,DateTime actualReturnDate,
    		double rentalFee,double lateFee){
        this(vehicleId,customerId,rentDate,estimatedReturnDate);
        this.actualReturnDate = actualReturnDate;
        this.rentalFee = rentalFee;
        this.lateFee = lateFee;
    }


    /*
    Mutator method for Id
     */
    public void setId(String id){
        this.id = id;
    }



    /*
    Accessor method for Id
     */
    public String getId(){
        return this.id;
    }


    /*
    Mutator method for rentDate
     */
    public void setRentDate(DateTime rentDate){
        this.rentDate = rentDate;
    }


    /*
    Accessor method for rentDate
     */
    public DateTime getRentDate(){
        return this.rentDate;
    }


    /*
    Accessor method for estimatedReturnDate
     */
    public DateTime getEstimatedReturnDate(){
        return this.estimatedReturnDate;
    }



    /*
    Mutator method for estimatedReturnDate
     */
    public void setEstimatedReturnDate(DateTime estimatedReturnDate){
        this.estimatedReturnDate = estimatedReturnDate;
    }



    /*
    Accessor method for actualReturnDate
     */
    public DateTime getActualReturnDate(){
        return this.actualReturnDate;
    }



    /*
    Mutator method for actualReturnDate
     */
    public void setActualReturnDate(DateTime actualReturnDate){
        this.actualReturnDate = actualReturnDate;
    }


    /*
    Accessor method for Rental Fee
     */
    public double getRentalFee(){
        return this.rentalFee;
    }


    /*
    Accessor method for lateFee
     */
    public double getLateFee(){
        return this.lateFee;
    }


    /*
    Mutator method for RentalFee
     */
    public void setRentalFee(double rentalFee){
        this.rentalFee = rentalFee;
    }

    /*
    Mutator method for lateFee
     */
    public void setLateFee(double lateFee){
        this.lateFee = lateFee;
    }




    /*
     The method "toString" returns a string contains vehicle id,
      yearManufactured, make, model, numberOfSeats and status
     of a vehicle.
    */
    public String toString(){
        String rf;
        String lf;
        String ard = "";
        
        Double doubleRF= new Double(0.0);
        Double doubleLF= new Double(0.0);
        if(actualReturnDate==null){
            rf="none";
            lf= "none";
            ard = "none";
        }
        else{
            //rf = String.format("%.2lf", this.rentalFee);
            //lf = String.format("%.2lf", this.lateFee);
            ard = actualReturnDate.toString();
            doubleRF = new Double(this.rentalFee);
            doubleLF = new Double(this.lateFee);
            rf = doubleRF.toString();
            lf = doubleLF.toString();
            
        }
        return id+":"+rentDate+":"+estimatedReturnDate+":"+ard+":"+rf+":"+lf;
    }



    /*
    The method "getDetails" returns a string all Vehicle Related Infos ,e.g.
     Vehicle Id, year,etc.
    It also returns the Rental Record information of all Vehicles.
    */
    public String getDetails(){
        String details="";

        if(this.getActualReturnDate()!=null){  
            details = String.format("Record ID:\t\t\t\t\t%s"
                    + "\nRent Date:\t\t\t\t\t%s"
                    + "\nEstimated Return Date:\t\t\t%s"
                    +"\nActual Return Date:\t\t\t\t%s" 
                    + "\nRental Fee:\t\t\t\t\t%.2f"
                    + "\nLate Fee:\t\t\t\t\t\t%.2f\n",
                    this.getId(),this.getRentDate(),this.getEstimatedReturnDate(),
                    this.getActualReturnDate(),this.getRentalFee(),this.getLateFee());
            
            
        }
        else {
        	details = String.format("Record ID:\t\t\t\t\t%s"
                    + "\nRent Date:\t\t\t\t\t%s"
                    + "\nEstimated Return Date:\t\t\t%s\n",
                    this.getId(),this.getRentDate(),this.getEstimatedReturnDate());
        }
        
        return details;
    }
    
    
    /*
     * The method compareTo is used to sort the RentalRecords
     * in ascending order.
     */
    @Override
    public int compareTo( final RentalRecord o) {
        return DateTime.diffDays(this.rentDate, o.rentDate);
    }
}


