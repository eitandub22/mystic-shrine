package BusinessLayer;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Board {
    private int width;
    private int height;
    private FindTreeSet<Tile> tiles;

    public Board(int width, int height){
        tiles = new FindTreeSet<>(new TilePosComperator());
        this.height = height;
        this.width = width;
    }

    public Tile get(int x, int y) throws Exception {
        Empty demiTile = new Empty(x, y);
        Tile toFind = this.tiles.find(demiTile);
        if(demiTile.getPosition().compareTo(toFind.getPosition()) == 0) return toFind;
        throw new Exception("Can't get tile with coordinates: (" + x + "," + y + ")");
    }

/*    public void remove(Position p) {
        tiles.remove(new Empty(p));
        tiles.add(new Empty(p));
    }*/

    @Override
    public String toString() {
        tiles = (FindTreeSet<Tile>) tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return "";
    }

    public void tick(){
        ;
    }
}
