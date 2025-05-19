package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import model.Colour;
import controller.GUI;

public class Mainboard {
    private StackPane mainboardView;
    
    @FXML
    private Pane drawPile;

    @FXML
    private HBox cardBox1;
    @FXML
    private HBox cardBox2;
    @FXML
    private HBox cardBox3;
    @FXML
    private HBox cardBox4;
    
    @FXML
    private ImageView playerImageView;

    private StackPane root;

    
    public Mainboard() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mainboard.fxml"));
        	loader.setController(this);
        	Parent root = loader.load();
            mainboardView = new StackPane(root);
            
        	
        } catch (IOException e) {
            e.printStackTrace();
            mainboardView = new StackPane(); // Fallback empty pane
        }
    }
    public  HBox getCardBox1() {
        return cardBox1;
    }
    public HBox getCardBox2() {
        return cardBox2;
    }
    public HBox getCardBox3() {
        return cardBox3;
    }
    public HBox getCardBox4() {
        return cardBox4;
    }
    public Pane getdrawPile()
    {
    	return drawPile;
    }

    public StackPane getMainboardView() {
        return mainboardView;
    }
    
    public ImageView getPlayerImageView() {
        return playerImageView;
    }
//    public String getHomeFxId(Colour colour) {
//        switch (colour) {
//            case RED: return "redHome1";
//            case BLUE: return "blueHome1";
//            case GREEN: return "greenHome1";
//            case YELLOW: return "yellowHome1";
//            default: return "";
//        }
//    }
//
//    public String getStartFxId(Colour colour) {
//        switch (colour) {
//            case RED: return "cell_0";  // adjust to your actual start IDs
//            case BLUE: return "cell_25";
//            case GREEN: return "cell_50";
//            case YELLOW: return "cell_75";
//            default: return "";
//        }


    
}