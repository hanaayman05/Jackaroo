package controller;

import model.card.Card;
import model.card.Deck;
import engine.GameManager;
import engine.board.BoardManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDistributor {
    private final GameManager gameManager;
    private final BoardManager boardManager;
    private List<List<Card>> playerHands;

    public CardDistributor(GameManager gameManager, BoardManager boardManager) {
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.playerHands = new ArrayList<>();
    }

    /**
     * Initializes the card pool and deals initial hands
     */
    public void initializeGame() throws IOException {
        // Load cards from CSV using your existing Deck class
        Deck.loadCardPool(boardManager, gameManager);
        
        // Deal 4 cards to each of 4 players
        for (int i = 0; i < 4; i++) {
            playerHands.add(Deck.drawCards());
        }
    }

    /**
     * Gets a specific player's hand
     * @param playerIndex 0-3 (0 is human player)
     */
    public List<Card> getPlayerHand(int playerIndex) {
        return playerHands.get(playerIndex);
    }

    /**
     * Discards a card and refills the hand if needed
     */
    public void discardAndRefill(int playerIndex, Card cardToDiscard) throws IOException {
        // Remove from player's hand
        playerHands.get(playerIndex).remove(cardToDiscard);
        
        // Refill if below 4 cards
        if (playerHands.get(playerIndex).size() < 4) {
            refillHand(playerIndex);
        }
    }

    /**
     * Refills a player's hand to 4 cards
     */
    private void refillHand(int playerIndex) throws IOException {
        List<Card> hand = playerHands.get(playerIndex);
        while (hand.size() < 4 && Deck.getPoolSize() > 0) {
            hand.addAll(Deck.drawCards());
        }
        
        // Reshuffle discard pile if deck is empty
        if (Deck.getPoolSize() == 0) {
            Deck.refillPool(new ArrayList<>()); // Pass empty list to trigger reshuffle
        }
    }
}