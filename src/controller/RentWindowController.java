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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.InvalidDateFormatException;
import model.InvalidIdException;
import model.InvalidInputException;
import model.RentException;
import model.ThriftyRentSystem;

/*

 * 
 *	RentWindowController is the controller associated with RentWindowView.
 *	
 *	All functionalities of the RentWindowView is handled
 *	here in this class.
 
 */
public class RentWindowController implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	RentWindowView.fxml file. No new GUI component is 
	 * created in this class.
	 */
	
	
	// a reference to TextField created in the RentWindowView.fxml file to contain customerId
	@FXML
	private TextField rentWindowCustomerIdField;
	
	
	// a reference to Text Field created in the RentWindowView.fxml file to contains days
	@FXML 
	private TextField rentWindowDaysField;
	// a reference to date field created in the .fxml file
	@FXML 
	private DatePicker rentWindowRentDateField;
	// the vehicle id  which needs to be rented
	private String vehicleId;
	// the model of the ThriftyRentSystem
	private static ThriftyRentSystem trs;
	
	//Initializing the date format for the DatePicker to dd/mm/yyyy
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	/*
	 * The Constructor of the RentWindowController initializes the vehicle Id 
	 * and the rent window model.
	 */
	public RentWindowController(String vehicleId) {
		this.vehicleId = vehicleId;
		trs = ThriftyRentSystem.getModel();
	}
	
	

	/*
	 * The initialize method initializes the RentWindow. For this method
	 * I have the changed the date pattern for the the DatePicker method.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rentWindowRentDateField.setConverter(new StringConverter<LocalDate>(){
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
	
	
	/*
	 * This is is the controller function to handle Cancel Click Action in the Rent Window
	 */
	public void handleRentWindowCancelClick(ActionEvent e){
		Node eNode = (Node) e.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		window.close();
	}
	
	
	/*
	 * This is is the controller function to handle OK Click Action in the Rent Window
	 */
	public void handleRentWindowOKClick(ActionEvent event){
		Node eNode = (Node) event.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		initialize(null,null);
		String inputCustomerID = rentWindowCustomerIdField.getText().trim();
		String inputDays = rentWindowDaysField.getText().trim();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		try {
			if(inputCustomerID.length()==0 || inputDays.length()==0 ||
					rentWindowRentDateField.getValue()==null) {
				throw new InvalidInputException("Error: Fields can not be empty.");
			}
			
				
	        String rentDate = rentWindowRentDateField.getEditor().getText();
	  
			trs.rentVehicle(vehicleId,inputCustomerID,rentDate,inputDays);
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
		catch (RentException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		} catch (InvalidDateFormatException e) {
			alert.setContentText(e.getMessage());
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		
		
		
	}
	
	
}
