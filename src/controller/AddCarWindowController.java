/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package controller;



import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AddVehicleException;
import model.InvalidDateFormatException;
import model.InvalidIdException;
import model.InvalidInputException;
import model.ThriftyRentSystem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;

/*
 * AddVanWindowController is the controller associated with 
 * AddVanWindowView.
 * All functionalities of AddVanWindow is handled here.
 */

public class AddCarWindowController implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	AddCarWindow.fxml file. No new GUI component is 
	 * created in this class.
	 */
	
	
	// a reference to text field created in the AddCarWindowView.fxml file to contain id
	@FXML 
	private TextField addCarWindowIdField;
	// a reference to text field created in the AddCarWindowView.fxml file to contain make
	@FXML 
	private TextField addCarWindowMakeField;
	// a reference to text field created in the AddCarWindowView.fxml file to contain model
	@FXML 
	private TextField addCarWindowModelField;
	// a reference to text field created in the AddCarWindowView.fxml file to contain year
	@FXML 
	private TextField addCarWindowYearField;
	// a reference to text field created in the AddCarWindowView.fxml file to contain seat
	@FXML 
	private TextField addCarWindowNumberOfSeatsField;
	// the model of the ThriftyRentSystem.
	private static ThriftyRentSystem trs;
	
	/*
	 * The initialize method initializes the ThriftyRentSytem model	
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trs = ThriftyRentSystem.getModel();
	}
	
	

	/*
	 * The handleAddCarWindowOKClick adds cars to the ThriftyRentSystem using
	 * GUI.
	 *  
	 */
	public void handleAddCarWindowOKClick(ActionEvent event){
		Node eNode = (Node) event.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		String inputId = addCarWindowIdField.getText().trim();
		String inputMake = addCarWindowMakeField.getText().trim();
		String inputModel = addCarWindowModelField.getText().trim();
		String inputYearManufactured = addCarWindowYearField.getText().trim();
		String inputNumberOfSeats =addCarWindowNumberOfSeatsField.getText().trim();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			if(inputId.length()==0 || inputMake.length()==0 || inputModel.length()==0 || 
					inputYearManufactured.length()==0 || inputNumberOfSeats.length()==0) {
				throw new InvalidInputException("Error: Fields can not be empty.");
			}
			trs.addVehicle(inputId,"Car", inputYearManufactured, inputMake, inputModel, inputNumberOfSeats,"Available","");	
			window.close();
		}

		catch(InvalidInputException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		catch (InvalidIdException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		catch (AddVehicleException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		} catch (InvalidDateFormatException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
	

	/*
	 * The handleAddCarWindowCancelClick adds cars to the ThriftyRentSystem quits the
	 * Add Car Window
	 */
	public void handleAddCarWindowCancelClick(ActionEvent e){
		Node eNode = (Node) e.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		window.close();
	}


	

	
	
	
}
