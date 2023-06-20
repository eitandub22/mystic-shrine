package BusinessLayer.BoardStuff;

import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;

public class BoardCallbacks {
    private EnemiesInRange enemiesInRange;
    private GetTile getTile;
    private PlayerInRange playerInRange;
    private SwapCallback swapCallback;

    public BoardCallbacks(Board board)
    {
        this.swapCallback = (Tile t1, Tile t2) -> board.swap(t1, t2);
        this.enemiesInRange = (Tile start, int range)-> board.getEnemiesInRange(start, range);
        this.playerInRange = (Tile start, int range)-> board.isPlayerInRange(start, range);
        this.getTile = (int x, int y) -> board.get(x, y);
    }

    public void swap(Tile t1, Tile t2)
    {
        swapCallback.swap(t1, t2);
    }

    public Tile getTile(int x, int y) {
        return getTile.get(x, y);
    }
    public List<Enemy> enemiesInRange(Tile start, int range)
    {
        return enemiesInRange.get(start,range);
    }

    public Player playerInRange(Tile start, int range){
        return playerInRange.get(start, range);
    }

}

