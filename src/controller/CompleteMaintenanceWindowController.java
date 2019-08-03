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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.InvalidDateFormatException;
import model.InvalidIdException;
import model.InvalidInputException;
import model.MaintenanceException;
import model.ThriftyRentSystem;



/*
 * CompleteMaintenanceWindowController is the controller associated with 
 * CompleteMaintenanceWindowView.
 * All functionalities of MainProgramWindow is handled here.
 */
public class CompleteMaintenanceWindowController  implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	CompleteMaintenanceWindow.fxml file. No new GUI component is 
	 * created in this class.
	 */
	
	// a reference to date field create in the CompleteMaintenanceWindowView.fxml file
	@FXML 
	DatePicker completeMaintenanceDateField;
	
	// the model of the ThriftyRentSystem
	private static ThriftyRentSystem trs;
	
	// the vehicle id whose maintenance needs to be done
	private String vehicleId;
	
	//Initializing the date format for the DatePicker to dd/mm/yyyy
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	/*
	 * Constructor initializes the vehicle id that needs to maintained.
	 *	Initializes the  the model.
	 */	
	public CompleteMaintenanceWindowController(String vehicleId) {
		this.vehicleId = vehicleId;
		trs = ThriftyRentSystem.getModel();
	}
	


	/*
	 * The initialize method is used to set and display the datepicker field
	 * in the format dd/mm/yyyy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		completeMaintenanceDateField.setConverter(new StringConverter<LocalDate>(){
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
	 * The method handleCompleteMaintenanceWindowOKClick tries to complete the
	 * maintenance of a vehicle. If it is successful, it changes the status
	 * of the vehicle to available
	 */
	public void handleCompleteMaintenanceWindowOKClick(ActionEvent event){
		Node eNode = (Node) event.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		initialize(null,null);
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		
		try {
			if(completeMaintenanceDateField.getValue()==null) {
				throw new InvalidInputException("Error: Field can not be empty.");
			}
	
	        String completeMaintenanceDate = completeMaintenanceDateField.getEditor().getText();
	        trs.completeMaintenance(vehicleId,completeMaintenanceDate);
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
		catch (MaintenanceException e) {
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
	 * The method handleCompleteMaintenanceWindowCancelClick tries to complete the
	 * quits the complete maintenance window.
	 */
	public void handleCompleteMaintenanceWindowCancelClick(ActionEvent e) {
		Node eNode = (Node) e.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		window.close();
	}
}
