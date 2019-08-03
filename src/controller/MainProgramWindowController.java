/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.FileOperations;
import model.ThriftyRentSystem;
import model.Van;
import model.Vehicle;
import model.database.DatabaseOperations;
import view.AddCarWindowView;
import view.AddVanWindowView;
import view.MainProgramWindowView;


/*
 * MainProgramWindowController is the controller associated with MainProgramWindowView.
 * All functionalities of MainProgramWindow is handled here.
 */
public class MainProgramWindowController implements Initializable{
	/*
	 *  P.S. These are references of the layout in the 
	 * 	MainProgramWindowView.fxml file. No new GUI component is 
	 * created in this class.
	 */
	
	
	//the reference layout hold vehicles GUI components
	@FXML
	ListView<HBox> mainWindowListView;
	//the reference of drop down menu for type
	@FXML
	ComboBox<String> dropDownType;
	// the reference of drop down menu for seat
	@FXML
	ComboBox<String> dropDownSeat;
	// the reference of drop down menu for status
	@FXML
	ComboBox<String> dropDownStatus;
	// the reference of drop down menu for make
	@FXML
	ComboBox<String> dropDownMake;
	//the  refernce layout which holds vehicles details
	@FXML
	GridPane mainWindowGridPane;
	
	
	private static ThriftyRentSystem trs;//trs is the model of ThriftyRentSystem
	
	private static String selectedType;//contains the selected type
	private static String selectedSeat;//contains the selected seat
	private static String selectedStatus;//contains the selected status
	private static String selectedMake;// contains selected make
	
	private static ArrayList<Boolean> marked;// An array list to filter out the search results
	private static ObservableList<HBox> guiListItems;// contains the list of gui vehicle image,
													//details and buttons
	
	
	/*
	 * The function initialize is used to initialize all the variables in the main program
	 * window. This includes the dropdown menus
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trs = ThriftyRentSystem.getModel();
		
		guiListItems = FXCollections.observableArrayList();
		ObservableSet<String> type = FXCollections.observableSet("All");
		ObservableList<String> seat = FXCollections.observableArrayList("All");
		ObservableSet<String> status = FXCollections.observableSet("All");
		ObservableSet<String> make = FXCollections.observableSet("All");
		marked = new ArrayList<Boolean>();
		 
		dropDownType.setPromptText("Choose Filter");
		dropDownSeat.setPromptText("Choose Filter");
		dropDownStatus.setPromptText("Choose Filter");
		dropDownMake.setPromptText("Choose Filter");
		
		for(Vehicle vehicle:trs.getVehiclesList()) {
			type.add(vehicle.getType());
			Integer seatIntegerBox = vehicle.getNumberOfSeats();
			if(!seat.contains(seatIntegerBox.toString())) {
				seat.add(seatIntegerBox.toString().trim());
			}
			make.add(vehicle.getMake());
			status.add(vehicle.getStatus());
		}
		
		dropDownType.getItems().addAll(type);
		dropDownSeat.getItems().addAll(seat);
		dropDownStatus.getItems().addAll(status);
		dropDownMake.getItems().addAll(make);
		
		marked.clear();
		for(int i = marked.size();i<trs.getVehiclesSize();i++) {
			marked.add(true);
		}
		
		displayList();
	}
	
	
	/*
	 * The method handleImportDataFromFileClick is used to import data 
	 * from a text file and load the data into the model.
	 */
	public void handleImportDataFromFileClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		trs.getVehiclesList().clear();
		FileChooser fc = new FileChooser();
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\files";
		
		fc.setInitialDirectory(new File(currentDirectory));
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile!=null) {
			String path=selectedFile.getAbsolutePath();
			try {	
				FileOperations.importFromFile(trs,path);
				displayList();
			} 
			catch (FileNotFoundException e) {
				alert.setContentText("Error: No Such File Found");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
		else {
			alert.setContentText("Error: File Not Valid");
			alert.setHeaderText(null);
			alert.showAndWait();
			
		}
		
		
		
	}
	
	

	/*
	 * The method handleExportDataFromFileClick is used to export data 
	 * from the model into a text file.
	 */

	public void handleExportDataToFileClick(){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\files";
		
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setInitialDirectory(new File(currentDirectory));
		Stage stage = ( Stage) mainWindowGridPane.getScene().getWindow(); 
		File file = dirChooser.showDialog(stage);
		
		if(file!=null) {
			
			String path=file.getAbsolutePath()+"\\export_data.txt";
			
			try {
				FileOperations.exportToFile(trs,path);
			}
			catch (IOException e) {
				alert.setContentText("Error: File cannot be created, or cannot be opened");
				alert.setHeaderText(null);
				alert.showAndWait();
			
			}
			catch (Exception e) {
					alert.setContentText("Error: File not valid");
					alert.setHeaderText(null);
					alert.showAndWait();
			}
		}
	}
	
	
	
	/*
	 * The method handleSaveToDatabaseClick is used to save the data into
	 * the database.
	 */
	public void handleSaveToDatabaseClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			DatabaseOperations.writeToDatabase(trs);
		}
		catch (Exception e){
			alert.setContentText("Error: Could not Write to Database");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}
	
	

	/*
	 * The method handleQuitProgramClick is used to quit the program 
	 * and save the vehicle information and rental record information into 
	 * the database
	 */
	public void handleQuitProgramClick() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Alert");
		try {
			DatabaseOperations.writeToDatabase(trs);
		}
		catch (Exception e){
			alert.setContentText("Error: Could not Write to Database");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		System.exit(0);
	}
	
	
	/*
	 * The method handleAddCarClick is used to add a car using the menu bar
	 * option
	 */
	public void handleAddCarClick(ActionEvent e){
		AddCarWindowView view = new AddCarWindowView();
		view.loadWindow();
		displayList();	
	}
	
	

	/*
	 * The method handleAddVanClick is used to add a van using the menu bar
	 * option
	 */
	public void handleAddVanClick()  {
		AddVanWindowView view = new AddVanWindowView();
		view.loadWindow();
		displayList();	
	}
	

	/*
	 * The method handleFilterType is used to filter the search by type
	 */
	public void handleFilterType() {
		selectedType = dropDownType.getValue();
	}
	

	/*
	 * The method handleFilterSeat is used to filter the search by seat
	 */
	public void handleFilterSeat() {
		selectedSeat = dropDownSeat.getValue();	
	}
	

	/*
	 * The method handleFilterMake is used to filter the search by make.
	 */
	public void handleFIlterMake() {
		selectedMake = dropDownMake.getValue();
	}
	

	/*
	 * The method handleFilterStatus is used to filter the search by make.
	 */
	public void handleFilterStatus() {
		selectedStatus = dropDownStatus.getValue();
	}
	
	/*
	 * The method handleFilterSearchClick performs the filtering of the vehicles list
	 * based on the dropdown menus selected.
	 */
	public void handleFilterSearchClick() {		
		boolean allType = selectedType==null || selectedType.equals("All");
		boolean allMake = selectedMake==null || selectedMake.equals("All");
		boolean allSeat = selectedSeat==null || selectedSeat.equals("All");
		boolean allStatus = selectedStatus==null || selectedStatus.equals("All");
		
		
		marked.clear();
		for(int i = marked.size();i<trs.getVehiclesSize();i++) {
			marked.add(true);
		}

		for(int index =0;index<trs.getVehiclesSize();index++) {
				marked.set(index,true);
		}

		//filter by type
		if(!allType){
			for(int index =0;index<trs.getVehiclesSize();index++) {
				Vehicle vehicle = trs.getVehiclesList().get(index);
				if(!vehicle.getType().equals(selectedType)) {
					marked.set(index,false);
				}
			}
		}
		//filter by seat
		if(!allSeat) {
			
			for(int index =0;index<trs.getVehiclesSize();index++) {
				Vehicle vehicle = trs.getVehiclesList().get(index);
				Integer vehicleSeat = vehicle.getNumberOfSeats();
				if(marked.get(index) && !selectedSeat.equals(vehicleSeat.toString())) {
					marked.set(index,false);
				}
			}
		}
		//filter by status
		if(!allStatus) {
			for(int index =0;index<trs.getVehiclesSize();index++) {
				Vehicle vehicle = trs.getVehiclesList().get(index);
				if(marked.get(index) && !vehicle.getStatus().equals(selectedStatus)) {
					marked.set(index,false);
				}
			}
		}
		//filter by make
		if(!allMake) {
			for(int index =0;index<trs.getVehiclesSize();index++) {
				Vehicle vehicle = trs.getVehiclesList().get(index);
				if(marked.get(index) && !vehicle.getMake().equals(selectedMake)) {
					marked.set(index,false);
				}
			}
		}

		displayList();
	}
	 
	/*
	 * The method handleFilterClearClick is used to clear the search the results.
	 */
	public void handleFilterClearClick() {
		mainWindowListView.getItems().clear();
	}
	

	/*
	 * The method displayList is used to display all the items from ThriftyRentModel into the
	 * into the mainWindowListBiew.
	 */
	private void displayList() {
				
		guiListItems.clear();
		mainWindowListView.getItems().clear();
		
		loadVehicles();
		
		mainWindowListView.setCellFactory(new Callback<ListView<HBox>,ListCell<HBox>>() {

			@Override
			public ListCell<HBox> call(ListView<HBox> param) {
				// TODO Auto-generated method stub
				
			
				ListCell<HBox> cell = new ListCell<HBox>() {
					protected void updateItem(HBox item,boolean empty) {
						super.updateItem(item,empty);
						if(item!=null) {
							setGraphic(item);
						}
					}
				};
				return cell;
			}
		
		});
 
		mainWindowListView.setItems(guiListItems);
	}
	
	
	
	
	
	
	

	/*
	 * The loadVehicles() method loads the gui vehicle items into the list guiListItems.
	 */
	private void loadVehicles() {
		String id = "";
		String type = "";
		String make ="";
		String model="";
		String year ="";
		String numberOfSeats = "";
		String lastMaintenanceDate = "";
		String status ="";
		String imageName = "";
		
		for(int i = marked.size();i<trs.getVehiclesSize();i++) {
			marked.add(true);
		}
		
		for(int index = 0 ;index<trs.getVehiclesSize();index++) {
			Vehicle vehicle = trs.getVehiclesList().get(index);
			if(marked.get(index)) {
				id = vehicle.getId();
				type = vehicle.getType();
				make = vehicle.getMake();
				model = vehicle.getModel();
				Integer yearBox = vehicle.getYearManufactured();
				year = yearBox.toString();
				Integer seatBox = vehicle.getNumberOfSeats();
				numberOfSeats = seatBox.toString();
				if(type.equals("Van")) {
					lastMaintenanceDate = ((Van)vehicle).getLastMaintenanceDate().toString() ;
				}
				status = vehicle.getStatus();
				imageName = vehicle.getImageName();
				guiListItems.add(MainProgramWindowView.createMainWindowListItemNode
						(id,type,make,model,year,numberOfSeats,lastMaintenanceDate,
								status,imageName));
			}
		}
	}

	

}
