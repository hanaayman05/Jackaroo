package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Mainboard extends Application {

    @Override
    public void start(Stage primaryStage) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("Mainboard.fxml"));
    		Scene scene = new Scene(root, 3200, 1900);

    		primaryStage.setTitle("JavaFX Application");
    		primaryStage.setScene(scene);
    		//primaryStage.setResizable(false);
    		primaryStage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void main(String[] args) {
        launch(args);
    }
}