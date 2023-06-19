package BusinessLayer.Tiles;

import BusinessLayer.BoardStuff.*;
import BusinessLayer.Position;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.EnvironmentObjects.Wall;
import BusinessLayer.Tiles.Units.Classes.Mage;
import BusinessLayer.Tiles.Units.Classes.Rogue;
import BusinessLayer.Tiles.Units.Classes.Warrior;
import BusinessLayer.Tiles.Units.Enemies.Monster;
import BusinessLayer.Tiles.Units.Enemies.Trap;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;
import FrontEnd.MessageCallback;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileFactory {
    private List<Supplier<Player>> playersList;
    private Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selected;

    private BoardCallbacks boardCallbacks;
    private MessageCallback messageCallback;

    public TileFactory(BoardCallbacks boardCallbacks, MessageCallback messageCallback){
        playersList = initPlayers();
        enemiesMap = initEnemies();

        this.boardCallbacks = boardCallbacks;
        this.messageCallback = messageCallback;
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3,25, 3),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),
                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
//                () -> new Boss('M', "The Mountain", 1000, 60, 25,  500, 6, 5),
//                () -> new Boss('C', "Queen Cersei", 100, 10, 10,1000, 1, 8),
//                () -> new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3),
//                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Warrior("The Hound", 400, 20, 6, 5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40, 2, 20),
                () -> new Rogue("Bronn", 250, 35, 3, 50)
                //() -> new Hunter("Ygritte", 220, 30, 2, 6)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }

    // TODO: Add additional callbacks of your choice

    public Enemy produceEnemy(char tile, Position position) {
        Enemy newEnm = this.enemiesMap.get(tile).get();
        newEnm.setPosition(position);
        newEnm.initialize(position, messageCallback, boardCallbacks);
        return newEnm;
    }

    public Player producePlayer(int idx, Position position){
        selected = playersList.get(idx).get();
        selected.initialize(position, messageCallback, boardCallbacks);
		return selected;
    }

    public Empty produceEmpty(Position position){
        return new Empty(position.getX(), position.getY());
    }

    public Wall produceWall(Position position){
        return new Wall(position.getX(), position.getY());
    }
}
