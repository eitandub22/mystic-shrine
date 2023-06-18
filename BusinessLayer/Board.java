package BusinessLayer;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Callbacks.GetTile;
import BusinessLayer.Callbacks.PlayerInRange;
import BusinessLayer.Callbacks.SwapCallback;
import BusinessLayer.DataStructs.FindTreeSet;
import BusinessLayer.DataStructs.TilePosComperator;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;
import BusinessLayer.Tiles.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private int width;
    private int height;
    private FindTreeSet<Tile> tiles;

    private Player player;
    private SwapCallback swapCallback;

    private EnemiesInRange enemiesInRange;

    private PlayerInRange playerInRange;

    private GetTile getTile;

    public Board(int width, int height){
        tiles = new FindTreeSet<>(new TilePosComperator());
        this.height = height;
        this.width = width;
        this.swapCallback = (Tile t1, Tile t2) -> this.swap(t1, t2);
        this.enemiesInRange = (Tile start, int range)-> this.getEnemiesInRange(start, range);
        this.playerInRange = (Tile start, int range)-> this.getPlayerInRange(start, range);
        this.getTile = (int x, int y) -> this.get(x, y);
    }

    private Player getPlayerInRange(Tile start, int range){
        return this.tiles.stream().
                filter((Tile t) -> t.getTile() == '@' && start.getPosition().distance(t.getPosition()) <= range).
                map((Tile t) -> (Player)t).
                collect(Collectors.toList()).get(0);
    }
    private List<Enemy> getEnemiesInRange(Tile start, int range) {
        return this.tiles.stream().
                filter((Tile t) -> isMonster(t) && start.getPosition().distance(t.getPosition()) <= range).
                map((Tile t) -> (Enemy)t).
                collect(Collectors.toList());
    }

    private boolean isMonster(Tile tile){
        return (tile.getTile() != '@' && tile.getTile() != '.' && tile.getTile() != '#');
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
        player.onGameTick();
        for(Tile t : tiles)
        {
            if(t.getTile() != '@')
            {
                
            }
        }
    }
}
