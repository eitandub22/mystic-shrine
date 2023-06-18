package BusinessLayer.Enemies;

import BusinessLayer.Callbacks.PlayerInRange;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Player;

public class Monster extends Enemy {
    Integer visionRange;
    protected Monster(char tile, String name, int healthCapacity, int attack, int defense, int expVal, PlayerInRange player, Integer visionRange) {
        super(tile, name, healthCapacity, attack, defense, expVal, player);
        this.visionRange = visionRange;
    }

    @Override
    public void onGameTick() {
        int dx = 0;
        int dy = 0;
        Player p = playerInRange.get(this, visionRange);
        if(p != null){
            dx = this.getPosition().getX() - p.getPosition().getX();
            dy = this.getPosition().getY() - p.getPosition().getY();
            if(Math.abs(dx) > Math.abs(dy)){
                if(dx > 0) ;//move one tile left
                else ;//move one tile right
            }
            else{
                if(dy > 0) ;//move one tile up
                else ;//move one tile down
            }
        }
        else ;//take a random step or stay at the same place
    }
}
