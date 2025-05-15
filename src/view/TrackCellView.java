package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrackCellView {

    private final GridPane track;

    public TrackCellView() {
        this.track = new GridPane();
        buildTrack();
    }

    public GridPane getTrack() {
        return track;
    }

    private void buildTrack() {
        int index = 0;

     
        for (int col = 0; col <= 6; col++) //left w right fe el left
        {
        	addCell(7, col, index++);
        	addCell(20,col,index++);
        }
        for(int col=21;col<=27;col++)   //left w right fe el right
        {
        	addCell(7, col, index++);
        	addCell(20,col,index++);
        }
        for (int row = 0; row <= 6; row++)   //left w right arm fe el top
        {
        	addCell(row, 7, index++);
        	addCell(row,20,index++);
        }

        
        for (int col = 7; col <= 19; col++)  // el top arm el fo2 w el tht
        {
        	addCell(0, col, index++);
        	addCell(27,col,index++);
        }
        for(int row=7;row<=20;row++)    // el arm fe el left w right
        {
        	addCell(row,0,index++);
        	addCell(row,27,index++);
        }
        for(int row=21;row<=27;row++)
        {
        	addCell(row,7,index++);
        	addCell(row,20,index++);
        }
        


    }

    private void addCell(int row, int col, int index) {
        Circle circle = new Circle(30);
        circle.setFill(Color.BEIGE);
        circle.setStroke(Color.BLACK);

        StackPane cell = new StackPane(circle);
        track.add(cell, col, row);
    }
}
