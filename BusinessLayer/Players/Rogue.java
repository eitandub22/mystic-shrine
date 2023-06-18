package BusinessLayer.Players;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Player;

import java.util.List;
import java.util.Random;

public class Rogue extends Player {
    Integer cost;

    Resource energy;

    private final Integer MAX_AMOUNT = 100;

    protected Rogue(char tile, String name, int healthCapacity, int attack, int defense, EnemiesInRange enemiesInRange, Integer cost) {
        super(tile, name, healthCapacity, attack, defense, enemiesInRange);
        this.cost = cost;
        energy = new Resource(MAX_AMOUNT);
        energy.addAmount(MAX_AMOUNT);
    }

    @Override
    public void castSpecial() {
        if(energy.getCurrAmount() >= cost){
            energy.takeAmount(cost);
            Random rand = new Random();
            List<Enemy> enemyList = enemiesInRange.get(this, 2);
            for (Enemy enemy: enemyList) {
                enemy.takeDmg(attack, this);
            }
        }
    }

    @Override
    public void onGameTick() {
        energy.addAmount(MAX_AMOUNT/10);
    }

    @Override
    public void levelUp(){
        super.levelUp();
        energy.addAmount(MAX_AMOUNT);
        attack += 3*level;
    }
}
