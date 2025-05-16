package controller;

import view.CardView;
import view.CardsView;
import view.Mainboard;
import view.StartView;
import view.TrackCellView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
	     
	     Mainboard mainboardView = new Mainboard();
	     StackPane layout = mainboardView.getMainboardView();
	     Scene scene2 = new Scene(layout);
	     
	     startView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 
	    	 public void handle(ActionEvent  event )
	    	 {
	    		 
	    		 primaryStage.setScene(scene2); 
	    		 primaryStage.setMaximized(true);
	    	 }
	    	
	    	
	    	        });
	     CardView card=new CardView("/images/10 clubs.jpg",false);
	     CardView card2=new CardView("/images/10 clubs.jpg",false);


	     CardsView cc=new CardsView();
	     cc.addCard(card);
	     cc.addCard(card2);
	     HBox root= cc.getCardsContainer();

	    // Scene scene3 = new Scene(root, 300, 300);
	  //   layout.setBottom(root);
	     
	     

	 
		
		
	}
	public static void main(String[]args)
	{
		launch(args);
	}




}