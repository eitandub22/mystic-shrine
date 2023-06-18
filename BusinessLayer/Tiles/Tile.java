package BusinessLayer.Tiles;

import BusinessLayer.Position;
import BusinessLayer.Visited;
import BusinessLayer.Visitor;

public abstract class Tile implements Visited {
    protected char tile;
    protected Position position;

    protected Tile(char tile){
        this.tile = tile;
    }

    protected Tile(char tile, Position position){
        this.tile = tile;
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p){
        position = p;
    }

    @Override
    public String toString(){
        return String.valueOf(tile);
    }

    public abstract void onGameTick();
}
