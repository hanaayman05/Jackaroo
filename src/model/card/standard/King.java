package model.card.standard;
import java.util.ArrayList;


import model.player.Marble;
import engine.board.*;
import engine.*;
import exception.ActionException;
import exception.InvalidMarbleException;
import exception.*
;
public class King extends Standard {
		
    public King(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) 
    {
        super(name, description, 13, suit, boardManager, gameManager);
    }
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
    	 if (!validateMarbleSize(marbles)) {
             throw new InvalidMarbleException("Invalid number of marbles selected for this card");
         }
         if (!validateMarbleColours(marbles)) {
             throw new InvalidMarbleException("You can only move your own marbles with this card");
         }
         
         try {
             boardManager.moveBy(marbles.get(0), this.getRank(), true);
         } catch (IllegalMovementException e) {
             throw e;
         } catch (IllegalDestroyException e) {
             throw new IllegalMovementException("King movement failed: " + e.getMessage());
         }
    }
}
