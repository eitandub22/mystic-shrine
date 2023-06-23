package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.HeroicUnit;
import BusinessLayer.Tiles.Units.Player;

import java.util.Random;

public class Boss extends Enemy implements HeroicUnit {

    private Integer visionRange;

    private Integer frequency;

    private Integer combatTicks;
    public Boss(char tile, String name, int healthCapacity, int attack, int defense, int expVal, Integer visionRange, Integer frequency) {
        super(tile, name, healthCapacity, attack, defense, expVal);
        this.visionRange = visionRange;
        this.frequency = frequency;
        combatTicks = 0;
    }

    @Override
    public void onGameTick() {
        int dx = 0;
        int dy = 0;
        Player p = boardCallbacks.playerInRange(this, visionRange);
        if(p != null){
            if(combatTicks == frequency){
                combatTicks = 0;
                castSpecial();
            }
            else{
                combatTicks++;
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
        }
        else { //take a random step or stay at the same place
            combatTicks = 0;
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

    @Override
    public void castSpecial() {
        Player p = boardCallbacks.playerInRange(this, visionRange);
        fronEndCallbacks.displayMessage(name + " is using his special ability!");
        if(p != null) {
            p.takeDmg(attack, this);
        }
    }
}
