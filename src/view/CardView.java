package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {

    private final ImageView frontImageView;
    private final ImageView backImageView;

    private boolean faceUp = true;

    public CardView(String rank, String suit,String path, boolean faceUp) {
        this.faceUp = faceUp;

     
        frontImageView = new ImageView(loadFrontImage(rank, suit,path));
        backImageView = new ImageView(loadBackImage());

      
        setupImageView(frontImageView);
        setupImageView(backImageView);

      
        this.getChildren().addAll(backImageView, frontImageView);

   
        updateVisibility();
    }

    private void setupImageView(ImageView imageView) {
        imageView.setFitWidth(80);       // Adjust width as needed
        imageView.setFitHeight(120);     // Card height
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }

    private Image loadFrontImage(String rank, String suit,String path) {
        String filename;
        if (suit != null) {
            filename = rank + "_of_" + suit;  // e.g., "A_of_spades"
        } else {
            filename = rank; // e.g., "Saver" or "Burner"
        }

        return new Image(getClass().getResourceAsStream(path));
    }

    private Image loadBackImage() {
        String path = "file:/C:/Users/aseel/Desktop/Semster%20IV/(CSEN401)%20Computer%20Programming%20Lab/Game/Milestone%202%20Solution/JackarooM2Solution/images/CardImage.png";
        return new Image(getClass().getResourceAsStream(path));
    }

    private void updateVisibility() {
        frontImageView.setVisible(faceUp);
        backImageView.setVisible(!faceUp);
    }

    // Public methods for controlling the card's state

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        updateVisibility();
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        setFaceUp(!faceUp);
    }
}
