package controller;

import view.CardView;
import view.CardsView;
import view.Mainboard;
import view.StartView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.player.Marble;
import model.Colour;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Glow;
import java.util.HashMap;
import java.util.Map;

public class GUI extends Application {
    private Map<Marble, Circle> marbleMap = new HashMap<>();
    private boolean isHumanTurn = true;

    @Override
    public void start(Stage primaryStage) {
        
        StartView startView = new StartView();
        Scene startScene = new Scene(startView.getView(), 800, 600);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Jackaroo - Start");
        primaryStage.show();

        
        Mainboard mainboardView = new Mainboard();
        StackPane layout = mainboardView.getMainboardView();
        Scene gameScene = new Scene(layout);
        
        
        startView.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(gameScene);
                primaryStage.setMaximized(true);
                initializeMarbles(layout); 
            }
        });

        
        CardView card = new CardView("/images/10 clubs.jpg", false);
        CardView card2 = new CardView("/images/10 clubs.jpg", false);
        CardsView cc = new CardsView();
        cc.addCard(card);
        cc.addCard(card2);
        HBox root = cc.getCardsContainer();
    }

    private void initializeMarbles(Parent root) {
        
        root.lookupAll("Circle").forEach(node -> {
            Circle circle = (Circle) node;
            if (!circle.getFill().equals(Color.BLACK)) {
                
                Colour marbleColour = convertColorToColour((Color) circle.getFill());
                Marble marble = new Marble(marbleColour);
                
                
                marbleMap.put(marble, circle);
                
              
                setupMarbleInteractions(circle);
            }
        });
    }

    private Colour convertColorToColour(Color color) {
        if (color.equals(Color.DODGERBLUE)) return Colour.BLUE;
        if (color.equals(Color.web("#730606"))) return Colour.RED;
        if (color.equals(Color.web("#257b24"))) return Colour.GREEN;
        if (color.equals(Color.web("#ffaf0f"))) return Colour.YELLOW;
        throw new IllegalArgumentException("Unknown marble color: " + color);
    }

    private void setupMarbleInteractions(Circle circle) {
        
        circle.setOnMouseEntered(e -> {
            if (isHumanTurn) {
                Glow glow = new Glow(0.7);
                glow.setInput(new javafx.scene.effect.DropShadow(10, Color.WHITE));
                circle.setEffect(glow);
            }
        });

        circle.setOnMouseExited(e -> {
            if (!isSelected(circle)) {
                circle.setEffect(null);
            }
        });

        
        circle.setOnMouseClicked(e -> {
            if (isHumanTurn) {
                toggleMarbleSelection(circle);
            }
        });
    }

    private boolean isSelected(Circle circle) {
        return circle.getStroke() != null && circle.getStroke().equals(Color.GOLD);
    }

    private void toggleMarbleSelection(Circle circle) {
        if (isSelected(circle)) {
            
            circle.setStroke(null);
            circle.setEffect(null);
        } else {
            
            circle.setStroke(Color.GOLD);
            circle.setStrokeWidth(2.0);
            
            
            Glow glow = new Glow(0.5);
            glow.setInput(new javafx.scene.effect.DropShadow(10, Color.WHITE));
            circle.setEffect(glow);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}