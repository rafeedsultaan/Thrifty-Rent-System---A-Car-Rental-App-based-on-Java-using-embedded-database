/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;
	
import model.DataGeneration;
import model.ThriftyRentSystem;
import model.database.CheckTableExist;
import model.database.DatabaseException;
import model.database.DatabaseOperations;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/*
 * The StartUp class is the driver class to load the Thrifty Rent Application
 */
public class StartUp extends Application {
	
	
	// an instance of the model is load the data into the model
	private static ThriftyRentSystem model = ThriftyRentSystem.getModel(); 
	@Override
	
	/*
	 * The start method launches the Thrifty Rent Application.
	 * It displays an error message if it can not.
	 */
	public void start(Stage primaryStage){	
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Alert");
		
			try {
				// if the database exists load the model from the database
				if(CheckTableExist.checkVehicleTableExist()) {
					DatabaseOperations.readFromDatabase(model);
				}
				// Otherwise, generate 10 random cars and 5 random vehicles with at least 
				//2 rental records.
				else {
					DataGeneration.generateVehicleData(model);
					DataGeneration.generateRentalRecordData(model);
				}
				
				//loading the main program window
				MainProgramWindowView mainProgramWindowView= new MainProgramWindowView();
				mainProgramWindowView.loadWindow(primaryStage);
			}
			catch(DatabaseException e) {
				alert.setContentText(e.getMessage());
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			catch(Exception e) {
				alert.setContentText(e.getMessage());
				alert.setHeaderText(null);
				alert.showAndWait();;
			}
				
		}
}
