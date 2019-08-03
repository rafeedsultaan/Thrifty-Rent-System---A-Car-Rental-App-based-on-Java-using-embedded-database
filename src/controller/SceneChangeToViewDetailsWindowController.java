/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewDetailsWindowView;




/*
 * SceneChangeToViewDetailsWindowController class is used to
 * change  the scene from Main Program Window to View Details Window
 */
public class SceneChangeToViewDetailsWindowController implements EventHandler<ActionEvent>{
	
	

/*
 * The handle method changes the scene from
 * Main Program Window to View Details Window
 */
	@Override
	public void handle(ActionEvent e) {

		String vehicleId = ((Button) e.getSource()).getId();
		
		Node eNode = (Node) e.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		ViewDetailsWindowView view = new ViewDetailsWindowView();
		view.loadWindow(window,vehicleId);
		
		

	}

}
