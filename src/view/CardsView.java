package view;

import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.Node;

public class CardsView {

    private final HBox cardsContainer;

    public CardsView() {
        cardsContainer = new HBox(-40); 
        cardsContainer.setPadding(new Insets(20));
    }

   
    public void addCard(Node cardView) {
        cardsContainer.getChildren().add(cardView);
    }

   
    public void removeCard(Node cardView) {
        cardsContainer.getChildren().remove(cardView);
    }

    public void clear() {
        cardsContainer.getChildren().clear();
    }

    
    public HBox getCardsContainer() {
        return cardsContainer;
    }
}
