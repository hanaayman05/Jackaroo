package model.card;

import engine.GameManager;
import engine.GameManager;
import java.util.ArrayList;
import model.player.Marble;
import engine.board.BoardManager;
import exception.*;
import model.Colour;

public abstract class Card {
	private final String name;
    private final String description;
    protected BoardManager boardManager;
    protected GameManager gameManager;

    public Card(String name, String description, BoardManager boardManager, GameManager gameManager) {
        this.name = name;
        this.description = description;
        this.boardManager = boardManager;
        this.gameManager = gameManager;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        
        return marbles.size() == 0 || marbles.size() == 1;
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        
//        if (!validateMarbleSize(marbles)) {  //you're not supposed to validate the size inside. The size is already valid 
//            return false; 
//        }
        
        Colour activePlayerColour = gameManager.getActivePlayerColour();
        for (Marble marble : marbles) {
            if (marble == null || marble.getColour() != activePlayerColour) { //either 0, 1 or 2 and all should be the same as the active player
                return false;
            }
        }
        return true;
    }
    
    public abstract void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException;


    
}
