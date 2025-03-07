package model.card;
import engine.board.*;
import engine.*;
import model.card.standard.*;
import model.card.wild.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	final static private String CARDS_FILE = "Cards.csv";
	static private ArrayList<Card> cardsPool;
	
	public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException {
		cardsPool = new ArrayList<>();
		
		BufferedReader reader = new BufferedReader(new FileReader(CARDS_FILE));
		
		String line;
		
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			
            String[] cols = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            int code = Integer.parseInt(cols[0]);
            int frequency = Integer.parseInt(cols[1]);
            String name = cols[2];
            String description = cols[3];

            Card card;
            
            while(frequency-->0) {
	            if (code>=0 && code<14) {
	            	
	                int rank = Integer.parseInt(cols[4]);
	                Suit suit = Suit.valueOf(cols[5]);
	                
	                switch (code) {
	                	case 0: card = new Standard(name, description, rank, suit, boardManager, gameManager); break;
	                    case 1: card = new Ace(name, description, suit, boardManager, gameManager); break;
	                    case 4: card = new Four(name, description, suit, boardManager, gameManager); break;
	                    case 5: card = new Five(name, description, suit, boardManager, gameManager); break;
	                    case 7: card = new Seven(name, description, suit, boardManager, gameManager); break;
	                    case 10: card = new Ten(name, description, suit, boardManager, gameManager); break;
	                    case 11: card = new Jack(name, description, suit, boardManager, gameManager); break;
	                    case 12: card = new Queen(name, description, suit, boardManager, gameManager); break;
	                    case 13: card = new King(name, description, suit, boardManager, gameManager); break;
	                    default: throw new IOException();
	                }
	                
	            } else {
	                switch (code) {
	                    case 14: card = new Burner(name, description, boardManager, gameManager); break;
	                    case 15: card = new Saver(name, description, boardManager, gameManager); break;
	                    default: throw new IllegalArgumentException("Invalid card code: " + code);
	                }
	            }
	            
	            cardsPool.add(card);
            }

        }
		
        reader.close();
    }
	
	public static ArrayList<Card> drawCards() {
		
        Collections.shuffle(cardsPool);
        ArrayList<Card> drawnCards = new ArrayList<>();

        for(int i=0; i<4 && !cardsPool.isEmpty(); i++) {
        	Card card = cardsPool.remove(0);
        	drawnCards.add(card);
        }
        return drawnCards;
    }
		
}
