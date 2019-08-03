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
 * AddVanWindowView  class contains the AddVanWindowView 
 * which is contained inside the AddVanWindowView.fxml file
 * AddVanWindowView.fxml file contains the static GUI view of
 * 
 * AddVanWindowController is the controller associated with this view
 */ 
public class AddVanWindowView {
	/*
	 * The method loadWindow loads the AddVanWindowView.
	 * AddVanWindowView.fxml contains AddVanWindowView Layout
	 */
	public void loadWindow() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/AddVanWindowView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root); 
			Stage dialogBoxVan = new Stage();
			dialogBoxVan.setTitle("Add Van Window"); // Set the stage title
			dialogBoxVan.setScene(scene); // Place the scene in the stage
			dialogBoxVan.initModality(Modality.APPLICATION_MODAL); 
			dialogBoxVan.showAndWait();
		}
		catch(IOException exp) {
			alert.setContentText("Error: Could not load Add Van Window");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
}
