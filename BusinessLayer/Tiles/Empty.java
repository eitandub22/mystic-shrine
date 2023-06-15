package BusinessLayer.Tiles;

import BusinessLayer.Position;
import BusinessLayer.Visitor;

public class Empty extends Tile{

    public Empty(int x, int y){
        super('.', new Position(x,y));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
