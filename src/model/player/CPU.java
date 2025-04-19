package model.player;
import engine.board.*;
import engine.*;
import model.*;
import model.card.Card;
import exception.*;

import java.util.ArrayList;
import java.util.Collections;
public class CPU extends Player {
    private final BoardManager boardManager;

    public CPU(String name, Colour colour, BoardManager boardManager) {
        super(name, colour);
        this.boardManager = boardManager;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }
    
    @Override
    public void play() throws InvalidCardException {
        ArrayList<Marble> actionableMarbles = boardManager.getActionableMarbles();
        ArrayList<Card> handCards = new ArrayList<>(getHand());
        Collections.shuffle(handCards);

        for (Card card : handCards) {
            try {
                
                try {
                    selectCard(card);
                    card.act(new ArrayList<>());
                    return;
                } catch (Exception e) {}

                
                Collections.shuffle(actionableMarbles);
                for (Marble marble : actionableMarbles) {
                    try {
                        selectCard(card);
                        selectMarble(marble);
                        card.act(new ArrayList<>(Collections.singletonList(marble)));
                        return;
                    } catch (Exception e) {}
                }

                
                if (actionableMarbles.size() >= 2) {
                    for (int i = 0; i < actionableMarbles.size(); i++) {
                        for (int j = i+1; j < actionableMarbles.size(); j++) {
                            try {
                                selectCard(card);
                                selectMarble(actionableMarbles.get(i));
                                selectMarble(actionableMarbles.get(j));
                                ArrayList<Marble> selectedPair = new ArrayList<>();
                                selectedPair.add(actionableMarbles.get(i));
                                selectedPair.add(actionableMarbles.get(j));
                                card.act(selectedPair);
                                return;
                            } catch (Exception e) {}
                        }
                    }
                }
            } finally {
                deselectAll();
            }
        }

        
        if (!handCards.isEmpty()) {
            selectCard(handCards.get(0));
        }
    }
    
    
}
