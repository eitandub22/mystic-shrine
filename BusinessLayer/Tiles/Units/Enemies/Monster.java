package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.Random;

public class Monster extends Enemy {
    Integer visionRange;
    public Monster(char tile, String name, int healthCapacity, int attack, int defense, int expVal, Integer visionRange) {
        super(tile, name, healthCapacity, attack, defense, expVal);
        this.visionRange = visionRange;
    }

    @Override
    public void onGameTick() {
        int dx = 0;
        int dy = 0;
        Player p = boardCallbacks.playerInRange(this, visionRange);
        if(p != null){
            dx = this.getPosition().getX() - p.getPosition().getX();
            dy = this.getPosition().getY() - p.getPosition().getY();
            if(Math.abs(dx) > Math.abs(dy)){
                if(dx > 0) move(LEFT);
                else move(RIGHT);
            }
            else{
                if(dy > 0) move(UP);
                else move(DOWN);
            }
        }
        else { //take a random step or stay at the same place
            Random random = new Random();
            int option = random.nextInt(5);
            switch (option){
                case 0://stay in place
                    break;
                case 1:
                    move(UP);
                    break;
                case 2:
                    move(DOWN);
                    break;
                case 3:
                    move(LEFT);
                    break;
                case 4:
                    move(RIGHT);
                    break;
            }
        }
    }
}
