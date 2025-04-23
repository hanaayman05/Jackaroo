package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.IllegalSwapException;
import exception.InvalidMarbleException;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 1 || marbles.size() == 2; 
    }
    
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
       
        if (marbles.size() == 1) {
            super.act(marbles); 
        } else {
           
            boardManager.swap(marbles.get(0), marbles.get(1));
        }
    }
    

    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        if (marbles.size() != 2) return false;
        Colour activeColour = gameManager.getActivePlayerColour();
        return marbles.get(0).getColour() == activeColour
               && marbles.get(1).getColour() != activeColour;
    }
 
    

}
