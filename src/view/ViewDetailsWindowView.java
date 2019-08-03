/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.util.ArrayList;

import controller.ViewDetailsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/* 
 * ViewDetailsWindowView class contains the ViewDetailsWindowView
 * which is contained inside the ViewDetailsWindowView.fxml file
 * ViewDetailsWindowView.fxml file contains the static GUI view of
 * the View Details Window
 * 
 * ViewDetailsWindowController is the controller associated with this view
 */ 
public class ViewDetailsWindowView {

	/*
	 * The method loadWindow loads the ViewDetailsWindowView.
	 * ViewDetailsWindowView.fxml contains ViewDetailsWindowView Layout
	 */
	public void loadWindow(Stage window,String vehicleId) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ViewDetailsWindowView.fxml"));
			loader.setController(new ViewDetailsWindowController(vehicleId));
			Parent root = loader.load();
			Scene vehicleDetailsWindow = new Scene(root);
			window.setTitle("View Details Window");
			window.setScene(vehicleDetailsWindow);
			window.setResizable(false);
			window.show();
		}
		catch (Exception exp) {
			alert.setContentText("Error: Could not load View Details Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
	

	/*
	 * The method createVehicleDetail creates the GUI Details component of the vehicle
	 * object based on the number of vehicles inside the Thrifty Rent System
	 * inside the vehicle details window.
	 */
	public static GridPane createVehicleDetails(String id,String type,String make,
			String model,String year,String numberOfSeats,String lastMaintenanceDate,
			String status,boolean isRentalRecordsEmpty) {
		
		
		ArrayList<Label> labelList =getVehicleDetailsLabelList(id,type,make,model,
				year,numberOfSeats,lastMaintenanceDate,
				status);
		
		GridPane grid = new GridPane();
		for(int i=0;i<=5;i++) {
			grid.add(labelList.get(i),0,i);
		}
		for(int i=0,j=9;i<=5;i++,j++) {
			grid.add(labelList.get(j),1,i);
		}
		
		if(type.equals("Van")) {
			labelList.get(15).setText(lastMaintenanceDate);
			grid.add(labelList.get(6),0, 6);
			grid.add(labelList.get(7),0, 7);	
			grid.add(labelList.get(15),1, 6);
			grid.add(labelList.get(16),1,7);
			if(isRentalRecordsEmpty) {
				labelList.get(8).setText("RENTAL RECORD:");
				grid.add(labelList.get(17),1,8);
			}
			grid.add(labelList.get(8),0,8);
		}
		else {
			grid.add(labelList.get(7),0,6);
			grid.add(labelList.get(16),1,6);
			if(isRentalRecordsEmpty) {
				labelList.get(8).setText("RENTAL RECORD:");
				grid.add(labelList.get(17),1,7);
			}
			grid.add(labelList.get(8),0,7);
		}
		return grid;
	}
	
	/*
	 * The method getVehicleDetailsLabelList creates all the labels used inside
	 * the vehicles details for the GUI Component in View Details Window.
	 */
	private static ArrayList<Label> getVehicleDetailsLabelList(String id,String type,
			String make,String model,String year,
			String numberOfSeats,String lastMaintenanceDate,String status){
		ArrayList<Label> labelList = new ArrayList<Label>();
		labelList.add(new Label("Vehicle ID:\t"));//index 0
		labelList.add(new Label("Type:\t"));//index 1
		labelList.add(new Label("Make:\t"));//index 2
		labelList.add(new Label("Model:\t"));//index 3
		labelList.add(new Label("Year:\t"));//index 4
		labelList.add(new Label("Number of seats:\t"));//index 5
		labelList.add(new Label("Last Maintenance Date:\t"));//index 6;
		labelList.add(new Label("Status:\t"));// index 7
		labelList.add(new Label("RENTAL RECORD\t"));//index 8
			
		labelList.add(new Label(id));//index 9
		labelList.add(new Label(type));//index 10
		labelList.add(new Label(make));//index 11
		labelList.add(new Label(model));//index 12
		labelList.add(new Label(year));//index 13
		labelList.add(new Label(numberOfSeats));//index 14	
		labelList.add(new Label(lastMaintenanceDate));//index15	
		labelList.add(new Label(status));//16
		labelList.add(new Label("empty"));//17
		
		return labelList;
	}
	

}
