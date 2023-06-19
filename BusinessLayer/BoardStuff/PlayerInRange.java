package BusinessLayer.BoardStuff;

import BusinessLayer.Tiles.Units.Player;
import BusinessLayer.Tiles.Tile;

public interface PlayerInRange {
    public Player get(Tile start, int range);
}
