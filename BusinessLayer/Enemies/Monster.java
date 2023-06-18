package BusinessLayer.Enemies;

import BusinessLayer.Tiles.Enemy;

public class Monster extends Enemy {
    Integer visionRange;
    protected Monster(char tile, String name, int healthCapacity, int attack, int defense, int expVal, Integer visionRange) {
        super(tile, name, healthCapacity, attack, defense, expVal);
        this.visionRange = visionRange;
    }

    @Override
    public void onGameTick() {

    }
}
