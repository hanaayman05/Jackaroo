package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Mainboard  {
	
	
	
	 public StackPane getMainboardView() {
		 
		 StackPane stackPane = new StackPane();
		 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainboard.fxml"));
			 Parent root = loader.load();  // loads the FXML and builds the UI tree
			 
			 ImageView background = new ImageView("/images/mainboardbg.jpg");
		     background.setPreserveRatio(false);
		     background.setSmooth(true);
		     background.fitWidthProperty().bind(stackPane.widthProperty());
		     background.fitHeightProperty().bind(stackPane.heightProperty());
		     
		     
		     stackPane.getChildren().addAll(background, root);

			

			  
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return stackPane;
		 
	 }
	

    
   
    
    	

    
}