package model.card.standard;

import java.util.ArrayList;

import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.CannotFieldException;
import exception.IllegalDestroyException;
import exception.InvalidMarbleException;

public class Ace extends Standard {

    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 1, suit, boardManager, gameManager);
    }
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        
        return marbles.size() == 0 || marbles.size() == 1;
    }
    
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
//    	if (marbles == null || marbles.isEmpty()) {
//            
//            try {
//                gameManager.fieldMarble();
//            } catch (CannotFieldException e) {
//                throw e;
//            } catch (IllegalDestroyException e) {
//                throw new CannotFieldException("Fielding failed: " + e.getMessage());
//            }
//        } else {
//            
//            super.act(marbles);
//        }
    	
    	if(marbles.size()==0)
    		gameManager.fieldMarble();
    	else 
    		super.act(marbles);
    }

}
