package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.Tiles.TileFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws IOException {
        GameManager gameManager = new GameManager(args);
        gameManager.createLevel(0, 1);
        Board board = gameManager.getBoard();
        System.out.println(board.toString());
    }

}
