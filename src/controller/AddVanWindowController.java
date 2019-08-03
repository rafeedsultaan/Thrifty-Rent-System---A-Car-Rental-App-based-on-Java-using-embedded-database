/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.util.StringConverter;
import model.AddVehicleException;
import model.InvalidDateFormatException;
import model.InvalidIdException;
import model.InvalidInputException;
import model.ThriftyRentSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * AddVanWindowController is the controller associated with 
 * AddVanWindowView.
 * All functionalities of AddVanWindow is handled here.
 */
public class AddVanWindowController implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	AddVanWindow.fxml file. No new GUI component is 
	 * created in this class.
	 */
	
	
	// a reference to text field created in the AddVanWindowView.fxml file to contain id
	@FXML 
	private TextField addVanWindowIdField;
	// a reference to text field created in the AddVanWindowView.fxml file to contain make
	@FXML 
	private TextField addVanWindowMakeField;
	// a reference to text field created in the AddVanWindowView.fxml file to contain make
	@FXML 
	private TextField addVanWindowModelField;
	// a reference to text field created in the AddVanWindowView.fxml file to contain year
	@FXML 
	private TextField addVanWindowYearField;
	// a reference to text field created in the AddVanWindowView.fxml file to contain customerId
	@FXML 
	private TextField addVanWindowNumberOfSeatsField;
	// a reference to last maintenance date field created in the AddVanWindowView.fxml file
	@FXML 
	private DatePicker addVanWindowLastMaintenanceDateField;;

	//Initializing the date format for the DatePicker to dd/mm/yyyy
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	// the model of the thrifty rent system.
	private static ThriftyRentSystem trs;
	
	
	
	
	/*
	 * The handleAddVanWindowOKClick adds vans to the ThriftyRentSystem using
	 * GUI. 
	 */
	public void handleAddVanWindowOKClick(ActionEvent event){
		Node eNode = (Node) event.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		

		
		String inputId = addVanWindowIdField.getText().trim();
		String inputMake = addVanWindowMakeField.getText().trim();
		String inputModel = addVanWindowModelField.getText().trim();
		String inputYearManufactured = addVanWindowYearField.getText().trim();
		String inputNumberOfSeats =addVanWindowNumberOfSeatsField.getText().trim();
		
        Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			if(inputId.length()==0 || inputMake.length()==0 || inputModel.length()==0 ||
					inputYearManufactured.length()==0 || inputNumberOfSeats.length()==0
					||addVanWindowLastMaintenanceDateField.getValue()==null) {
				throw new InvalidInputException("Errors: Fields can not be empty.");
			}

	        String lastMaintenanceDate = addVanWindowLastMaintenanceDateField.getEditor().getText();
			
			trs.addVehicle(inputId,"Van", inputYearManufactured, inputMake, inputModel,
					inputNumberOfSeats,"Available",lastMaintenanceDate);
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
	 * Add Van Window
	 *  
	 */
	public void handleAddVanWindowCancelClick(ActionEvent e){
		Node eNode = (Node) e.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		window.close();
	}


	/*
	 * The initialize method initializes the Add Car Window. For this method
	 * I have the changed the date pattern for the the DatePicker method.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trs = ThriftyRentSystem.getModel();
		addVanWindowLastMaintenanceDateField.setConverter(new StringConverter<LocalDate>(){
			@Override
			public LocalDate fromString(String string) {
				if(string!=null && !string.trim().isEmpty()) {
					return LocalDate.parse(string,formatter);
				}
				return null;
			}

			@Override
			public String toString(LocalDate t) {
				if(t!=null) {
					return formatter.format(t);
				}
				return null;
				
			}
		});
		
	}
	

}
