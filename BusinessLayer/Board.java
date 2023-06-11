package BusinessLayer;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Board {
    private int width;
    private int height;
    private HashMap<Position, Tile> tiles;

    public Board(int width, int height){
        tiles = new HashMap<>();
        this.height = height;
        this.width = width;
    }

    public Tile get(int x, int y) throws Exception {
        for(Position p : tiles.keySet()){
            if (p.equals(new Position(x,y))){
                return tiles.get(p);
            }
        }
        throw new Exception("Can't get tile with coordinates: (" + x + "," + y + ")");
    }

/*    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }*/

    @Override
    public String toString() {
        tiles = (HashMap<Position, Tile>) tiles.values().stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return "";
    }

    public void tick(){
        ;
    }
}
