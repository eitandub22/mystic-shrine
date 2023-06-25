package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Position;
import BusinessLayer.Tiles.TileFactory;
import BusinessLayer.Tiles.Units.Player;

import java.io.*;
import java.util.*;

public class GameManager{

    private Board board;
    private TileFactory tileFactory;

    private int turn;
    private final char PLAYER = '@';

    private final char EMPTY = '.';

    private final char WALL = '#';

    private List<Dictionary<Position, Character>> levelsStrings;

    public FronEndCallbacks fronEndCallbacks;

    private GameOverCallback gameOverCallback;
    private List<Board> boards;
    private int currLvl;
    private Player player;

    public GameManager(String levelsDir, FronEndCallbacks fronEndCallbacks, GameOverCallback gameOverCallback) throws IOException {
        List<File> Files = null;
        turn = 1;
        this.gameOverCallback = gameOverCallback;
        this.fronEndCallbacks = fronEndCallbacks;
        levelsStrings = new LinkedList<>();
        currLvl = 0;
        boards = new LinkedList<>();
        int width = 0;
        int height = 0;
        File folder = new File(levelsDir);
        File[] levels = folder.listFiles();
        if (0 < levels.length) {
            String sCurrentLine = null;
            for(int i = 0; i < levels.length; i++){
                levelsStrings.add(new Hashtable<>());
                BufferedReader br = new BufferedReader(new FileReader(levels[i]));
                while ((sCurrentLine = br.readLine()) != null) {
                   for(int j = 0; j < sCurrentLine.length(); j++){
                       levelsStrings.get(i).put(new Position(j,height), sCurrentLine.charAt(j));
                   }
                   height++;
                   width = sCurrentLine.length();
                }
                boards.add(new Board(width,height));
                height = 0;
                width = 0;
            }
            board = boards.get(currLvl);
            tileFactory = new TileFactory(new BoardCallbacks(board), fronEndCallbacks);
        }
        else {
            System.err.println("Invalid arguments count:" + levels.length);
            System.exit(0);
        }
    }

    public void initLevel(int i){
        board = boards.get(i);
        Dictionary<Position, Character> currLevel = levelsStrings.get(i);
        BoardCallbacks boardCallbacks = new BoardCallbacks(board);
        tileFactory = new TileFactory(boardCallbacks, fronEndCallbacks);
        Enumeration<Position> k = currLevel.keys();
        while (k.hasMoreElements()) {
            Position key = k.nextElement();
            switch (currLevel.get(key)){
                case PLAYER:
                    tileFactory.initializePlayer(player, key, boardCallbacks, gameOverCallback);
                    board.add(player);
                    break;
                case WALL:
                    board.add(tileFactory.produceWall(key));
                    break;
                case EMPTY:
                    board.add(tileFactory.produceEmpty(key));
                    break;
                default:
                    board.add(tileFactory.produceEnemy(currLevel.get(key), key));
                    break;
            }
        }
    }

    public TileFactory getFactory() {
        return tileFactory;
    }

    public void startGame() {
        while(currLvl < boards.size()){
            fronEndCallbacks.displayMessage(boardString());
            fronEndCallbacks.displayMessage("Turn: " + turn);
            if(board.cleared()){
                currLvl++;
                if(currLvl < boards.size()){
                    initLevel(currLvl);
                }
            }
            board.tick();
            turn++;
        }


        fronEndCallbacks.displayMessage("YOU WIN!");
    }

    public String boardString()
    {
        return board.toString();
    }


    public void initPlayer(Player p){
        player = p;
    }
}
