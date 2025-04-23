package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import engine.board.Board;
import engine.board.Cell;
import engine.board.CellType;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.*;
import exception.*;

@SuppressWarnings("unused")
public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
	private int currentPlayerIndex;
    private final ArrayList<Card> firePit;
    private int turn;

    public Game(String playerName) throws IOException {
        turn = 0;
        currentPlayerIndex = 0;
        firePit = new ArrayList<>();

        ArrayList<Colour> colourOrder = new ArrayList<>();
        
        colourOrder.addAll(Arrays.asList(Colour.values()));
        
        Collections.shuffle(colourOrder);
        
        this.board = new Board(colourOrder, this);
        
        Deck.loadCardPool(this.board, (GameManager)this);
        
        this.players = new ArrayList<>();
        this.players.add(new Player(playerName, colourOrder.get(0)));
        
        for (int i = 1; i < 4; i++) 
            this.players.add(new CPU("CPU " + i, colourOrder.get(i), this.board));
        
        for (int i = 0; i < 4; i++) 
            this.players.get(i).setHand(Deck.drawCards());
        
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
    
    private Player getCurrentPlayer() {
	    return players.get(currentPlayerIndex);
	}
    
    public void selectCard(Card card) throws InvalidCardException {
        getCurrentPlayer().selectCard(card);
    }
    
    public void selectMarble(Marble marble) throws InvalidMarbleException {
        getCurrentPlayer().selectMarble(marble);
    }
    
    public void deselectAll() {
        getCurrentPlayer().deselectAll();
    }
    
    public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException {
        if (splitDistance < 1 || splitDistance > 6) {
            throw new SplitOutOfRangeException("Split distance must be between 1 and 6.");
        }
        
        this.board.setSplitDistance(splitDistance); //there is no split distance instance variable in board class
    }
    
    public boolean canPlayTurn() {
        return !getCurrentPlayer().getHand().isEmpty();
    }
    
    public void playPlayerTurn() throws GameException {
        getCurrentPlayer().play();
    }
    
    public void endPlayerTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);

        
        if (currentPlayer.getSelectedCard() != null) {
            firePit.add(currentPlayer.getSelectedCard());
            currentPlayer.getHand().remove(currentPlayer.getSelectedCard());
        }

        currentPlayer.deselectAll();

       
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        
        if (currentPlayerIndex == 0) {
            turn++;
            
            
            if (turn >= 4) {
                turn = 0;
                
                
                for (Player player : players) {
                    while (player.getHand().size() < 4 && Deck.getPoolSize() > 0) {
                        ArrayList<Card> drawnCards = Deck.drawCards();
                        if (!drawnCards.isEmpty()) {
                            player.getHand().add(drawnCards.get(0));
                        }
                    }
                }
                
                
                if (Deck.getPoolSize() < 4) {
                    Deck.refillPool(new ArrayList<>(firePit));
                    firePit.clear();
                }
            }
        }
    }
    
    public Colour checkWin() {
        for (Player player : players) {
            
            ArrayList<Marble> allMarbles = new ArrayList<>(player.getMarbles());
            int marblesInSafeZone = 0;
            
            
            for (Marble marble : allMarbles) {
                if (!board.getActionableMarbles().contains(marble)) {
                    marblesInSafeZone++;
                }
            }
            
            
            if (marblesInSafeZone >= 4) {
                return player.getColour();
            }
        }
        return null;
    }
    
 
    public void sendHome(Marble marble) {
        for (Player player : players) {
            if (player.getColour() == marble.getColour()) {
                player.regainMarble(marble);
                break;
            }
        }
    }

    
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException {
        Player currentPlayer = getCurrentPlayer();
        Marble marble = currentPlayer.getOneMarble();
        if (marble == null) {
            throw new CannotFieldException("No marbles available to field");
        }
        board.sendToBase(marble);
        currentPlayer.getMarbles().remove(marble);
    }

    
    public void discardCard(Colour colour) throws CannotDiscardException {
        Player target = null;
        for (Player p : players) {
            if (p.getColour() == colour) {
                target = p;
                break;
            }
        }
        
        if (target == null || target.getHand().isEmpty()) {
            throw new CannotDiscardException("No cards to discard");
        }
        
        Card discarded = target.getHand().remove(0); 
        firePit.add(discarded);
    }

    
    public void discardCard() throws CannotDiscardException {
        ArrayList<Player> opponents = new ArrayList<>(players);
        opponents.remove(getCurrentPlayer());
        opponents.removeIf(p -> p.getHand().isEmpty());
        
        if (opponents.isEmpty()) {
            throw new CannotDiscardException("No players with cards to discard");
        }
        
        Player target = opponents.get(0); 
        Card discarded = target.getHand().remove(0);
        firePit.add(discarded);
    }

    
    public Colour getActivePlayerColour() {
        return getCurrentPlayer().getColour();
    }

    
    public Colour getNextPlayerColour() {
        int nextIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(nextIndex).getColour();
    }
    
    
    
}

    

