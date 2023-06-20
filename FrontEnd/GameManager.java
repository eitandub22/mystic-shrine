package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Position;
import BusinessLayer.Tiles.TileFactory;

import java.io.*;
import java.util.*;

public class GameManager {

    private Board board;
    private TileFactory tileFactory;

    private final char PLAYER = '@';

    private final char EMPTY = '.';

    private final char WALL = '#';

    private List<Dictionary<Position, Character>> levelsStrings;


    public GameManager(String[] levels) throws IOException {
        List<File> Files = null;
        levelsStrings = new LinkedList<>();
        int width = 0;
        int height = 0;
        if (0 < levels.length) {
            String sCurrentLine = null;
            for(int i = 0; i < levels.length; i++){
                levelsStrings.add(new Hashtable<>());
                BufferedReader br = new BufferedReader(new FileReader(new File(levels[i])));
                while ((sCurrentLine = br.readLine()) != null) {
                   for(int j = 0; j < sCurrentLine.length(); j++){
                       levelsStrings.get(i).put(new Position(j,height), sCurrentLine.charAt(j));
                   }
                    height++;
                    width = sCurrentLine.length();
                }
            }
            board = new Board(width, height);

            //when making cli change
            tileFactory = new TileFactory(new BoardCallbacks(board), null);
        }
        else {
            System.err.println("Invalid arguments count:" + levels.length);
            System.exit(0);
        }
    }

    public void createLevel(int i, int plrInx){
        board.reset();
        Dictionary<Position, Character> currLevel = levelsStrings.get(i);
        Enumeration<Position> k = currLevel.keys();
        while (k.hasMoreElements()) {
            Position key = k.nextElement();
            switch (currLevel.get(key)){
                case PLAYER:
                    board.add(tileFactory.producePlayer(plrInx, key));
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

    public Board getBoard(){
        return board;
    }
}
