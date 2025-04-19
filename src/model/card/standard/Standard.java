package model.card.standard;
import engine.board.*;
import engine.*;
import model.card.Card;
import model.player.Marble;
import exception.*;

import java.util.ArrayList;

public class Standard extends Card {
	
	private final int rank;
    private final Suit suit;
    
    public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
    }

	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
		   if (!validateMarbleSize(marbles)) {
		        throw new InvalidMarbleException("Invalid number of marbles selected");
		    }
		    if (!validateMarbleColours(marbles)) {
		        throw new InvalidMarbleException("You can only move your own marbles");
		    }

		    
		    try {
		        boardManager.moveBy(marbles.get(0), rank, false);
		    } catch (IllegalMovementException e) {
		        throw new IllegalMovementException(e.getMessage());
		    } catch (IllegalDestroyException e) {
		        throw new IllegalDestroyException(e.getMessage());
		    }
		    }
	
		
	}
