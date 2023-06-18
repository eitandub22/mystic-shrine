package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    Resource cooldown;
    public Warrior(String name, int healthCapacity, int attack, int defense, Integer cooldown) {
        super(name, healthCapacity, attack, defense);
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
        hp.updatePool(hp.getPool() + 5*lvl);
        attack += 2*lvl;
        defense += lvl;
    }

    @Override
    public void onGameTick() {
        cooldown.addAmount( -1);
    }
}
