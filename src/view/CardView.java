package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {

    private final ImageView frontImageView;
    private final ImageView backImageView;

    private boolean faceUp = true;

    public CardView(String path, boolean faceUp) {
        this.faceUp = faceUp;

     
        frontImageView = new ImageView(loadFrontImage(path));
        backImageView = new ImageView(loadBackImage());

      
        setupImageView(frontImageView);
        setupImageView(backImageView);

      
        this.getChildren().addAll(backImageView, frontImageView);

   
        updateVisibility();
    }

    private void setupImageView(ImageView imageView) {
        imageView.setFitWidth(180);       
        imageView.setFitHeight(220);     
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }

    private Image loadFrontImage(String path) {
       
        return new Image(getClass().getResourceAsStream(path));
    }

    private Image loadBackImage() {
        String path = "/images/CardImage.png";
        return new Image(getClass().getResourceAsStream(path));
    }

    public void updateVisibility() {
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