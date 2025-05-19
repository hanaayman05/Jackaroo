package controller;

import view.CardView;
import view.CardsView;
import view.Mainboard;
import view.StartView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.player.Marble;
import model.Colour;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import engine.Game;

public class GUI extends Application {
    private Map<Marble, Circle> marbleMap = new HashMap<>();
    private boolean isHumanTurn = true;
    
    @FXML
    private ImageView playerImageView;

    @Override
    public void start(Stage primaryStage) {
        
        StartView startView = new StartView();
        Scene startScene = new Scene(startView.getView(), 800, 600);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Jackaroo - Start");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        GameController gameController = new GameController();

        startView.getStartButton().setOnAction(event -> {
           
            gameController.initializeGame();

           
            StackPane layout = gameController.getLayout();

            Scene gameScene = new Scene(layout);
            primaryStage.setScene(gameScene);
            
        });
        
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}