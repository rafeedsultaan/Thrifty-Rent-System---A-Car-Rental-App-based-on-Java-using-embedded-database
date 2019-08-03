/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Car;
import model.FileOperations;
import model.InvalidIdException;
import model.MaintenanceException;
import model.ThriftyRentSystem;
import model.Van;
import model.Vehicle;
import model.database.DatabaseOperations;
import view.AddCarWindowView;
import view.AddVanWindowView;
import view.CompleteMaintenanceWindowView;
import view.RentWindowView;
import view.ReturnWindowView;
import view.ViewDetailsWindowView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/* 
 * ViewDetailsWindowController is the controller associated with ViewDetailsWindow.
 */


public class ViewDetailsWindowController implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	ViewDetailsWindow.fxml file. No new GUI component is 
	 * created in this class.
	 */
	 
	// This a reference to the back button in the ViewDetailsWindowView.fxml file
	@FXML
	Button backButton;
	// This a reference to image view of the vehicle created in the ViewDetailsWindowView.fxmlfile
	@FXML
	ImageView vehicleImageView; 
	 // This a reference to GridPane created in the  ViewDetailsWindowView.fxmlfile
	@FXML
	GridPane viewDetailsWindowGridPane;
	@FXML
	// This a reference to ListView create in the  ViewDetailsWindowView.fxmlfile
	ListView<String> viewDetailsListView;
	// This a reference to HBox created in the  ViewDetailsWindowView.fxmlfile
	@FXML
	HBox vehicleDetailsHBox;
	
	// The vehicle details of this ID is displayed in the ViewDetails window
	private String vehicleId;
	// The model of ThriftyRentSystem
	private static ThriftyRentSystem trs;

	
	
	
	
	/*
	 * Constructor of the ViewDetailsWindowController initialized 
	 * with the vehicleId whose records needs to replaced
	 * 
	 */
	public ViewDetailsWindowController(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	/*
	 * The method handleImportDataFromFileClick is used to import data 
	 * from the text file  into the model.
	 */
	public void handleImportDataFromFileClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		trs.getVehiclesList().clear();
		
		FileChooser fc = new FileChooser();
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\files";
		fc.setInitialDirectory(new File(currentDirectory));
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile!=null) {
			
			String path=selectedFile.getAbsolutePath();
			try {
				FileOperations.importFromFile(trs,path);
			} 
			catch (FileNotFoundException e) {
				alert.setContentText("Error: No Such File Found");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
		else {
			alert.setContentText("Error: File Not Valid");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		
		
		
	}
	
	/*
	 * The method handleExportDataFromFileClick is used to export data 
	 * from the model into a text file.
	 */
	public void handleExportDataToFileClick(){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\files";
		
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setInitialDirectory(new File(currentDirectory));
		Stage stage = ( Stage) viewDetailsWindowGridPane.getScene().getWindow(); 
		File file = dirChooser.showDialog(stage);
		
		if(file!=null) {
			
			String path=file.getAbsolutePath()+"\\export_data.txt";
			
			try {
				FileOperations.exportToFile(trs,path);
			}
			catch (IOException e) {
				alert.setContentText("Error: File cannot be created, or cannot be opened");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			catch (Exception e) {
				alert.setContentText("Error: File Not Valid");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
	}
	
	
	/*
	 * The method handleSaveToDatabaseClick is used to save the data into
	 * the database.
	 */
	public void handleSaveToDatabaseClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			DatabaseOperations.writeToDatabase(trs);
		}
		catch (Exception e){
			alert.setContentText("Error: Could not Write to Database");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
	
	

	/*
	 * The method handleQuitProgramClick is used to quit the program 
	 * and save the vehicle information and rental record information into 
	 * the database
	 */
	public void handleQuitProgramClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			DatabaseOperations.writeToDatabase(trs);
		}
		catch (Exception e){
			alert.setContentText("Error: Could not Write to Database");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		System.exit(0);
	}
	
	
	/*
	 * The method handleAddCarClick is used to add a car using the menu bar
	 * option
	 */
	public void handleAddCarClick(ActionEvent e){
		AddCarWindowView view = new AddCarWindowView();
		view.loadWindow();
	}
	
	
	/*
	 * The method handleAddVanClick is used to add a van using the menu bar
	 * option
	 */
	
	public void handleAddVanClick(){
		AddVanWindowView view = new AddVanWindowView();
		view.loadWindow();
	}

	
	/*
	 * The method handleRentVehicleClick loads the RentWindowView
	 */
	public void handleRentVehicleClick(){
		RentWindowView view = new RentWindowView();
		view.loadWindow(vehicleId);
		loadGUIItems();
	}
	
	
	/*
	 * The method handleReturnVehicle loads the ReturnWindowView
	 */
	public void handleReturnVehicleClick(){
		ReturnWindowView view = new ReturnWindowView();
		view.loadWindow(vehicleId);
		loadGUIItems();
	}
	
	/*
	 * The method handleReturnVehicle loads the ReturnWindowView
	 */
	public void handlePerformMaintenanceClick(){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			trs.vehicleMaintenance(vehicleId);
			loadGUIItems();
		}
		catch(MaintenanceException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		catch(InvalidIdException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
	
	
	/*
	 * The method handleCompleteMaintenanceClick loads the 
	 * CompleteMaintenanceWindowView
	 */
	public void handleCompleteMaintenanceClick(){
		CompleteMaintenanceWindowView view = new CompleteMaintenanceWindowView();
		view.loadWindow(vehicleId);
		loadGUIItems();
	}



	/*
	 * The method initialize is used initialize all the variables in the View Details
	 * Window . For example,
	 * the image of the vehicle
	 * in the View Details Window. It is initialized it with the vehicle image.
	 * The backButton to go back to the main program window is used to set the controller
	 * to the MainProgramWindow, so that the control goes back to the main program window.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trs = ThriftyRentSystem.getModel();
		
		backButton.setOnAction(new SceneChangeToMainProgramWindowController());
		
		
		int vehicleKey = trs.searchIndex(vehicleId);
		Vehicle vehicle = trs.getVehiclesList().get(vehicleKey);
		String imageName = vehicle.getImageName();
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\images\\"+imageName;
		Image image = new Image(new File(currentDirectory).toURI().toString());
		image.getWidth();
		image.getHeight();
		vehicleImageView.setPreserveRatio(false);
		vehicleImageView.setImage(image);
		vehicleImageView.setFitWidth(500);
		loadGUIItems();
	}
	
	/*
	 * The method  loadGUIItems is used to load the selected vehicles
	 * image and its corresponding rental records.
	 */
	private void loadGUIItems() {
		
		int vehicleKey = trs.searchIndex(vehicleId);
		Vehicle vehicle=trs.getVehiclesList().get(vehicleKey);
		if(vehicle instanceof Car) {
			Car car = (Car) vehicle;
			String details = car.getDetails();
			Integer year = car.getYearManufactured();
			Integer numberOfSeats = car.getNumberOfSeats();
			boolean isRentalRecordsEmpty = false;
			if (car.getMostRecentRentedSize()==0) {
				isRentalRecordsEmpty = true;
	        }
			loadGUIVehicleDetails(car.getId(),car.getType(),car.getMake(),car.getModel(),
					year.toString(),numberOfSeats.toString(),"",car.getStatus(),isRentalRecordsEmpty);
			loadGUIRentalRecords(car,details);
		}
		
		if(vehicle instanceof Van) {
			Van van = (Van) vehicle;	
			Integer year = van.getYearManufactured();
			Integer numberOfSeats = van.getNumberOfSeats();
			boolean isRentalRecordsEmpty = false;
			if (van.getMostRecentRentedSize()==0) {
				isRentalRecordsEmpty = true;
	        }
			loadGUIVehicleDetails(van.getId(),van.getType(),van.getMake(),van.getModel(),
					year.toString(),numberOfSeats.toString(),van.getLastMaintenanceDate().toString(),
					van.getStatus(),isRentalRecordsEmpty);
			String details = van.getDetails();
			loadGUIRentalRecords(van,details);
		}
	}
	
	/*
	 * The method  loadGUIVehicle is used to load the selected vehicles
	 * details.
	 */
	private void  loadGUIVehicleDetails(String id,String type,String make,String trs,
			String year,String numberOfSeats,
			String lastMaintenanceDate,String status,boolean isRentalRecordsEmpty) {
			vehicleDetailsHBox.getChildren().clear();
			vehicleDetailsHBox.getChildren().add(ViewDetailsWindowView.createVehicleDetails(
			id, type, make, trs, year, numberOfSeats, 
			lastMaintenanceDate, status, isRentalRecordsEmpty)) ;
				
	}
	

	/*
	 * The method  loadGUIVehicle is used to load the selected vehicles
	 * rentalRecords.
	 */
	private void loadGUIRentalRecords(Vehicle vehicle,String details) {
		
		viewDetailsListView.getItems().clear();
		 viewDetailsListView.getItems().add(vehicle.retrieveMostRecentRentalRecord().getDetails());
        
        for (int i = 0; i < vehicle.getMostRecentRentedSize() - 1; i++) {
            viewDetailsListView.getItems().add(vehicle.retrieveMostRecentRentalRecordAtIndex(i).getDetails());
        }
        
	}
	


}



