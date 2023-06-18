package BusinessLayer;

import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;
import BusinessLayer.Tiles.EnvironmentObjects.Wall;

public interface Visitor {
    public void visit(Empty empty);
    public void visit(Wall wall);

    public void visit(Player player);

    public void visit(Enemy enemy);
}
