package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        GameManager gameManager = new GameManager(args[0]);
        gameManager.createLevel(0, 1);
        Board board = gameManager.getBoard();
    }

}
