package model.player;

import java.util.ArrayList;

import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;

@SuppressWarnings("unused")
public class Player {
    private final String name;
    private final Colour colour;
    private ArrayList<Card> hand;
    private final ArrayList<Marble> marbles;
    private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.hand = new ArrayList<>();
        this.selectedMarbles = new ArrayList<>();
        this.marbles = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            this.marbles.add(new Marble(colour));
        }
        
        //default value
        this.selectedCard = null;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    
    public ArrayList<Marble> getMarbles() {
		return marbles;
	}
    
    public Card getSelectedCard() {
        return selectedCard;
    }
    
    public void regainMarble(Marble marble) {
        if (marble != null) {
            marbles.add(marble);
        }
    }

    public Marble getOneMarble() {
        return marbles.isEmpty() ? null : marbles.get(0);
    }

    public void selectCard(Card card) throws InvalidCardException {
        if (card == null || !hand.contains(card)) {
            throw new InvalidCardException("Card not in hand");
        }
        this.selectedCard = card; 
    }

    public void selectMarble(Marble marble) throws InvalidMarbleException {
        if (selectedMarbles.contains(marble)) {
            return; 
        }
        if (selectedMarbles.size() >= 2) {
            throw new InvalidMarbleException("Cannot select more than 2 marbles");
        }
        selectedMarbles.add(marble);
    }

    public void deselectAll() {
        selectedCard = null;
        selectedMarbles.clear();
    }

    public void play() throws GameException {
        if (selectedCard == null) {
            throw new InvalidCardException("No card selected");
        }

        
        if (!selectedCard.validateMarbleSize(selectedMarbles)) {
            throw new InvalidMarbleException("Invalid marble count for card");
        }
        if (!selectedCard.validateMarbleColours(selectedMarbles)) {
            throw new InvalidMarbleException("Invalid marble colours for card");
        }

        
        selectedCard.act(new ArrayList<>(selectedMarbles));
        deselectAll();
    }



}
