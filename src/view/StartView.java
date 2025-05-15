package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartView {

    private TextField nameField = new TextField();
    private Button startButton = new Button("Start Game");
    private VBox layout;

    public StartView() {
        Label label = new Label("Enter your name:");
        label.setStyle("-fx-text-fill: red;");
        
        nameField.setMaxWidth(400);   // Set preferred width
        nameField.setPrefHeight(40); 
        layout = new VBox(10, label, nameField, startButton);
        
        layout.setAlignment(Pos.CENTER);
        

        // Set the background image for the VBox
        String Backgroundimg="file:/C:/Users/aseel/Desktop/Semster%20IV/(CSEN401)%20Computer%20Programming%20Lab/Game/Milestone%202%20Solution/JackarooM2Solution/images/Background.jpg";
        BackgroundImage backgroundImage = new BackgroundImage(
                new javafx.scene.image.Image(Backgroundimg, 800, 600, false, true),  // Path to the image
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        layout.setBackground(new Background(backgroundImage));
        
    
    	
    }
    
    public VBox getView() {
        return layout;
    }

    public Button getStartButton() {
        return startButton;
    }

    public String getPlayerName() {
        return nameField.getText();
    }

}
