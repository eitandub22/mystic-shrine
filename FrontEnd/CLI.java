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
        char i;
        do {
            System.out.print("Select a player: ");
            i = sc.nextLine().charAt(0);
        }while(!isValid(i));
        Player p = tileFactory.producePlayer(Character.getNumericValue(i));
        p.initializeReader(r);
        gameManager.initPlayer(p);
        gameManager.createLevel(0);
        gameManager.startGame();
    }

    private boolean isValid(char i){
        int numPlayers = tileFactory.numberOfPlayers();
        if(!Character.isDigit(i)) return false;
        if(Character.getNumericValue(i) > numPlayers + 1 || Character.getNumericValue(i) <= 0){
            return false;
        }  else return true;
    }

}
