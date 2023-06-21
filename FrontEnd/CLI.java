package FrontEnd;

import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Tiles.TileFactory;
import BusinessLayer.Tiles.Units.Player;

import java.util.Scanner;

public class CLI {
    private MessageCallback m;
    private InputReader r;

    private BoardCallbacks boardCallbacks;

    public CLI(){
        m = (String message) -> displayMessage(message);
        r = () -> readLine();
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
        TileFactory tileFactory = new TileFactory();
    }

}
