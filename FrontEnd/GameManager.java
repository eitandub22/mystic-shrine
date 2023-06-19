package FrontEnd;

import BusinessLayer.BoardStuff.Board;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Tiles.TileFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private Board board;

    private final char PLAYER = '@';

    private final char EMPTY = '.';

    private final char WALL = '#';


    public GameManager(String[] levels) throws IOException {
        List<File> Files = null;
        LinkedList<Character> tile_chars = new LinkedList<>();
        int width = 0;
        int height = 0;
        if (0 < levels.length) {
            String sCurrentLine;
            for(int i = 0; i < levels.length; i++){
                BufferedReader br = new BufferedReader(new FileReader(new File(levels[i])));
                while ((sCurrentLine = br.readLine()) != null) {
                   height++;
                   for(int j = 0; j < sCurrentLine.length(); j++){
                       tile_chars.add(sCurrentLine.charAt(j));
                   }
                }
                height = sCurrentLine.length();
            }
            board = new Board(width, height);
            TileFactory tileFactory = new TileFactory(new BoardCallbacks(board), );

        }
        else {
            System.err.println("Invalid arguments count:" + levels.length);
            System.exit(0);
        }
    }
}
