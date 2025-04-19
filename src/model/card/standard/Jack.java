package model.card.standard;
import java.util.ArrayList;

import model.player.Marble;
import engine.board.*;
import engine.*;
import exception.ActionException;
import exception.InvalidMarbleException;
import exception.*;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) 
    {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
    	if (marbles == null) {
    	    throw new InvalidMarbleException("Marbles list cannot be null");
    	}
    	if (!validateMarbleSize(marbles)) {
            throw new InvalidMarbleException("Jack card requires exactly two marbles");
        }
        if (!validateMarbleColours(marbles)) {
            throw new InvalidMarbleException("Jack card requires one of your marbles and one opponent's marble");
        }
        
        try {
            boardManager.swap(marbles.get(0), marbles.get(1));
        } catch (IllegalSwapException e) {
            throw e;
        }
    }
}
