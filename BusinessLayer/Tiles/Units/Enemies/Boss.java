package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.HeroicUnit;
import BusinessLayer.Tiles.Units.Player;

import java.util.Random;

public class Boss extends Monster implements HeroicUnit {
    private Integer frequency;
    private Integer combatTicks;
    public Boss(char tile, String name, int healthCapacity, int attack, int defense, int expVal, Integer visionRange, Integer frequency) {
        super(tile, name, healthCapacity, attack, defense, expVal, visionRange);
        this.frequency = frequency;
        combatTicks = 0;
    }

    @Override
    public void onGameTick() {
        Player p = boardCallbacks.playerInRange(this, visionRange);
        if(p != null && combatTicks >= frequency)
        {
            combatTicks = 0;
            castSpecial();
            return;
        }

        combatTicks += p != null ? 1 : -combatTicks;
        super.onGameTick();
    }

    @Override
    public void castSpecial() {
        Player p = boardCallbacks.playerInRange(this, visionRange);
        fronEndCallbacks.displayMessage(name + " is using his special generic ability!");
        if(p != null) {
            p.takeDmg(attack, this);
        }
    }
}
