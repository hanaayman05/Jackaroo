package model.card.standard;

import engine.board.*;
import engine.*;
import exception.*;

import java.util.ArrayList;

import model.player.Marble;
public class Ace extends Standard {
	
    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) 
    {
        super(name, description, 1, suit, boardManager, gameManager);
    }
    
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
    	if (marbles == null || marbles.isEmpty()) {
            
            try {
                gameManager.fieldMarble();
            } catch (CannotFieldException e) {
                throw e;
            } catch (IllegalDestroyException e) {
                throw new CannotFieldException("Fielding failed: " + e.getMessage());
            }
        } else {
            
            super.act(marbles);
        }
    }
}

