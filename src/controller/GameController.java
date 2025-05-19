package controller;

import engine.Game;
import javafx.application.Platform;
//import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import model.Colour;
import view.Mainboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Game game;
    private Mainboard mainboardView;
    private StackPane layout;

    private List<PlayerController> playerControllers = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private boolean gameRunning = false;

    public GameController() {}

    public void initializeGame() {
        try {
            game = new Game("aseel");
            mainboardView = new Mainboard();
            layout = mainboardView.getMainboardView();

            CardDistributor cardDistributor = new CardDistributor(game, game.getBoard(), mainboardView);
            cardDistributor.initializeCards();

            MarbleInitializer marbleInitializer = new MarbleInitializer();
            marbleInitializer.initializeMarbles(layout);

//            initializePlayers(); // NEW
//            startGameLoop();     // NEW

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePlayers() {
        for (Colour colour : Colour.values()) {
            PlayerController pc = new PlayerController(colour, game, game.getBoard(), mainboardView);
            playerControllers.add(pc);
        }
    }

    private void startGameLoop() {
        gameRunning = true;
        Platform.runLater(this::nextTurn);
    }

    private void nextTurn() {
        if (!gameRunning) return;

        PlayerController currentPlayer = playerControllers.get(currentPlayerIndex);
        currentPlayer.playTurn();

        currentPlayerIndex = (currentPlayerIndex + 1) % playerControllers.size();

        Platform.runLater(() -> {
            try {
                Thread.sleep(800); // Optional delay
            } catch (InterruptedException ignored) {}
            nextTurn();
        });
    }

    public StackPane getLayout() {
        return layout;
    }

    public Mainboard getMainboardView() {
        return mainboardView;
    }
}
