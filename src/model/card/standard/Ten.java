package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Ten extends Standard {

    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
    
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 0 || marbles.size() == 1;
    }
    
   	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
   		if(marbles.size()==0) {
   			Colour nextPlayerColour = gameManager.getNextPlayerColour();
   			gameManager.discardCard(nextPlayerColour);
   		}
   		
   		else {
   			super.act(marbles);
   		}
   	}
}
