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
        this.pierce = 0.1;
        energy = new Resource(MAX_AMOUNT);
        energy.addAmount(MAX_AMOUNT);
    }

    @Override
    public void castSpecial() {
        if(energy.getCurrAmount() >= cost){
            energy.takeAmount(cost);
            List<Enemy> enemyList = boardCallbacks.getEnemiesInRange(this, 2);
            fronEndCallbacks.displayMessage("Fan Of Knives! deal " + attack + " DMG per hit");
            for (Enemy enemy: enemyList) {
                enemy.takeDmg(attack, this);
            }
        }
        else
        {
            this.fronEndCallbacks.displayMessage(BarGenerator.Color.RED + "Out of Energy!" + BarGenerator.Color.RESET);
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
    }

    @Override
    public int gainedAttack()
    {
        return super.gainedAttack() + 3*lvl;
    }

    @Override
    public double gainedPierce()
    {
        return 0.05;
    }


    @Override
    public String describe(){
        BarGenerator bg = new BarGenerator();
        return String.format(super.describe() + "\n%s Energy: %d/%d",
                bg.genBar(energy.getCurrAmount(), energy.getPool(), ' ', BarGenerator.Color.YELLOW_BACKGROUND),
                energy.getCurrAmount(),
                energy.getPool());
    }
}
