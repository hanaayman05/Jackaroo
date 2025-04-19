package model.player;

import model.card.Card;
import exception.*;

import model.player.Marble;
import model.Colour;
import java.util.ArrayList;

public class Player {
    private final String name;
    private final Colour colour;
    private final ArrayList<Marble> marbles;
    private final ArrayList<Marble> selectedMarbles;
    private ArrayList<Card> hand;
    private Card selectedCard;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.marbles = new ArrayList<>();
        this.selectedMarbles = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.selectedCard = null;

        for (int i = 0; i < 4; i++) {
            marbles.add(new Marble(colour));
        }
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public ArrayList<Marble> getMarbles() {
        return new ArrayList<>(marbles);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
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
        if (marble == null) {
            throw new InvalidMarbleException("Marble cannot be null");
        }
        if (selectedMarbles.size() >= 2) {
            throw new InvalidMarbleException("Max 2 marbles selectable");
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
