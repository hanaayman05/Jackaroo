package model.card.wild;
import java.util.ArrayList;

import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Saver extends Wild {

    public Saver(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
    

    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
    	if (marbles == null) {
    	    throw new InvalidMarbleException("Marbles list cannot be null");
    	}
    	if (!validateMarbleSize(marbles)) {
            throw new InvalidMarbleException("Saver card requires exactly one marble");
        }
        if (!validateMarbleColours(marbles)) {
            throw new InvalidMarbleException("Saver card can only target your own marbles");
        }
        
        try {
            boardManager.sendToSafe(marbles.get(0));
        } catch (InvalidMarbleException e) {
            throw e;
        }
    }

}
