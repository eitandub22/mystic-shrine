package FrontEnd;

import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.TileFactory;
import BusinessLayer.Tiles.Units.Player;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private MessageCallback m;
    private InputReader r;
    private GameManager gameManager;

    private Scanner sc;

    public CLI(String levelsDir) throws IOException {
        m = (String message) -> displayMessage(message);
        r = () -> readLine();
        gameManager = new GameManager(levelsDir);
        sc = new Scanner(System.in);
    }

    private void displayMessage(String m) {
        System.out.println(m);
    }
    private String readLine(){
        return sc.nextLine();
    }
    public void getPlayer(){
        TileFactory tileFactory = gameManager.getFactory();
        tileFactory.showPlayers(m);
        do {
            System.out.println("Select a player");
        }while();
    }

}
