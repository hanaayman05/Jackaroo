package model.card.standard;
import engine.board.*;
import engine.*;


public class King extends Standard {
		
    public King(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) 
    {
        super(name, description, 13, suit, boardManager, gameManager);
    }
}
