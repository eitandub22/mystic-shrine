package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.BarGenerator;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;

public class Rogue extends Player {
    Integer cost;

    Resource energy;

    private final Integer MAX_AMOUNT = 100;

    public Rogue(String name, int healthCapacity, int attack, int defense, Integer cost) {
        super(name, healthCapacity, attack, defense);
        this.cost = cost;
        energy = new Resource(MAX_AMOUNT);
        energy.addAmount(MAX_AMOUNT);
    }

    @Override
    public void castSpecial() {
        if(energy.getCurrAmount() >= cost){
            energy.takeAmount(cost);
            List<Enemy> enemyList = enemiesInRange.get(this, 2);
            for (Enemy enemy: enemyList) {
                enemy.takeDmg(attack, this);
            }
        }
        else
        {
            this.messageCallback.send(BarGenerator.Color.RED + "Out of Energy!" + BarGenerator.Color.RESET);
        }
    }

    @Override
    public void onGameTick() {
        super.onGameTick();
        energy.addAmount(MAX_AMOUNT/10);
    }

    @Override
    public void levelUp(){
        super.levelUp();
        energy.addAmount(MAX_AMOUNT);
        attack += 3*lvl;
    }
}
