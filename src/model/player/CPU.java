package model.player;
import engine.board.*;
import engine.*;
import model.*;
public class CPU extends Player {
    private final BoardManager boardManager;

    public CPU(String name, Colour colour, BoardManager boardManager) {
        super(name, colour);
        this.boardManager = boardManager;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }
}
