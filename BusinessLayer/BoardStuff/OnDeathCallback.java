package BusinessLayer.BoardStuff;

import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Unit;

public interface OnDeathCallback {
    public Empty dealWith(Enemy dead);
}
