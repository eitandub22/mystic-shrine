package BusinessLayer.BoardStuff;

import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Tile;

import java.util.List;

public interface EnemiesInRange {
    public List<Enemy> get(Tile start, int range);
}
