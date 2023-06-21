package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.BarGenerator;
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
        else
        {
            this.fronEndCallbacks.displayMessage(BarGenerator.Color.RED + "Avenger’s Shield is on cooldown!" + BarGenerator.Color.RESET);
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        cooldown.takeAmount(cooldown.getCurrAmount());
    }

    @Override
    public int gainedAttack()
    {
        return super.gainedAttack() + 3*lvl;
    }
    @Override
    public int gainedHP()
    {
        return super.gainedHP() + 5*lvl;
    }
    @Override
    public int gainedDefence()
    {
        return super.gainedDefence() + lvl;
    }



    @Override
    public void onGameTick() {
        cooldown.addAmount( -1);
        super.onGameTick();
    }
}
