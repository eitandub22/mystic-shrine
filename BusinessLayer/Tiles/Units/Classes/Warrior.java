package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.BarGenerator;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    Resource cooldown;
    public Warrior(String name, int healthCapacity, int attack, int defense, Integer cooldown) {
        super(name, healthCapacity, attack, defense);
        this.cooldown = new Resource(cooldown);
        this.cooldown.addAmount(cooldown);
    }

    @Override
    public void castSpecial() {
        if(cooldown.getCurrAmount() == cooldown.getPool()){
            cooldown.takeAmount(cooldown.getPool());
            hp.addAmount(10*defense);
            List<Enemy> enemyList = boardCallbacks.getEnemiesInRange(this, 3);
            if(enemyList.size() > 0) {
                Random rand = new Random();
                Enemy randomEnemy = enemyList.get(rand.nextInt(enemyList.size()));
                int dmg = hp.getPool() / 10;
                fronEndCallbacks.displayMessage("Avengers Shield! deal " + dmg + " DMG");
                randomEnemy.takeDmg(dmg, this);
            }
        }
        else
        {
            this.fronEndCallbacks.displayMessage(BarGenerator.Color.RED + "Avenger’s Shield is on cooldown!" + BarGenerator.Color.RESET);
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        cooldown.addAmount(cooldown.getPool());
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
    public String describe(){
        BarGenerator bg = new BarGenerator();
        return String.format(super.describe() + "\n%s Cooldown: %d/%d",
                bg.genBar(cooldown.getCurrAmount(), cooldown.getPool(), ' ', BarGenerator.Color.WHITE_BACKGROUND),
                cooldown.getCurrAmount(),
                cooldown.getPool());
    }

    @Override
    public void onGameTick() {
        cooldown.addAmount( 1);
        super.onGameTick();
    }
}
