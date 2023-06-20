package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Position;
import BusinessLayer.Tiles.TileFactory;
import BusinessLayer.Tiles.Units.Player;

import java.io.*;
import java.util.*;

public class GameManager {

    private Board board;
    private TileFactory tileFactory;

    private final char PLAYER = '@';

    private final char EMPTY = '.';

    private final char WALL = '#';

    private List<Dictionary<Position, Character>> levelsStrings;

    private List<Board> boards;
    private int currLvl;

    public GameManager(String levelsDir, int ind) throws IOException {
        List<File> Files = null;
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
            tileFactory = new TileFactory(new BoardCallbacks(board), null);
            createLevel(currLvl, ind);
        }
        else {
            System.err.println("Invalid arguments count:" + levels.length);
            System.exit(0);
        }
    }

    public void createLevel(int i, int plrInx){
        Player p = null;
        p = board.getPlayer();
        board = boards.get(i);
        Dictionary<Position, Character> currLevel = levelsStrings.get(i);
        Enumeration<Position> k = currLevel.keys();
        while (k.hasMoreElements()) {
            Position key = k.nextElement();
            switch (currLevel.get(key)){
                case PLAYER:
                    if(p == null){
                        board.add(tileFactory.producePlayer(plrInx, key));
                    }
                    else{
                        p.setPosition(key);
                        board.add(p);
                    }
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
        currLvl++;
    }

    public Board getBoard(){
        return board;
    }
}
