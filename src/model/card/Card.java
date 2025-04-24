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
        return marbles.size() == 1;
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        if (marbles.size() == 0)
            return true;
        
        else if (marbles.size() == 1) {
            Marble marble = marbles.get(0);
            return marble.getColour() == gameManager.getActivePlayerColour();
        }
        
//        else if(this instanceof Five && marbles.size()==1) return true; 
        
        else {
            Colour activePlayerColour = gameManager.getActivePlayerColour();
            return marbles.get(0).getColour()==(activePlayerColour) && marbles.get(1).getColour()==(activePlayerColour);
        }
    }
    
    
    public abstract void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException;


    
}
