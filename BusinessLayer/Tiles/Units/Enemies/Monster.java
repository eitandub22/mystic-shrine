package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Callbacks.GetTile;
import BusinessLayer.Callbacks.PlayerInRange;
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
        Player p = playerInRange.get(this, visionRange);
        if(p != null){
            dx = this.getPosition().getX() - p.getPosition().getX();
            dy = this.getPosition().getY() - p.getPosition().getY();
            if(Math.abs(dx) > Math.abs(dy)){
                if(dx > 0) interact(getTile.get(this.position.getX() - 1, this.position.getY()));//move one tile left
                else interact(getTile.get(this.position.getX() + 1, this.position.getY()));//move one tile right
            }
            else{
                if(dy > 0) interact(getTile.get(this.position.getX(), this.position.getY() - 1));//move one tile up
                else interact(getTile.get(this.position.getX(), this.position.getY() + 1));//move one tile down
            }
        }
        else { //take a random step or stay at the same place
            Random random = new Random();
            int option = random.nextInt(5);
            switch (option){
                case 0://stay in place
                    break;
                case 1:
                    interact(getTile.get(this.position.getX() - 1, this.position.getY()));
                    break;
                case 2:
                    interact(getTile.get(this.position.getX() + 1, this.position.getY()));
                    break;
                case 3:
                    interact(getTile.get(this.position.getX(), this.position.getY() - 1));
                    break;
                case 4:
                    interact(getTile.get(this.position.getX() - 1, this.position.getY() + 1));
                    break;
            }
        }
    }
}
