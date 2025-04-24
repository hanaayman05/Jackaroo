package model.card.standard;

import java.util.ArrayList;

import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Seven extends Standard {

    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }

 
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 1 || marbles.size() == 2; 
    }
    
    //Not supposed to override validateMarbleColours --> Failure
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	if(marbles.size()==1) super.act(marbles); //Act as standard
    	else { //splits the total distance of 7 between 2 of the current player's marbles
    		int splitDistance = boardManager.getSplitDistance();
    		
    		Marble marble_1 = marbles.get(0);
    		Marble marble_2 = marbles.get(1);
    		
    		boardManager.moveBy(marble_1, splitDistance, false);
    		boardManager.moveBy(marble_2, 7 - splitDistance, false); 		
    	}
	}
}
