package BusinessLayer.BoardStuff;

import BusinessLayer.DataStructs.FindTreeSet;
import BusinessLayer.DataStructs.TilePosComperator;
import BusinessLayer.Position;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;
import BusinessLayer.Tiles.Tile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Board {
    private int width;
    private int height;
    private FindTreeSet<Tile> tiles;
    private LinkedList<Enemy> enemies;//dead enemies will remain, use carefully
    private Player player;

    public Board(int width, int height){
        tiles = new FindTreeSet<>(new TilePosComperator());
        enemies = new LinkedList<>();
        this.height = height;
        this.width = width;
    }

    public Player isPlayerInRange(Tile start, int range){
        return start.getPosition().distance(player.getPosition()) < range ? player : null;
    }
    public List<Enemy> getEnemiesInRange(Tile start, int range) {
        return this.enemies.stream().
                filter((Tile t) -> start.getPosition().distance(t.getPosition()) < range).
                collect(Collectors.toList());
    }

    public void add(Enemy enemy)
    {
        this.enemies.add(enemy);
        this.tiles.add(enemy);
    }
    public void add(Player player)
    {
        this.player = player;
        this.tiles.add(player);
    }
    public void add(Tile tile)
    {
        this.tiles.add(tile);
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
        StringBuilder stringBuilder = new StringBuilder();
        List<Tile> sorted = new LinkedList<>();
        for (Tile t: tiles) {
            sorted.add(t);
        }
        Collections.sort(sorted, new TilePosComperator());
        int counter = 0;
        for (Tile t: sorted) {
            if(counter == width){
                stringBuilder.append('\n');
                counter = 0;
            }
            stringBuilder.append(t.toString());
            counter++;
        }
        return stringBuilder.toString();
    }

    public void swap(Tile t1, Tile t2)
    {
        this.tiles.remove(t1);
        this.tiles.remove(t2);

        Position temp = t1.getPosition();

        t1.setPosition(t2.getPosition());
        t2.setPosition(temp);

        this.tiles.add(t1);
        this.tiles.add(t2);
    }


    public void tick(){
        player.onGameTick();
        for(Enemy enemy : enemies)
        {
            if(!enemy.isDead()) {
                enemy.onGameTick();
            }
        }
    }
    public Player getPlayer() {
        return player;
    }

    public boolean cleared(){
        for (Enemy e: enemies) {
            if(!e.isDead()) return false;
        }
        return true;
    }

}
