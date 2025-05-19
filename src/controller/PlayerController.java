package controller;

import engine.GameManager;
import engine.board.BoardManager;
import exception.*;
//import javafx.scene.control.Alert;
import model.Colour;
import model.player.Marble;
import view.Mainboard;

import java.util.List;

public class PlayerController {

    private final Colour playerColour;
    private final GameManager gameManager;
    private final BoardManager boardManager;
    private final Mainboard mainboard;

    public PlayerController(Colour playerColour, GameManager gameManager,
                            BoardManager boardManager, Mainboard mainboard) {
        this.playerColour = playerColour;
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.mainboard = mainboard;
    }

    public void playTurn() {
        try {
            if (!gameManager.getActivePlayerColour().equals(playerColour)) return;

            List<Marble> marbles = boardManager.getActionableMarbles();
            if (marbles.isEmpty()) {
                gameManager.discardCard();  // Let user discard
                return;
            }

            // Example: just move the first marble for now
            Marble selected = marbles.get(0);
            boardManager.moveBy(selected, 4, false); // hardcoded steps for now
        } catch (CannotDiscardException | IllegalDestroyException | IllegalMovementException e) {
            showAlert("Player Turn Error", e.getMessage());
        }
    }

    public Colour getPlayerColour() {
        return playerColour;
    }

    private void showAlert(String title, String content) {
   //     Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
  }
}
