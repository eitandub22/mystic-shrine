package BusinessLayer.Players;

import BusinessLayer.Resource;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Player;

import java.util.List;
import java.util.Random;

public class Mage extends Player {
    private Resource mana;

    private Integer cost;

    private Integer spellPow;

    private Integer hitsCount;

    private Integer range;

    protected Mage(char tile, String name, int healthCapacity, int attack, int defense, Integer manaPool, Integer manaCost, Integer spellPow, Integer hitsCount, Integer range) {
        super(tile, name, healthCapacity, attack, defense);
        mana = new Resource(manaPool);
        mana.addAmount((-1*mana.getCurrAmount() + mana.getPool()/4));
        cost = manaCost;
        this.spellPow = spellPow;
        this.hitsCount = hitsCount;
        this.range = range;
    }

    @Override
    public void castSpecial() {
        if(mana.getCurrAmount() >= cost){
            mana.takeAmount(cost);
            int hits = 0;
            Random rand = new Random();
            List<Enemy> enemyList = enemiesInRange.get(new Empty(position.getX() - range, position.getY() - range), new Empty(position.getX() + range, position.getY() + range));
            while(hits < hitsCount && !enemyList.isEmpty()){
                Enemy randomEnemy = enemyList.get(rand.nextInt(enemyList.size()));
                randomEnemy.takeDmg(spellPow, this);
                if(randomEnemy.isDead()) enemyList.remove(randomEnemy);
                hits++;
            }
        }
    }

    @Override
    public void onGameTick() {
        mana.addAmount(level);
    }

    @Override
    public void levelUp(){
        super.levelUp();
        mana.updatePool(mana.getPool() + 25*level);
        mana.addAmount(mana.getPool()/4);
        spellPow += 10*level;
    }
}
