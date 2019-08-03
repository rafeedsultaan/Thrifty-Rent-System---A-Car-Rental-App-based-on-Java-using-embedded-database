/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.io.IOException;

import controller.CompleteMaintenanceWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* 
 * CompleteMaintenanceWindowView class contains the CompleteMaintenanceWindowView
 * which is contained inside the CompleteMaintenanceWindowView.fxml file
 * CompleteMaintenanceWindowView.fxml file contains the static GUI view of
 * the CompleteMaintenanceWindowView Window
 * CompleteMaintenanceWindowController is the controller associated with this view
 */
public class CompleteMaintenanceWindowView {
	/*
	 * The method loadWindow loads the RentWindowView.
	 * RentWindowView.fxml contains RentWindowView Layout
	 */
	public void loadWindow(String vehicleId) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/CompleteMaintenanceWindowView.fxml"));
			loader.setController(new CompleteMaintenanceWindowController(vehicleId));
			Parent root = loader.load();
			Scene scene = new Scene(root); 
			
			Stage dialogBoxReturn = new Stage();
			dialogBoxReturn.setTitle("Return Vehicle Window"); 
			dialogBoxReturn.setScene(scene);
			dialogBoxReturn.initModality(Modality.APPLICATION_MODAL); 
			dialogBoxReturn.showAndWait();
		}
		catch(IOException e) {
			alert.setContentText("Error: Could not load Complete Maintenance Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
}
