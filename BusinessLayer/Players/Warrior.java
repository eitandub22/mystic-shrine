package BusinessLayer.Players;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Player;
import BusinessLayer.Visitor;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    Resource cooldown;
    protected Warrior(char tile, String name, int healthCapacity, int attack, int defense, EnemiesInRange enemiesInRange, Integer cooldown) {
        super(tile, name, healthCapacity, attack, defense, enemiesInRange);
        this.cooldown = new Resource(cooldown);
    }

    @Override
    public void castSpecial() {
        if(cooldown.getCurrAmount() == 0){
            cooldown.addAmount(cooldown.getPool());
            hp.addAmount(10*defense);
            List<Enemy> enemyList = enemiesInRange.get(this, 3);
            Random rand = new Random();
            Enemy randomEnemy = enemyList.get(rand.nextInt(enemyList.size()));
            randomEnemy.takeDmg(hp.getPool()/10, this);
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        cooldown.takeAmount(cooldown.getCurrAmount());
        hp.updatePool(hp.getPool() + 5*level);
        attack += 2*level;
        defense += level;
    }

    @Override
    public void onGameTick() {
        cooldown.addAmount( -1);
    }
}
