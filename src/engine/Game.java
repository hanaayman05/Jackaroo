package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import engine.board.Board;
import engine.board.Cell;
import engine.board.CellType;
import engine.board.SafeZone;
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
    	//should check whether the number of cards is enough or not with respect to the current turn 0-->4 , 1-->3, 2-->2, 3-->1
        return getCurrentPlayer().getHand().size() + turn == 4;
    }
    
    public void playPlayerTurn() throws GameException {
        getCurrentPlayer().play();
    }
    
    public void endPlayerTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);

        firePit.add(currentPlayer.getSelectedCard());
        currentPlayer.getHand().remove(currentPlayer.getSelectedCard());

        currentPlayer.deselectAll();

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        if (currentPlayerIndex == 0) {
            turn++;
            
            if(turn == 4) {
            	turn = 0;
            	
        		for(int i=0; i<4; i++) {
        			Player player = players.get(i);
        			//refilling cardsPool if less than 4
        			if(Deck.getPoolSize()<4) {
        				Deck.refillPool(firePit);
        				firePit.clear();
        			}
        			
        			//refilling player's hands
        			player.setHand(Deck.drawCards());
        		}
        	}
        }
    }
    
    public Colour checkWin() {
    	ArrayList<SafeZone> safeZones = board.getSafeZones();
    	
        for (SafeZone safeZone : safeZones) {
            
        	//The arrayList marbles represent the player's home zone, marbles get removed when they are sent to the track
        	
//            ArrayList<Marble> allMarbles = new ArrayList<>(player.getMarbles());
//            int marblesInSafeZone = 0;
            
            
//            for (Marble marble : allMarbles) {
//                if (!board.getActionableMarbles().contains(marble)) {
//                    marblesInSafeZone++;
//                }
//            }
            
            if(safeZone.isFull()) return safeZone.getColour();
            
            
//            if (marblesInSafeZone >= 4) {
//                return player.getColour();
//            }
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
        Player target = players.get(0);
        for (Player p : players) {
            if (p.getColour() == colour) {
                target = p;
                break;
            }
        }
        
        if (target.getHand().isEmpty()) {
            throw new CannotDiscardException("No cards to discard");
        }
        
        //should discard a random card --> choose a random index
//        Card discarded = target.getHand().remove(0); 
        Card discarded = target.getHand().remove((int) (Math.random()*(target.getHand().size())));
        firePit.add(discarded);
    }

    
    public void discardCard() throws CannotDiscardException {
    	//Not supposed to do all of this or throw any exception, just get a random player index that is not the current player
    	
//        ArrayList<Player> opponents = new ArrayList<>(players);
//        opponents.remove(getCurrentPlayer());
//        opponents.removeIf(p -> p.getHand().isEmpty());
//        
//        if (opponents.isEmpty()) {
//            throw new CannotDiscardException("No players with cards to discard");
//        }
        
    	//Not supposed to do all of this or throw any exception, just get a random player index that is not the current player
    	
//        Player target = opponents.get(0); 
//        Card discarded = target.getHand().remove(0);
//        firePit.add(discarded);
    	
    	Colour currentPlayerColour = players.get(currentPlayerIndex).getColour();
    	Colour randomPlayerColour = players.get(0).getColour();
    	
    	do randomPlayerColour = players.get((int)(Math.random()*4)).getColour();
    	while(randomPlayerColour==currentPlayerColour);
    	
    	discardCard(randomPlayerColour);
    }

    
    public Colour getActivePlayerColour() {
        return getCurrentPlayer().getColour();
    }

    
    public Colour getNextPlayerColour() {
        int nextIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(nextIndex).getColour();
    }
    
    
    
}

    

