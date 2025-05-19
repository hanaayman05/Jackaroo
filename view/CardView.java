package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class CardView extends StackPane {

    private static final Map<String, Image> imageCache = new HashMap<>();

    private final ImageView frontImageView;
    private final ImageView backImageView;

    private boolean faceUp = true;

    public CardView(String path, boolean faceUp) {
        this.faceUp = faceUp;

        frontImageView = new ImageView(loadImage(path));          // Use cached image
        backImageView = new ImageView(loadImage("/images/CardImage.png"));  // Back side

        setupImageView(frontImageView);
        setupImageView(backImageView);

        this.getChildren().addAll(backImageView, frontImageView);

        updateVisibility();
    }

    private void setupImageView(ImageView imageView) {
        imageView.setFitWidth(120);       // Suggest smaller to reduce memory
        imageView.setFitHeight(160);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }

    private Image loadImage(String path) {
        return imageCache.computeIfAbsent(path, key ->
            new Image(getClass().getResourceAsStream(key), 120, 160, true, true)
        );
    }

    public void updateVisibility() {
        frontImageView.setVisible(faceUp);
        backImageView.setVisible(!faceUp);
    }

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