package BusinessLayer;

import java.util.Comparator;

public class TilePosComperator implements Comparator<Tile> {
    public int compare(Tile t1, Tile t2)
    {
        if(t1.getPosition().getY() - t2.getPosition().getY() != 0)
        {
            return t1.getPosition().getY() - t2.getPosition().getY();
        }
        return t1.getPosition().getX() - t2.getPosition().getX();
    }
}
