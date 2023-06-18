package BusinessLayer.Tiles.EnvironmentObjects;

import BusinessLayer.Position;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Visitor;

public class Wall extends Tile {
    public Wall(int x, int y){
        super('#', new Position(x,y));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
