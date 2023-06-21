package FrontEnd;

import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Tiles.TileFactory;
import BusinessLayer.Tiles.Units.Player;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private MessageCallback m;
    private InputReader r;
    private GameManager gameManager;

    public CLI(String levelsDir) throws IOException {
        m = (String message) -> displayMessage(message);
        r = () -> readLine();
        gameManager = new GameManager(levelsDir);
    }

    private void displayMessage(String m) {
        System.out.println(m);
    }
    private String readLine(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public Player getPlayer(){
        System.out.println("Select a player");
        return null;
    }

}
