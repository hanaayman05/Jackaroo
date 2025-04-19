package model.card.wild;
import java.util.ArrayList;

import engine.board.*;
import engine.*;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;
import exception.*;

public class Burner extends Wild {
	
    public Burner(String name, String description, BoardManager boardManager, GameManager gameManager) 
    {
        super(name, description, boardManager, gameManager);
    }
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
    	if (marbles == null) {
    	    throw new InvalidMarbleException("Marbles list cannot be null");
    	}
    	 if (!validateMarbleSize(marbles)) {
             throw new InvalidMarbleException("Burner card requires exactly one marble");
         }
         if (!validateMarbleColours(marbles)) {
             throw new InvalidMarbleException("Burner card can only target opponent's marbles");
         }
         
         try {
             boardManager.destroyMarble(marbles.get(0));
         } catch (IllegalDestroyException e) {
             throw e;
         }
    }
}

