package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

import java.util.*;

import model.*;
import model.player.Marble;
public class Four  extends Standard {

    public Four(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 4, suit, boardManager, gameManager);
    }
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		boardManager.moveBy(marbles.get(0), -4, false);	
	}
}
