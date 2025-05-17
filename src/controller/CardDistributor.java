package controller;

import model.card.Card;
import model.card.Deck;
import model.card.standard.Standard;
import model.card.standard.Suit;
import model.card.wild.Burner;
import engine.GameManager;
import engine.board.BoardManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import view.CardView;
import view.CardsView;
import view.Mainboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class CardDistributor {
	
    private final GameManager gameManager;
    private final BoardManager boardManager;
    private static ArrayList<ArrayList<Card>> playerHands;
    private final Mainboard mainboard;

   

    public CardDistributor(GameManager gameManager, BoardManager boardManager,Mainboard mainboard) {
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.playerHands = new ArrayList<>();
        this.mainboard=mainboard;
    }

   
    public  void initializeGame() throws IOException {
        // Load cards from CSV using your existing Deck class
        Deck.loadCardPool(boardManager, gameManager);
        
        // Deal 4 cards to each of 4 players
        for (int i = 0; i < 4; i++) {
            playerHands.add(Deck.drawCards());
        }

       this.putCardsOnBoard();
    }
    public  void putCardsOnBoard()
    {
    	mainboard.getCardBox1().getChildren().add(getCardBox(0));
    	mainboard.getCardBox2().getChildren().add(getCardBox(1));
    	mainboard.getCardBox3().getChildren().add(getCardBox(2));
    	mainboard.getCardBox4().getChildren().add(getCardBox(3));


    	
    }
    
    public static HBox getCardBox(int i)
    {
    	CardsView cardsView=new CardsView();
    	ArrayList<Card> tmp=playerHands.get(i);
    	for(int j=0;j<tmp.size();j++)
    	{
    		Card card=tmp.get(j);
    		CardView cardView;
    		String path="";
    		if (card instanceof Standard)
    	    {
    		   int rank=((Standard) card).getRank();
    		   Suit suit=((Standard) card).getSuit();
    		   
    		   switch (suit) {
    	        case SPADE:
    	        	switch(rank) {
    	        	case 1: path ="/images/1 spades.jpg"; break;
    	        	case 2: path ="/images/2 spades.jpg"; break;
    	        	case 3: path ="/images/3 spades.jpg"; break;
    	        	case 4: path ="/images/4 spades.jpg"; break;
    	        	case 5: path ="/images/5 spades.jpg"; break;
    	        	case 6: path ="/images/6 spades.jpg"; break;
    	        	case 7: path ="/images/7 spades.jpg"; break;
    	        	case 8: path ="/images/8 spades.jpg"; break;
    	        	case 9: path ="/images/9 spades.jpg"; break;
    	        	case 10: path ="/images/10 spades.jpg"; break;
    	        	case 11: path ="/images/jack spades.jpg"; break;
    	        	case 12: path ="/images/queen spades.jpg"; break;
    	        	case 13: path ="/images/king spades.jpg"; break;


    	        	}
    	        case CLUB:
    	        	switch(rank) {
    	        	case 1: path ="/images/1 clubs.jpg"; break;
    	        	case 2: path ="/images/2 clubs.jpg"; break;
    	        	case 3: path ="/images/3 clubs.jpg"; break;
    	        	case 4: path ="/images/4 clubs.jpg"; break;
    	        	case 5: path ="/images/5 clubs.jpg"; break;
    	        	case 6: path ="/images/6 clubs.jpg"; break;
    	        	case 7: path ="/images/7 clubs.jpg"; break;
    	        	case 8: path ="/images/8 clubs.jpg"; break;
    	        	case 9: path ="/images/9 clubs.jpg"; break;
    	        	case 10: path ="/images/10 clubs.jpg"; break;
    	        	case 11: path ="/images/jack clubs.jpg"; break;
    	        	case 12: path ="/images/queen clubs.jpg"; break;
    	        	case 13: path ="/images/king clubs.jpg"; break;

    	        	}
    	        case DIAMOND:
    	        	switch(rank){
    	        	case 1:path="/images/1 diamnods.jpg";break;
    	        	case 2:path="/images/2 diamnods.jpg";break;
    	        	case 3:path="/images/3 diamnods.jpg";break;
    	        	case 4:path="/images/4 diamnods.jpg";break;
    	        	case 5:path="/images/5 diamnods.jpg";break;
    	        	case 6:path="/images/6 diamnods.jpg";break;
    	        	case 7:path="/images/7 diamnods.jpg";break;
    	        	case 8:path="/images/8 diamnods.jpg";break;
    	        	case 9:path="/images/9 diamnods.jpg";break;
    	        	case 10:path="/images/10 diamnods.jpg";break;
    	        	case 11:path="/images/jack diamnods.jpg";break;
    	        	case 12:path="/images/queen diamnods.jpg";break;
    	        	case 13:path="/images/king diamnods.jpg";break;

    	        	}
    	        case HEART:
    	        	switch(rank){
    	        	case 1:path="/images/1 hearts.jpg";break;
    	        	case 2:path="/images/2 hearts.jpg";break;
    	        	case 3:path="/images/3 hearts.jpg";break;
    	        	case 4:path="/images/4 hearts.jpg";break;
    	        	case 5:path="/images/5 hearts.jpg";break;
    	        	case 6:path="/images/6 hearts.jpg";break;
    	        	case 7:path="/images/7 hearts.jpg";break;
    	        	case 8:path="/images/8 hearts.jpg";break;
    	        	case 9:path="/images/9 hearts.jpg";break;
    	        	case 10:path="/images/10 hearts.jpg";break;
    	        	case 11:path="/images/jack hearts.jpg";break;
    	        	case 12:path="/images/queen hearts.jpg";break;
    	        	case 13:path="/images/king hearts.jpg";break;
    	        		
    	        	}
    		   }
    		   cardView=new CardView(path,false);
    	    } 
    		else
    		{
    			if(card instanceof Burner)
    			{
    				 cardView=new CardView("/images/Burner.jpg",false);
    			}
    			else
    			{
    				 cardView=new CardView("/images/.Saver.jpg",false);
    			}
    		}
    		cardsView.addCard(cardView);
    		
    		}
    		return cardsView.getCardsContainer();
    	}
    

    
    public ArrayList<Card> getPlayerHand(int playerIndex) {
        return playerHands.get(playerIndex);
    }

    
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