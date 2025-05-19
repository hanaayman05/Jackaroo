// In controller package
package controller;

import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;


import model.player.Marble;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MarbleInitializer {

	   private final Map<Marble, Circle> marbleMap;
	    private final boolean isHumanTurn;
	    private Consumer<Marble> onMarbleSelected;  // <--- NEW

	    public MarbleInitializer() {
	        marbleMap = new HashMap<>();
	        isHumanTurn = true;
	    }

	    public void setOnMarbleSelected(Consumer<Marble> onMarbleSelected) {
	        this.onMarbleSelected = onMarbleSelected;
	    }

	    public Map<Marble, Circle> getMarbleMap() {
	        return marbleMap;
	    }

	    public void initializeMarbles(Parent root) {
	        root.lookupAll("Circle").forEach(node -> {
	            Circle circle = (Circle) node;
	            if (!circle.getFill().equals(Color.BLACK)) {
	                Colour marbleColour = convertColorToColour((Color) circle.getFill());
	                Marble marble = new Marble(marbleColour);
	                marbleMap.put(marble, circle);
	                setupMarbleInteractions(circle, marble);
	            }
	        });
	    }

	    private void setupMarbleInteractions(Circle circle, Marble marble) {
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
	                if (onMarbleSelected != null) {
	                    onMarbleSelected.accept(marble);  // Trigger logic in PlayerInteractionController
	                }
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
}
