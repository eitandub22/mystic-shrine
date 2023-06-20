package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.Tiles.TileFactory;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        String[] levels = new String[]{"level1.txt"};
        GameManager gameManager = new GameManager(levels);
        gameManager.createLevel(1, 1);
        Board board = gameManager.getBoard();
        System.out.println(board.toString());
    }

}
