package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;

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
        if (arrows.getCurrAmount() == 0) {
            arrows.takeAmount(1);
            List<Enemy> enemyList = boardCallbacks.getEnemiesInRange(this, range + 1);
            if(enemyList.size() == 0) return;
            int minRange = Integer.MAX_VALUE;
            Enemy closest = null;
            for (Enemy e: enemyList) {
                int currDistance = (int) this.position.distance(e.getPosition());
                if(currDistance < minRange){
                    minRange = currDistance;
                    closest = e;
                }
            }
            closest.takeDmg(attack, this);
        }

    }

    @Override
    public void levelUp(){
        fronEndCallbacks.displayMessage("Arrows: " + arrows + " -> " + (arrows.getPool() + gainedArrows()));

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
        return String.format(super.describe() + "  Arrows: %d/%d", arrows.getCurrAmount(), arrows.getPool());
    }
}
