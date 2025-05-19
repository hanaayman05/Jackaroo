package controller;

import engine.GameManager;
import engine.board.BoardManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
//import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Colour;
import model.player.Marble;

import java.util.List;
import java.util.Map;

public class MarbleController {

    private final StackPane mainboardLayout;
    private final GameManager gameManager;
    private final BoardManager boardManager;
    private final Map<Marble, Circle> marbleCircleMap;
    private final Map<String, Circle> trackCellMap;

    public MarbleController(StackPane mainboardLayout, GameManager gameManager, BoardManager boardManager,
                            Map<Marble, Circle> marbleCircleMap, Map<String, Circle> trackCellMap) {
        this.mainboardLayout = mainboardLayout;
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.marbleCircleMap = marbleCircleMap;
        this.trackCellMap = trackCellMap;
    }

    public void sendToHome(Marble marble) {
        try {
            gameManager.sendHome(marble);
            moveToHomeUI(marble);
        } catch (Exception e) {
       //     showAlert("Error", "Failed to send marble home: " + e.getMessage());
        }
    }

    public void fieldMarble(Marble marble) {
        try {
            gameManager.fieldMarble(); // Or gameManager.fieldMarble(marble); depending on your logic
            moveToTrackStartUI(marble);
        } catch (Exception e) {
        //    showAlert("Error", "Failed to field marble: " + e.getMessage());
        }
    }

    public void moveMarbleAlongTrack(Marble marble, List<String> path) {
        if (path == null || path.isEmpty()) return;

        Circle marbleCircle = marbleCircleMap.get(marble);
        if (marbleCircle == null) {
         //   showAlert("Error", "No UI Circle found for selected marble.");
            return;
        }

        try {
            // Do the model move once
            boardManager.moveBy(marble, path.size(), false);
        } catch (Exception ex) {
        //    showAlert("Invalid Move", ex.getMessage());
            return;
        }

        Timeline timeline = new Timeline();
        int totalSteps = path.size();

        for (int i = 0; i < totalSteps; i++) {
            final int step = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300 * (step + 1)), e -> {
                String cellId = path.get(step);
                Circle cellCircle = trackCellMap.get(cellId);
                if (cellCircle != null) {
                    marbleCircle.setLayoutX(cellCircle.getLayoutX());
                    marbleCircle.setLayoutY(cellCircle.getLayoutY());
                }
            });

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();
    }

    private void moveToHomeUI(Marble marble) {
        Circle marbleCircle = marbleCircleMap.get(marble);
        if (marbleCircle != null) {
        	
        	String colorPrefix = marble.getColour().name().toLowerCase(); //  "red"
        	Circle targetCell = null;

        	for (int i = 0; i < 4; i++) {
        	    String fxId = "#" + colorPrefix + "_" + i;
        	    Circle cell = (Circle) mainboardLayout.lookup(fxId);

        	    boolean isOccupied = false;
        	    for (Map.Entry<Marble, Circle> entry : marbleCircleMap.entrySet()) {
        	        Circle otherCircle = entry.getValue();
        	        if (otherCircle.getLayoutX() == cell.getLayoutX() && otherCircle.getLayoutY() == cell.getLayoutY()) {
        	            isOccupied = true;
        	            break;
        	        }
        	    }

        	    if (cell != null && !isOccupied) {
        	        targetCell = cell;
        	        break;
        	    }
        	}

        	if (targetCell != null) {
        	    marbleCircle.setLayoutX(targetCell.getLayoutX());
        	    marbleCircle.setLayoutY(targetCell.getLayoutY());
        	}
        }
    }

    private void moveToTrackStartUI(Marble marble) {
        Circle marbleCircle = marbleCircleMap.get(marble);
        if (marbleCircle != null) {
        	Circle targetCell;
            String entryFxId = marble.getColour().name().toLowerCase() ;
            if(entryFxId=="red")
            {
                 targetCell = (Circle) mainboardLayout.lookup("#cell_25");
            }
            else
            {
            	if(entryFxId=="blue")
            	{
                   targetCell = (Circle) mainboardLayout.lookup("#cell_0");
            	}
            	else
            	{
            		if(entryFxId=="green")
                	{
                         targetCell = (Circle) mainboardLayout.lookup("#cell_50");
                	}
            		else
            		{
                        targetCell = (Circle) mainboardLayout.lookup("#cell_75");
            		}
            	}
            }
            if (targetCell != null) {
                marbleCircle.setLayoutX(targetCell.getLayoutX());
                marbleCircle.setLayoutY(targetCell.getLayoutY());
            }
        }
    }
//
//    private void showAlert(String title, String content) {
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle(title);
//            alert.setHeaderText(null);
//            alert.setContentText(content);
//            alert.showAndWait();
//        });
//    }
}
