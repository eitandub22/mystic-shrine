package FrontEnd;

import BusinessLayer.BarGenerator;
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
    private FrameContrller frameContrller;
    public static final char ERROR = 'E';

    public CLI(String levelsDir) throws IOException {
        m = this::displayMessage;
        r = this::readLine;
        gameManager = new GameManager(levelsDir, new FronEndCallbacks(this), this::endGame);
        sc = new Scanner(System.in);
        tileFactory = gameManager.getFactory();
        frameContrller = new FrameContrller();
    }

    public void displayMessage(String m) {
        System.out.println(m);
    }
    public String readLine(){
        return "" + frameContrller.readBuffer();
    }
    public void getPlayer(){
        tileFactory.showPlayers(m);
        char i = 0;
        String line = "";
        do {
            System.out.print("Select a player: ");
            line = sc.nextLine();
            i = line.length() > 0 ? line.charAt(0) : 0;
        }while(!isValid(i));

        Player p = tileFactory.producePlayer(Character.getNumericValue(i) - 1);
        gameManager.initPlayer(p);
        gameManager.initLevel(0);
        gameManager.startGame();
    }

    private boolean isValid(char i) {
        int numPlayers = tileFactory.numberOfPlayers();
        if (!Character.isDigit(i)) return false;
        if (Character.getNumericValue(i) > numPlayers + 1 || Character.getNumericValue(i) <= 0) {
            return false;
        } else return true;
    }

    public void endGame()
    {
        displayMessage(gameManager.boardString());
        displayMessage(BarGenerator.Color.RED + "YOU DIED" + BarGenerator.Color.RESET);

        sc.close();
        System.exit(0);
    }
}
