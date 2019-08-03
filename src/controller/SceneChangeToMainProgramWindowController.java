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
import javafx.stage.Stage;
import view.MainProgramWindowView;


/*
 * SceneChangeToMainProgramWindowController class is used to change
 * the scene from View Details Window to Main Program Window
 */
public class SceneChangeToMainProgramWindowController implements EventHandler<ActionEvent> {

	@Override

	/*
	 * The handle method changes the scene from
	 * View Details Window to Main Program Window
	 */
	public void handle(ActionEvent event) {
		Node eNode = (Node) event.getSource();
		Stage window = (Stage)eNode.getScene().getWindow();
		MainProgramWindowView mainProgramWindowView = new MainProgramWindowView();
		mainProgramWindowView.loadWindow(window);
	}

}
