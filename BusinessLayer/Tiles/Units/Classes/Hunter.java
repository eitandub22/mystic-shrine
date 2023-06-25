package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.BarGenerator;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player {

    private Resource arrows;

    private Integer range;

    private Integer ticksCount;
    public Hunter(String name, int healthCapacity, int attack, int defense, Integer range) {
        super(name, healthCapacity, attack, defense);
        arrows = new Resource(10*lvl);
        this.range = range;
        ticksCount = 0;
    }

    @Override
    public void onGameTick() {
        super.onGameTick();
        if (ticksCount == 10) {
            arrows.addAmount(lvl);
            ticksCount = 0;
        }
        else ticksCount++;
    }

    @Override
    public void castSpecial() {
        if (arrows.getCurrAmount() > 0) {
            arrows.takeAmount(1);
            fronEndCallbacks.displayMessage("Shoot! deal " + attack + " DMG");
            List<Enemy> enemyList = boardCallbacks.getEnemiesInRange(this, range + 1).stream().
                    sorted((Enemy e1, Enemy e2) -> (int)(e1.getPosition().distance(position)-e2.getPosition().distance(position))).
                    collect(Collectors.toList());
            if(enemyList.size() == 0) return;
            enemyList.get(0).takeDmg(attack, this);
        }

    }

    @Override
    public void levelUp(){
        arrows.updatePool(arrows.getPool() + gainedArrows());
        arrows.addAmount(gainedArrows());
        attack += gainedAttack();
        defense += gainedDefence();
        super.levelUp();
    }

    private int gainedArrows(){
        return 10*lvl;
    }
    @Override
    public int gainedAttack(){
        return 2*lvl;
    }

    @Override
    public int gainedDefence(){
        return lvl;
    }

    @Override
    public String describe(){
        BarGenerator bg = new BarGenerator();
        return String.format(super.describe() + "\n%s Arrows: %d/%d",
                bg.genBar(arrows.getCurrAmount(), arrows.getPool(), '|', BarGenerator.Color.WHITE),
                arrows.getCurrAmount(), arrows.getPool());
    }
}
