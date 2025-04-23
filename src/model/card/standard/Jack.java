package model.card.standard;

import java.util.ArrayList;

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
   	public void act(ArrayList<Marble> marbles) throws ActionException,
   			InvalidMarbleException {
    	
    	//Not supposed to validate anything just do the action of the card
    	
//       	if (marbles == null) {
//       	    throw new InvalidMarbleException("Marbles list cannot be null");
//       	}
//       	if (!validateMarbleSize(marbles)) {
//               throw new InvalidMarbleException("Jack card requires exactly two marbles");
//           }
//           if (!validateMarbleColours(marbles)) {
//               throw new InvalidMarbleException("Jack card requires one of your marbles and one opponent's marble");
//           }
           
//           try {
//               boardManager.swap(marbles.get(0), marbles.get(1));
//           } catch (IllegalSwapException e) {
//               throw e;
//           }
    	
    	
    	//if marbles' size is 1 --> Act as standard
    	
    	if(marbles.size()==1) 
    		super.act(marbles);
    	
    	else {
    		boardManager.swap(marbles.get(0), marbles.get(1));
    	}
       }
    

}
