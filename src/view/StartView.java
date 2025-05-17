package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartView {

    private TextField nameField = new TextField();
    private Button startButton = new Button("Start Game");
    private VBox layout;
    
    private RadioButton maleButton = new RadioButton("Male");
    private RadioButton femaleButton = new RadioButton("Female");
    private ToggleGroup genderGroup = new ToggleGroup();

    public StartView() {
        Label nameLabel = new Label("Enter your name:");
        nameLabel.setStyle("-fx-text-fill: red;");

        nameField.setMaxWidth(400);
        nameField.setPrefHeight(40);

        // Gender selection
        Label genderLabel = new Label("Select Gender:");
        genderLabel.setStyle("-fx-text-fill: red;");

        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        // Wrap radio buttons in HBox
        HBox genderButtonsBox = new HBox(10, maleButton, femaleButton);
        genderButtonsBox.setAlignment(Pos.CENTER);

        VBox genderBox = new VBox(5, genderLabel, genderButtonsBox);
        genderBox.setAlignment(Pos.CENTER);

        layout = new VBox(15, nameLabel, nameField, genderBox, startButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Set the background image for the VBox
        String backgroundImg = "/images/startbg.jpeg";
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(backgroundImg, 800, 600, false, true),
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
    
    public String getSelectedGender() {
        if (genderGroup.getSelectedToggle() == maleButton) {
            return "Male";
        } else if (genderGroup.getSelectedToggle() == femaleButton) {
            return "Female";
        } else {
            return null; // or "Unspecified"
        }
    }
    
    

}
