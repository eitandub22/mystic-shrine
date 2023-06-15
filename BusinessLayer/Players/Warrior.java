package BusinessLayer.Players;

import BusinessLayer.Resource;
import BusinessLayer.Tiles.Player;
import BusinessLayer.Visitor;

public class Warrior extends Player {
    Resource cooldown;
    protected Warrior(char tile, String name, int healthCapacity, int attack, int defense, Integer cooldown) {
        super(tile, name, healthCapacity, attack, defense);
        this.cooldown = new Resource(cooldown);
    }

    @Override
    public void castSpecial() {
        if(cooldown.getCurrAmount() == 0){
            cooldown.addAmount(cooldown.getPool());
            hp.addAmount(10*defense);

        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        cooldown.addAmount(-1*cooldown.getCurrAmount());
        hp.updatePool(hp.getPool() + 5*level);
        attack += 2*level;
        defense += level;
    }

    @Override
    public void onGameTick() {
        cooldown.addAmount( -1);
    }
}
