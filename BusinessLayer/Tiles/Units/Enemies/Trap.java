package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Enemy;

public class Trap extends Enemy {

    protected int visTime;
    protected int invisTime;
    protected int ticks;
    protected boolean isVisible;
    public Trap(char tile, String name, int healthCapacity, int attack, int defense, int expVal, int visTime, int invisTime) {
        super(tile, name, healthCapacity, attack, defense, expVal);
        this.visTime = visTime;
        this.invisTime = invisTime;
        ticks = 0;
        isVisible = true;
    }

    @Override
    public void onGameTick() {
        this.ticks++;
        sortVisibility();
        if(playerInRange.get(this, 2) != null)
        {
            playerInRange.get(this, 2).takeDmg(this.attack, this);
        }
    }


    private void sortVisibility()
    {
        if(isVisible && ticks == visTime)
        {
            isVisible = false;
            ticks = 0;
        }
        else if (!isVisible && ticks == invisTime)
        {
            isVisible = true;
            ticks = 0;
        }
    }

    @Override
    public String toString()
    {
        if(isVisible)
        {
            return super.toString();
        }
        else
        {
            return ".";
        }
    }
}
