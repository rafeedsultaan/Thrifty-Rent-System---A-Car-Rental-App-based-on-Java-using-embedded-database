/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;


/* 
 * AddCarWindowView  class contains the AddCarWindowView 
 * which is contained inside the AddCarWindowView.fxml file
 * AddCarWindowView.fxml file contains the static GUI view of
 * 
 * AddCarWindowController is the controller associated with this view
 */ 
public class AddCarWindowView {
	/*
	 * The method loadWindow loads the AddCarWindowView.
	 * AddCarWindowView.fxml contains AddCarWindowView Layout
	 */
	public void loadWindow() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/AddCarWindowView.fxml"));
			Parent root = loader.load();		
			Scene scene = new Scene(root); // temporary file
		
			Stage dialogBoxCar = new Stage();
			dialogBoxCar.setTitle("Add Car Window"); // Set the stage title
			dialogBoxCar.setScene(scene); // Place the scene in the stage	
			dialogBoxCar.initModality(Modality.APPLICATION_MODAL); 
			dialogBoxCar.showAndWait();
		}
		catch (IOException e1) {
			alert.setContentText("Error: Could not load Add Car Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
}
