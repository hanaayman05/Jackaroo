package model.card;

import engine.GameManager;
import engine.board.BoardManager;

abstract public class Card {
	
	private final String name;
	private final String description;
	protected BoardManager boardManager;  //  initialized by the Game class, wrapped as the interface BoardManager.
	protected GameManager gameManager;
	
	public Card(String name, String description, BoardManager boardManager, GameManager gameManager)
	{
		this.name=name;
		this.description=description;
		this.boardManager=boardManager;
		this.gameManager=gameManager;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	

}
