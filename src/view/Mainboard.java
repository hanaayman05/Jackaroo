package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class Mainboard {
    private StackPane mainboardView;

    public Mainboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Mainboard.fxml"));
            mainboardView = new StackPane(root);
        } catch (IOException e) {
            e.printStackTrace();
            mainboardView = new StackPane(); // Fallback empty pane
        }
    }

    public StackPane getMainboardView() {
        return mainboardView;
    }
}