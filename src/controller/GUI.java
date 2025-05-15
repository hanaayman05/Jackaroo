package controller;

import view.StartView;
import view.TrackCellView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application
{
	@Override
	public void start(Stage primaryStage) {
		
	     StartView startView = new StartView();
	     Scene scene = new Scene(startView.getView(), 800, 600);  // Adjust size as needed
	     primaryStage.setScene(scene);
	     primaryStage.setTitle("Jackaroo - Start");
	     primaryStage.show();
	     
	     TrackCellView track=new TrackCellView();
	     Scene scene2=new Scene(track.getTrack(),800,800);
	     
	     startView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 
	    	 public void handle(ActionEvent  event )
	    	 {
	    		 primaryStage.setScene(scene2); 
	    	 }
	    	
	    	
	    	        });
		
		
	}
	public static void main(String[]args)
	{
		launch(args);
	}




}
