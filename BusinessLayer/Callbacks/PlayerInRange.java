package BusinessLayer.Callbacks;

import BusinessLayer.Tiles.Player;
import BusinessLayer.Tiles.Tile;

public interface PlayerInRange {
    public Player get(Tile start, int range);
}
