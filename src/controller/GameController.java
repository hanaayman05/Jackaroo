package controller;

import engine.Game;
import javafx.application.Platform;
//import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import model.Colour;
import model.card.Card;
import model.player.Player;
import view.Mainboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Game game;
    private Mainboard mainboardView;
    private StackPane layout;



    public void initializeGame() {
        try {
            game = new Game("aseel");
            mainboardView = new Mainboard();
            layout = mainboardView.getMainboardView();
            
            MarbleInitializer marbleInitializer = new MarbleInitializer();
            marbleInitializer.initializeMarbles(layout);

            ArrayList<ArrayList<Card>> playerHands=initializePlayers(); 

            CardDistributor cardDistributor = new CardDistributor( mainboardView);
            cardDistributor.initializeCards(playerHands);

            
           //startGameLoop(true);     

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  ArrayList<ArrayList<Card>> initializePlayers() {
       ArrayList<Player> players=game.getPlayers();
       ArrayList<ArrayList<Card>> playerHands=new  ArrayList<ArrayList<Card>>();
       
       for(int i=0;i<players.size();i++)
       {
    	  ArrayList<Card> tmp=players.get(i).getHand();
    	  playerHands.add(tmp);
       }
      return playerHands;
    }

    private void startGameLoop(Boolean isGameRuning) {
        
    }

//    private void nextTurn() {
//        if (!gameRunning) return;
//
//        PlayerController currentPlayer = playerControllers.get(currentPlayerIndex);
//        currentPlayer.playTurn();
//
//        currentPlayerIndex = (currentPlayerIndex + 1) % playerControllers.size();
//
//        Platform.runLater(() -> {
//            try {
//                Thread.sleep(800); // Optional delay
//            } catch (InterruptedException ignored) {}
//            nextTurn();
//        });
//    }

    public StackPane getLayout() {
        return layout;
    }

    public Mainboard getMainboardView() {
        return mainboardView;
    }
}