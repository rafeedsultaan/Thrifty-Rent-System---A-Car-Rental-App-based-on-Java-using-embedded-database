/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.io.IOException;

import controller.RentWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* 
 * RentWindowView class contains the RentWindowView
 * which is contained inside the RentWindowView.fxml file
 * RentWindowView.fxml file contains the static GUI view of
 * the RentWindowView Window
 * RentWindowController is the controller associated with this view
 */
public class RentWindowView {
	/*
	 * The method loadWindow loads the RentWindowView.
	 * RentWindowView.fxml contains RentWindowView Layout
	 */
	public void loadWindow(String vehicleId) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RentWindowView.fxml"));
			loader.setController(new RentWindowController(vehicleId));
			Parent root = loader.load();
			Scene scene = new Scene(root); 
			Stage dialogBoxRent = new Stage();
			dialogBoxRent.setTitle("Rent Vehicle Window"); // Set the stage title
			dialogBoxRent.setScene(scene); // Place the scene in the stage
			dialogBoxRent.initModality(Modality.APPLICATION_MODAL); 
			dialogBoxRent.showAndWait();
		}
		catch(IOException e) {
			alert.setContentText("Error: Could not load Rent Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
}
