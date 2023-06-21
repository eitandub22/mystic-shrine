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

    private TileFactory tileFactory;
    private Scanner sc;

    public CLI(String levelsDir) throws IOException {
        m = (String message) -> displayMessage(message);
        r = () -> readLine();
        gameManager = new GameManager(levelsDir, m);
        sc = new Scanner(System.in);
        tileFactory = gameManager.getFactory();
    }

    private void displayMessage(String m) {
        System.out.println(m);
    }
    private String readLine(){
        return sc.nextLine();
    }
    public void getPlayer(){
        tileFactory.showPlayers(m);
        int i = 0;
        do {
            System.out.print("Select a player: ");
            i = sc.nextInt();
        }while(!isValid(i));
        sc.nextLine();
        Player p = tileFactory.producePlayer(i);
        p.initializeReader(r);
        gameManager.initPlayer(p);
        gameManager.createLevel(0);
        gameManager.startGame();
    }

    private boolean isValid(int i){
        int numPlayers = tileFactory.numberOfPlayers();
        if(i > numPlayers + 1 || numPlayers <= 0){
            return false;
        }  else return true;
    }

}
