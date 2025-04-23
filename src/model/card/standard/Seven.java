package model.card.standard;

import java.util.ArrayList;

import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;

public class Seven extends Standard {

    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        
        return marbles.size() == 1 || marbles.size() == 2;
    }
    
    //Not supposed to override validateMarbleColours --> Failure
    
}
