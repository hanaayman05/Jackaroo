package model.card.standard;
import engine.board.*;
import engine.*;



public class Seven extends Standard {
	public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) 
	{
        super(name, description, 7, suit, boardManager, gameManager);
    }
}
