package BusinessLayer;

import java.util.Comparator;

public class TilePosComperator implements Comparator<Tile> {
    public int compare(Tile t1, Tile t2)
    {
        return t1.getPosition().compareTo(t2.getPosition());
    }
}
