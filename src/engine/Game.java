package engine;

import model.player.Player;
import model.player.CPU;
import model.card.Card;
import model.card.Deck;
import engine.board.Board;
import engine.board.BoardManager;
import model.Colour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;

    public Game(String playerName) throws IOException {
        // Shuffle colors for random player assignments
        ArrayList<Colour> colourOrder = new ArrayList<>();
        Collections.addAll(colourOrder, Colour.RED, Colour.GREEN, Colour.BLUE, Colour.YELLOW);
        Collections.shuffle(colourOrder);

        // Initialise game board
        this.board = new Board(colourOrder , this);

        // Load card pool
        Deck.loadCardPool((BoardManager) this.board, this);

        // Initialise players
        this.players = new ArrayList<>();
        players.add(new Player(playerName, colourOrder.get(0))); // Human player

        // Add CPU players
        for (int i = 1; i < 4; i++) {
            players.add(new CPU("CPU " + i, colourOrder.get(i), (BoardManager) this.board));
        }

        // Distribute initial cards
        for (Player player : players) {
            player.setHand(Deck.drawCards());
        }

        // Initialise turn tracking
        this.currentPlayerIndex = 0;
        this.turn = 0;
        this.firePit = new ArrayList<>();
    }

	public Board getBoard() {
		return board;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getFirePit() {
		return firePit;
	}
    
    
}
