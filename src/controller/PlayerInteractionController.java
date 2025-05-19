package controller;

import engine.GameManager;
import engine.board.BoardManager;
import exception.*;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.card.Card;
import model.player.Marble;
import view.Mainboard;

import java.util.List;
import java.util.Map;

public class PlayerInteractionController {

    private final GameManager gameManager;
    private final BoardManager boardManager;
    private final Mainboard mainboard;
    private final Map<Marble, Circle> marbleMap;

    private Marble selectedMarble;
    private Marble secondMarble;
    private Card selectedCard;

    public PlayerInteractionController(GameManager gameManager, BoardManager boardManager,
                                       Mainboard mainboard, Map<Marble, Circle> marbleMap) {
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.mainboard = mainboard;
        this.marbleMap = marbleMap;
    }

    public void handleMarbleClick(Marble marble) {
        if (selectedMarble == null) {
            selectedMarble = marble;
        } else if (requiresSecondMarble(selectedCard) && secondMarble == null && marble != selectedMarble) {
            secondMarble = marble;
        }

        if (selectedCard != null) {
            handleAction();
        }
    }

    public void setSelectedCard(Card card) {
        this.selectedCard = card;
        if (selectedMarble != null) {
            handleAction();
        }
    }

    public boolean hasSelectedMarble() {
        return selectedMarble != null;
    }

    public void handleAction() {
        if (selectedCard == null || selectedMarble == null) {
            return;
        }

        try {
            int value = selectedCard.getValue();

            if (requiresSecondMarble(selectedCard)) {
                if (secondMarble == null) return;
                boardManager.swap(selectedMarble, secondMarble);
            } else if (isDestroyCard(selectedCard)) {
                boardManager.destroyMarble(selectedMarble);
                moveToHomeUI(selectedMarble);
            } else if (isSendHomeCard(selectedCard)) {
                gameManager.sendHome(selectedMarble);
                moveToHomeUI(selectedMarble);
            } else {
                boardManager.moveBy(selectedMarble, value, false);
            }

            clearHighlights();
            resetSelections();

        } catch (Exception e) {
            System.err.println("Action Error: " + e.getMessage());
        }
    }

    private boolean requiresSecondMarble(Card card) {
        return card != null && card.getValue() == 11;
    }

    private boolean isDestroyCard(Card card) {
        return card != null && card.getValue() == 13;
    }

    private boolean isSendHomeCard(Card card) {
        return card != null && card.getValue() == 12;
    }

    private void resetSelections() {
        selectedCard = null;
        selectedMarble = null;
        secondMarble = null;
    }

    private void moveToHomeUI(Marble marble) {
        Circle circle = marbleMap.get(marble);
        String fxId = mainboard.getHomeFxId(marble.getColour());
        Circle target = (Circle) mainboard.getMainboardView().lookup("#" + fxId);
        if (circle != null && target != null) {
            animateMarbleMove(circle, target.getLayoutX(), target.getLayoutY());
        }
    }

    private void animateMarbleMove(Circle marble, double toX, double toY) {
        TranslateTransition t = new TranslateTransition(Duration.millis(500), marble);
        t.setToX(toX - marble.getLayoutX());
        t.setToY(toY - marble.getLayoutY());
        t.setOnFinished(event -> {
            marble.setLayoutX(toX);
            marble.setLayoutY(toY);
            marble.setTranslateX(0);
            marble.setTranslateY(0);
        });
        t.play();
    }

    private void highlightSelected(Circle circle) {
        Glow glow = new Glow(0.8);
        circle.setEffect(glow);
    }

    private void clearHighlights() {
        for (Circle c : marbleMap.values()) {
            c.setEffect(null);
        }
    }
}
