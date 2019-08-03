/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package view;

import java.io.File;
import java.io.IOException;

import controller.SceneChangeToViewDetailsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/* 
 * MainProgramWindowView class contains the MainProgramWindowView
 * which is contained inside the MainProgramWindowView.fxml file
 * MainProgramWindowView.fxml file contains the static GUI view of
 * 
 * MainProgramWindowController is the controller associated with this view
 */ 
public class MainProgramWindowView {
	/*
	 * The method loadWindow loads the MainProgramWindowView.
	 * MainProgramWindowView.fxml contains MainProgramWindowView Layout
	 */
	public void loadWindow(Stage window) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Alert");
				try {

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/MainProgramWindowView.fxml"));
					Parent root= loader.load();
					Scene mainProgramWindow = new Scene(root);
					
					window.setTitle("Thrifty Rent System");
					window.setScene(mainProgramWindow);
					window.show();
					
				} catch (IOException e1) {
					alert.setContentText("Error: Could not load Main Program Window");
					alert.setHeaderText(null);
					alert.showAndWait();
				}
	}
	
	
	
	/*
	 * The method createMainWindowListItemNode creates dynamic list item nodes of vehicle
	 * object based on the number of vehicles inside the Thrifty Rent System.
	 */
	public static HBox createMainWindowListItemNode(String id,String type,String make,
			String model,String year,String numberOfSeats,String lastMaintenanceDate,
			String status,String imageName) {					
		HBox hContainer = new HBox();
		VBox vContainer1 = createMainWindowListItemNodeImageComponent(imageName);
		VBox vContainer2 = createMainWindowListItemNodeDetailComponent(id,type,make,
				model,year,numberOfSeats,lastMaintenanceDate,status);
		VBox vContainer3 = createMainWindowListItemNodeButtonComponent(id);		
		
		hContainer.getChildren().add(vContainer1);
		hContainer.getChildren().add(vContainer2);
		hContainer.getChildren().add(vContainer3);
		hContainer.setSpacing(10);
		return hContainer;
	}

	/*
	 * The method createMainWindowListItemNodeImageComponent creates dynamic image component of the vehicle
	 * object based on the number of vehicles inside the Thrifty Rent System.
	 */
	private static VBox createMainWindowListItemNodeImageComponent(String imageName) {
		VBox imageContainer = new VBox();
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory+="\\images\\"+imageName;
		Image image =  new Image(new File(currentDirectory).toURI().toString());
		ImageView imageview = new ImageView(image);
		imageview.setFitHeight(135);
		imageview.setFitWidth(150);
		imageContainer.getChildren().add(imageview);
		return imageContainer;
	}
	

	/*
	 * The method createMainWindowListItemNodeImageComponent creates dynamic button component of the vehicle
	 * object based on the number of vehicles inside the Thrifty Rent System.
	 */
	private static VBox createMainWindowListItemNodeButtonComponent(String id) {
		VBox buttonContainer = new VBox();
		Button viewDetailsButton = new Button("View Details");
		viewDetailsButton.setAlignment(Pos.CENTER);
		viewDetailsButton.setId(id);
		viewDetailsButton.setOnAction(new SceneChangeToViewDetailsWindowController());
		buttonContainer.getChildren().add(viewDetailsButton);			
		return buttonContainer;
	}
	
	

	/*
	 * The method createMainWindowListItemNodeDetailComponen creates the GUI Details component of the vehicle
	 * object based on the number of vehicles inside the Thrifty Rent System.
	 */
	private static VBox createMainWindowListItemNodeDetailComponent(
			String id,String type,String make,
			String model,String year,String numberOfSeats,String lastMaintenanceDate,
			String status) {
		VBox detailContainer = new VBox();

		GridPane grid = new GridPane();
		grid.add(new Label("ID:\t\t"),0,0);
		grid.add(new Label("Type:\t\t"),0, 1);
		grid.add(new Label("Make:\t\t"),0,2);
		grid.add(new Label("Model:\t\t"),0, 3);
		grid.add(new Label("Year:\t\t"),0, 4);
		grid.add(new Label("Number of seats:\t\t"),0, 5);		
		grid.add(new Label(id),1,0);
		grid.add(new Label(type),1, 1);
		grid.add(new Label(make),1,2);
		grid.add(new Label(model),1, 3);
		grid.add(new Label(year),1, 4);
		grid.add(new Label(numberOfSeats),1, 5);
		
		if(type.equals("Van")) {
			grid.add(new Label("Last Maintenance Date:"),0, 6);
			grid.add(new Label("Status:\t\t"),0, 7);	
			grid.add(new Label(lastMaintenanceDate),1, 6);
			grid.add(new Label(status),1,7);
		}
		else {
			grid.add(new Label("Status\t\t"),0,6);
			grid.add(new Label(status),1,6);
		}
		detailContainer.getChildren().add(grid);
		detailContainer.setMaxWidth(400);
		detailContainer.setMinWidth(400);
		return detailContainer;
	}

}
