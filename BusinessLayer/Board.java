package BusinessLayer;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Callbacks.SwapCallback;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Tile;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private int width;
    private int height;
    private FindTreeSet<Tile> tiles;

    private SwapCallback swapCallback;

    private EnemiesInRange enemiesInRange;

    public Board(int width, int height){
        tiles = new FindTreeSet<>(new TilePosComperator());
        this.height = height;
        this.width = width;
        this.swapCallback = (Tile t1, Tile t2) -> this.swap(t1, t2);
        this.enemiesInRange = (Tile start, Tile end)-> this.getEnemiesInRange(start, end);
    }

    private List<Enemy> getEnemiesInRange(Tile start, Tile end) {
        List<Enemy> enemies = new LinkedList<>();
        for (int i = start.getPosition().getY(); i < end.getPosition().getY(); i++) {
            for (int j = start.getPosition().getX(); j < end.getPosition().getX() ; j++) {
                if(get(i,j).getTile() != '@' && get(i,j).getTile() != '.' && get(i,j).getTile() != '#'){
                    enemies.add((Enemy) get(i,j));
                }
            }
        }
        return enemies;
    }

    public Tile get(int x, int y){
        Empty demiTile = new Empty(x, y);
        Tile toFind = this.tiles.find(demiTile);
        return demiTile.getPosition().compareTo(toFind.getPosition()) == 0 ? toFind : null;
    }

    public void remove(int x, int y) {
        tiles.remove(new Empty(x, y));
        tiles.add(new Empty(x, y));
    }

    @Override
    public String toString() {
        tiles = (FindTreeSet<Tile>) tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return "";
    }

    private void swap(Tile t1, Tile t2)
    {
        this.remove(t1.getPosition().getX(), t1.getPosition().getY());
        this.remove(t2.getPosition().getX(), t2.getPosition().getY());

        Position temp = t1.getPosition();

        t1.setPosition(t2.getPosition());
        t2.setPosition(temp);

        this.tiles.add(t1);
        this.tiles.add(t2);
    }


    public void tick(){
        ;
    }
}
