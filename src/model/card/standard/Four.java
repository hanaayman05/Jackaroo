package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;

import java.util.*;

import model.*;
import model.player.Marble;
public class Four  extends Standard {

    public Four(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 4, suit, boardManager, gameManager);
    }
    
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 1; 
    }

}
