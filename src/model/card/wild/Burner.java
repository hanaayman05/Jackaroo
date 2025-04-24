package model.card.wild;
import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.IllegalDestroyException;
import exception.InvalidMarbleException;
import model.player.Marble;


public class Burner extends Wild {

    public Burner(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager); 
    }
    
    @Override
//    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
//        return marbles.size() == 1;
//    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {    	
    	if(marbles.get(0).getColour()!=gameManager.getActivePlayerColour())
    		return true; //should be am opponent's marble
    	return false;
    }
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	boardManager.destroyMarble(marbles.get(0));
    }
    
//    @Override
//	public void act(ArrayList<Marble> marbles) throws ActionException,
//			InvalidMarbleException {
//    	if (marbles == null) {
//    	    throw new InvalidMarbleException("Marbles list cannot be null");
//    	}
//    	 if (!validateMarbleSize(marbles)) {
//             throw new InvalidMarbleException("Burner card requires exactly one marble");
//         }
//         if (!validateMarbleColours(marbles)) {
//             throw new InvalidMarbleException("Burner card can only target opponent's marbles");
//         }
//         
//         try {
//             boardManager.destroyMarble(marbles.get(0));
//         } catch (IllegalDestroyException e) {
//             throw e;
//         }
//    }

}
