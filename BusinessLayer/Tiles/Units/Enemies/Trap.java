package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

public class Trap extends Enemy {

    private Integer visTime;

    private Integer invisTime;

    private Integer ticksCount;

    private boolean visible;
    protected Trap(char tile, String name, int healthCapacity, int attack, int defense, int expVal, Integer visibility, Integer invisibility) {
        super(tile, name, healthCapacity, attack, defense, expVal);
        this.visTime = visibility;
        this.invisTime = invisibility;
        ticksCount = 0;
        visible = true;
    }

    @Override
    public void onGameTick() {
        visible = ticksCount < visTime;
        if(ticksCount  == (visTime + invisTime)){
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
        Player p = playerInRange.get(this, 2);
        if(p != null){
            interact(p);
        }
    }
}
