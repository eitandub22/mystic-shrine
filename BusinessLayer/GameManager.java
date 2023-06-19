package BusinessLayer;

import BusinessLayer.Tiles.TileFactory;

import java.io.*;
import java.util.List;

public class GameManager {

    private Board board;

    private final char PLAYER = '@';

    private final char EMPTY = '.';

    private final char WALL = '#';


    public GameManager(String[] levels) throws IOException {
        List<File> Files = null;
        TileFactory tileFactory = new TileFactory();
        int width = 0;
        int height = 0;
        if (0 < levels.length) {
            String sCurrentLine;
            for(int i = 0; i < levels.length; i++){
                BufferedReader br = new BufferedReader(new FileReader(new File(levels[i])));
                while ((sCurrentLine = br.readLine()) != null) {
                   height++;
                   for(int j = 0; j < sCurrentLine.length(); j++){
                        switch (sCurrentLine.charAt(j)){
                            case PLAYER:
                                
                                break;
                        }
                   }
                }
                height = sCurrentLine.length();
            }
            board = new Board(width, height);
        }
        else {
            System.err.println("Invalid arguments count:" + levels.length);
            System.exit(0);
        }
    }
}
