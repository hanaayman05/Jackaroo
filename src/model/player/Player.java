package model.player;

import model.card.Card;

import model.player.Marble;
import model.Colour;
import java.util.ArrayList;

public class Player {
    private final String name;
    private final Colour colour;
    private final ArrayList<Marble> marbles;
    private final ArrayList<Marble> selectedMarbles;
    private ArrayList<Card> hand;
    private final Card selectedCard;

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

}
