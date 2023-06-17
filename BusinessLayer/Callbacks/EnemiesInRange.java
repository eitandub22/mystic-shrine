package BusinessLayer.Callbacks;

import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Tile;

import java.util.List;

public interface EnemiesInRange {
    public List<Enemy> get(Tile rangeStart, Tile rangeEnd);
}
