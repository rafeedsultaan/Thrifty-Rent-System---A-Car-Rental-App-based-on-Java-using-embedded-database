/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;
import java.util.ArrayList;

/*
The class ThriftyRent System is an application class designed to control
 the actions of the program.
*/
public class ThriftyRentSystem{
	// Total no. of Vehicles in the Thrift rent system.
    private final int MAX_VEHICLES = 50; 
 // The minimum id length of the vehicle
    private final int MIN_VEHICLEID_LENGTH = 3; 
 // The constant that hold the value for the number of car seats allowed.
    private final int CAR_SEATS_ALLOWED[] = {4,7};
 // The constant that hold the value for the number of van seats allowed.
    private final int VAN_SEATS_ALLOWED = 15; 
    
   
    private ArrayList<Vehicle> vehicles;
    private static ThriftyRentSystem model;
    

    /*
    Constructor for the Thrift Rent System is private
    since, I have implemented the singleton pattern for the model.
    Only once instance of the model is needed.
    */
    private ThriftyRentSystem(){
    	vehicles = new ArrayList<Vehicle>();
    }
    
    // Implemented design pattern singleton
    
    /* 
     *  The method getModel() returns the model of the ThriftyRentSystem. Since I have implemented 
     *  the singleton pattern for the model, it will only return one instance of the model.
     */
    public static ThriftyRentSystem getModel() {
    	if(model==null) {
    		model = new ThriftyRentSystem();
    	}
    	return model;
    }
    
    /*
     * The method getVehiclesSize() returns the size of the vehicles present in the database.
     */
    public int getVehiclesSize() {
    	return vehicles.size();
    }

    /*
     * The method getVehiclesList returns the list of vehicles in the database.
     */
    public ArrayList<Vehicle> getVehiclesList() {
    	return vehicles;
    }

    


    /*
     *	The method addVehicle() adds Vehicles into the Thrift Rent System Database.
     *	It validates the input and throws Exceptions if there are errors.
     */
    public void addVehicle(String id,String type,String inputYear,String make,String model
    		,String inputNumberOfSeats,String status,String inputLastMaintenanceDate)
    				throws AddVehicleException,InvalidIdException,InvalidInputException,
    				InvalidDateFormatException{
    	if (getVehiclesSize()==MAX_VEHICLES){
            throw new AddVehicleException("Error: Full Capacity.The Maximum "
            		+ "Number of Vehicles that can be added "
            		+ "in ThriftyRentSystem is 50.");
        }else if (id.length()<MIN_VEHICLEID_LENGTH) {
        	throw new InvalidIdException("Error: Length of Vehicle ID can not be less than to 3");
        }else if (!id.substring(0,(MIN_VEHICLEID_LENGTH-1)).equals("C_") &&
        		!id.substring(0,(MIN_VEHICLEID_LENGTH-1)).equals("V_")){
        	throw new InvalidIdException("Error: Invalid Vehicle ID");
        }else if(isVehicleIdPresent(id)) {
    		throw new InvalidIdException("Error: Vehicle ID is already present in the database.");
    	}else if (ThriftyRentSystem.isErrorPresentNumber(inputNumberOfSeats)==true){
            throw new InvalidInputException("Error: Invalid input for number of seats.");
        }else if(ThriftyRentSystem.isErrorPresentNumber(inputYear)){ // Checks if the input year is a number.
        	 throw new InvalidInputException("Error: Invalid input for number of year.");
       }
       
       int year = Integer.parseInt(inputYear);
       int numberOfSeats = Integer.parseInt(inputNumberOfSeats);
       
       if(year<=0) {
    	   throw new InvalidInputException("Error:Year can not be negative.");
       }else if (type.equals("Car") && numberOfSeats!=CAR_SEATS_ALLOWED[0] 
    		   && numberOfSeats!=CAR_SEATS_ALLOWED[1]){ 
            throw new AddVehicleException("Error: No. of seats of Car can be 4 or 7 only.");
        }else if (type.equals("Van") && numberOfSeats!=VAN_SEATS_ALLOWED){ // Checks if the vehicle is a Car.
            throw new AddVehicleException("Error: No. of seats of Van can be 15 only.");
        }else if (type.equals("Car")){ // Adds Cars into the Thrift Rent System
            Car c = new Car(id,year,make,model,numberOfSeats,"Car","Available");
        	c.setImageName("default.png");
            vehicles.add(c);
        }
        else if(type.equals("Van")){ // Adds Vehicles into the Thrift Rent System.
        	 if(isInvalidDateFormat(inputLastMaintenanceDate)) {
             	throw new InvalidInputException("Error: Invalid Date Format. Date Format is (DD/MM/YYYY)");
             }
        	isErrorPresentDate(inputLastMaintenanceDate);
        	String[] tokens = inputLastMaintenanceDate.toString().split("/");
        	DateTime lastMaintenanceDate = new DateTime(Integer.parseInt(tokens[0]),
        			Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
            Van v = new Van(id,year,make,model,numberOfSeats,"Van","Available",lastMaintenanceDate);
            v.setImageName("default.png");
            vehicles.add(v);
       }
    }




    /*
     * The method rentVehicle() rents Vehicles. It checks for errors in input. It validates those inputs.
     *	Throws exceptions if the vehicle can not be rented.
     */
    public void rentVehicle(String id,String customerId,String inputRentDate,String inputNumOfRentDay) 
    		throws InvalidIdException, InvalidInputException, RentException, InvalidDateFormatException{
        
        DateTime lastDateAvailableForRent = null;
        int vehicleKey = searchIndex(id);

        if(!isVehicleIdPresent(id)){ 
        	// The method "isVehicleIdPresent" checks if the vehicles id is present in the database.
            throw  new InvalidIdException("Error: Id is not found in the database.");
        }else if(isInvalidDateFormat(inputRentDate)){ 
        	// The method "isErrorPresentDate" checks if there are errors inside the date input.
        	throw new InvalidInputException("Error: Invalid Date Format for Rent Date. "
        			+ "Date Format is (DD/MM/YYYY) ");
        }else if (isErrorPresentNumber(inputNumOfRentDay)){
        	// The method "isErrorPresentNumber" checks if there are errors inside the date input.
        	throw new InvalidInputException("Error: Number of Rent Days must be a Number");
        }else if (Integer.parseInt(inputNumOfRentDay)<0){ // Number of Rent Days can not be Negative.
            throw new InvalidInputException("Error: Number of Days must be positive");
        }
  
        isErrorPresentDate(inputRentDate);
        String[] tokens = inputRentDate.toString().split("/");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        DateTime rentDate = new DateTime(day,month,year);
        int numOfRentDay = Integer.parseInt(inputNumOfRentDay);
       
        if (isConflictWithLastDateAvailableForRent(vehicleKey,inputRentDate,inputNumOfRentDay)) {
        	lastDateAvailableForRent 
        	= new DateTime(vehicles.get(vehicleKey).retrieveMostRecentRentalRecord().getActualReturnDate(),1);	
        	throw new RentException("Error: Request Rejected. According "
        			+ "to past bookings, the Vehicle "
        	+vehicles.get(vehicleKey).getId()+" is available for rent from "
        	+lastDateAvailableForRent+"Rent Date must start from last date available"
        			+ " for rent");
        }
        vehicles.get(vehicleKey).rent(customerId,rentDate,numOfRentDay); 
    }


    /*
     *	The method returnVehicle() rents Vehicles. It checks for errors in input. It validates those inputs.
     *	It throws exception if there are errors.
     */
    public void returnVehicle(String vehicleId,String inputReturnDate) 
    		throws InvalidIdException, ReturnException, InvalidDateFormatException{
        if (isInvalidDateFormat(inputReturnDate)){
            return;
        }
        /*
         *  The method "isErrorPresentDate" checks for Errors in Date.
         *   Throws an InvalidDateFormatException if
         *    there are errors
         */
        isErrorPresentDate(inputReturnDate);
        String[] str = inputReturnDate.toString().split("/");
        int day = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);
        DateTime returnDate = new DateTime(day,month,year);
        int vehicleKey = searchIndex(vehicleId);
        

        if (!isVehicleIdPresent(vehicleId)){ 
        	// Checks if the Vehicle Id is present inside the Thrift Rent System Database.
            throw new InvalidIdException("Error: Vehicle "+vehicleId+ "is not found in the database.");
        }
    	vehicles.get(vehicleKey).returnVehicle(returnDate);        
    }


    /*
    The method vehicleMaintenance() rents Vehicles. It checks for 
    errors in input. It validates those inputs.
    */
    public void vehicleMaintenance(String inputVehicleId)
    		throws InvalidIdException, MaintenanceException{
        
        int vehicleKey = searchIndex(inputVehicleId);

        if (!isVehicleIdPresent(inputVehicleId )){ 
        	// Checks if the Vehicle Id is present inside the database.
            throw new InvalidIdException("Error: Vehicle is not present in the database.");
        }
        vehicles.get(vehicleKey).performMaintenance();

        
    }

 


    /*
     * The method completeMaintenance is used to complete the maintenance of the 
     * vehicle in the database. 
     * It checks for errors in input. It validates those inputs.
    */
    public void completeMaintenance(String inputVehicleId,String inputCompleteMaintenanceDate)
    		throws InvalidInputException, MaintenanceException, 
    		InvalidIdException, InvalidDateFormatException{
        DateTime completeMaintenanceDate=null;
       
     
        int vehicleKey = searchIndex(inputVehicleId);
        // The method "searchIndex" searches for the Vehicle Id inside the database. 
        //Returns -1, if the vehicle id doesn't exist.
        if (!isVehicleIdPresent(inputVehicleId)){
        	// Checks if the Vehicle ID is present inside the database
            throw new InvalidIdException("Error: Vehicle Id not found in the database.");
        }
        
        if (vehicles.get(vehicleKey).getType().equals("Van")){
        	if (isInvalidDateFormat(inputCompleteMaintenanceDate)){
        		// Check if the there are errors inside the completeMaintenance Date input
        		throw new InvalidInputException("Error: Invalid Date Format.");
            }
        	 isErrorPresentDate(inputCompleteMaintenanceDate);
            try{
                String[] tokens = inputCompleteMaintenanceDate.toString().split("/");
                int day = Integer.parseInt(tokens[0]);
                int month = Integer.parseInt(tokens[1]);
                int year = Integer.parseInt(tokens[2]);
              //Constructs the completeMaintenaceDate Object
                completeMaintenanceDate = new DateTime(day,month,year); 
            }catch(Exception e){
                throw new InvalidInputException("Error: Invalid Date Format.");
            }
        }
        vehicles.get(vehicleKey).completeMaintenance(completeMaintenanceDate);        
    }


    /*
     * The method searchIndex searches the vehicleId from vehicles 
     * and returns -1 if the Id can not be found
    */
    public int searchIndex(String id){
        int  searchIndex = -1;
        for(int i =0;i<vehicles.size();i++) {
            if (vehicles.get(i).getId().equals(id)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }




    /*
     *	The method "isErrorPresentDate" checks for errors in Date.
     *	If there is an error, it returns true. Otherwise, it returns false.
     */

    private void isErrorPresentDate(String date) throws InvalidDateFormatException{
        
        boolean isLeapYear = false;
        int year,month,day;

        try{
            String[] tokens = date.toString().split("/");
            day = Integer.parseInt(tokens[0]);
            month = Integer.parseInt(tokens[1]);
            year = Integer.parseInt(tokens[2]);
            new DateTime(day,month,year);
        }
        catch (Exception e){
        	throw new InvalidDateFormatException("Error: Invalid Date. Format."
        			+ " Date format is  (DD/MM/YYYY)\")");
        }

        if(year<0){
            throw new InvalidDateFormatException("Error:Year can not be a Negative Number.");
        }
        if(day<=0){
        	throw new InvalidDateFormatException("Error:Day can not be a Negative Number.");
        }
        if(month<=0 || month>12){
            throw new InvalidDateFormatException("Error:Month can not be a Negative Number.");
        }
        if(year%4 == 0 && (year %100 !=0 || year%400==0)){ // Checks if the year is a leap years
            isLeapYear = true;
        }
        if(isLeapYear && month==2 && day>29) {
        	// If the year is a leapYear. Changes the available dates for February to 29.
            throw new InvalidDateFormatException("Error: Invalid date");
        }else if(!isLeapYear && month==2 && day>28){ 
        	//// If the year is NOT a leapYear. Changes the available dates for February to 28.
            
            throw new InvalidDateFormatException("Error: Invalid date "+year+ "is not a Leap Year.");
        }else if((month==1 || month==3 || month==5||month==7 ||month==8 ||
        		month==10 || month==12) && day>31){
        	// Checks for Invalid Dates e.g. January has 31 days.
            throw new InvalidDateFormatException("Error: Invalid date");
        }else if((month==4 || month==6 || month ==9 || month==11) && day>30){
        	// Checks for Invalid Dates e.g. June has 30 days.
        	throw new InvalidDateFormatException("Error: Invalid date");
        }
    }

    /*
     *	The method "isErrorPresentNumber" checks for errors in Number inputs.
     *	If there is an error, it returns true. Otherwise, it returns false.
     */
    private static boolean isErrorPresentNumber(String number){
        boolean valid = true;
        for(int i = 0;i<number.length();i++){
            if(Character.isDigit(number.charAt(i))){
                valid = false;
                break;
            }
        }
        return valid;
    }



    /*
     *	The method "isVehicleIdPresent checks if the vehicle id is present inside the database.
     *	It returns true, if the vehicle id is present. Otherwise, it returns false.
     */
    
    private boolean isVehicleIdPresent(String vid){
        int vehicleKey = searchIndex(vid);
        if(vehicleKey==-1){
            return false;
        }
        return true;
    }



    /*
     * The method isInvalidDateFormat checks if the date format is valid. 
     * It returns true if there is an error
     * 
     */

    private boolean isInvalidDateFormat(String date){
        boolean isErrorPresent = false;
        boolean isValidDateFormat = true;
        int year,month,day;

        if(date.length()<10){
            isErrorPresent = true;
        }
        try{
            String[] str = date.toString().split("/");
            day = Integer.parseInt(str[0]);
            month = Integer.parseInt(str[1]);
            year = Integer.parseInt(str[2]);
            new DateTime(day,month,year);
        }
        catch(Exception e){
            isErrorPresent = true;
        }
        if(date.length()>=10){
            /*
            The isValidDateFormat checks the "DD/MM/YYYY". If there is an error present,
             it returns true. Other wise it returns false
            */
            isValidDateFormat = Character.isDigit(date.toString().charAt(0))&& 
            		Character.isDigit(date.toString().charAt(1)) 
            		&& date.toString().charAt(2)=='/' && Character.isDigit(date.toString().charAt(3))
                                && Character.isDigit(date.toString().charAt(4)) 
                                &&  date.toString().charAt(5)=='/' 
                                && Character.isDigit(date.toString().charAt(6)) 
                                && Character.isDigit(date.toString().charAt(7))
                                && Character.isDigit(date.toString().charAt(8)) 
                                && Character.isDigit(date.toString().charAt(9));
        }
        if(!isValidDateFormat){
            isErrorPresent = true;
        }
        try{
            String[] tokens = date.toString().split("/");
            // The variable tokens tokenizes the date into day, month and year.
            day = Integer.parseInt(tokens[0]);
            month = Integer.parseInt(tokens[1]);
            year = Integer.parseInt(tokens[2]);
            new DateTime(day,month,year); // The rentDate Date object is constructed
        }
        catch (Exception e){
        	// If the object, can not be constructed. There is an error present in the Date Format.
            isErrorPresent = true; 
        }
        return isErrorPresent;
    }
    
    /*
     * The method isConflictWithLastDateAvailableForRent checks f there are conflicts in Rental Period.
     * It returns true, if there is a conflict. Otherwise, it returns false.
     */
    public boolean isConflictWithLastDateAvailableForRent(int vehicleKey,
    		String inputRentDate,String inputNumOfRentDay) {
    	 // To make sure all past bookings are Sorted, New Rent Dates must 
    	//be greater than old Return Dates of the same cars
        try {
  
	    	 String[] tokens = inputRentDate.toString().split("/");
	         DateTime rentDate = new DateTime(Integer.parseInt(tokens[0]),
	        		 Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
	    	 
	         if(vehicleKey!=-1 && vehicles.get(vehicleKey).getMostRecentRentedSize()>0 &&
	        		 vehicles.get(vehicleKey).getStatus().equals("Available")){
	                if(vehicles.get(vehicleKey).retrieveMostRecentRentalRecord()
	                		.getActualReturnDate()!=null){
	                    DateTime lastDateAvailableForRent = 
	                    		new DateTime(vehicles.get(vehicleKey).retrieveMostRecentRentalRecord()
	                    				.getActualReturnDate(),1); 
	                    // Assuming rent date and return date do not overlap
	                    int differenceBetweenRentDateAndLastDateAvailableForRent = 
	                    		DateTime.diffDays(rentDate,lastDateAvailableForRent);
	                    if(differenceBetweenRentDateAndLastDateAvailableForRent<1){ 
	                    	// Assuming return date and rent date do not overlap
	                        return true;
	                    }
	                }
	            }
        }
    	catch (Exception e){
            return true;
        }
    	return false;
    }
    
    /*
     * The method getMaxVehicleSize returns the maximum vehicles allowed 
     * in the Thrifty rent database.
     * 
     */
    public int getMaxVehicleSize() {
    	return MAX_VEHICLES;
    }
    
}
