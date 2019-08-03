/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.io.IOException;

import controller.ReturnWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* 
 * ReturnWindowView class contains the ReturnWindowViewView
 * which is contained inside the ReturnWindowView.fxml file
 * ReturnWindowView.fxml file contains the static GUI view of
 * the Return Window
 * ReturnWindowController is the controller associated with this view
 */
public class ReturnWindowView {
	/*
	 * The method loadWindow loads the ReturnWindowView.
	 * ReturnWindowView.fxml contains ReturnWindowView Layout
	 */
	public void loadWindow(String vehicleId) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ReturnWindowView.fxml"));
			loader.setController(new ReturnWindowController(vehicleId));
			Parent root = loader.load();
			Scene scene = new Scene(root); // temporary file

			Stage dialogBoxReturn = new Stage();
			dialogBoxReturn.setTitle("Return Vehicle Window"); // Set the stage title
			dialogBoxReturn.setScene(scene); // Place the scene in the stage
			dialogBoxReturn.initModality(Modality.APPLICATION_MODAL); 
			dialogBoxReturn.showAndWait();
			
		}
		catch(IOException e) {
			alert.setContentText("Error: Could not load Rent Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
}
