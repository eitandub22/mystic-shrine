package BusinessLayer.Tiles.Units.Classes;

import BusinessLayer.BarGenerator;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.Units.Enemy;
import BusinessLayer.Tiles.Units.Player;

import java.util.List;
import java.util.Random;

public class Mage extends Player {
    private Resource mana;

    private Integer cost;

    private Integer spellPow;

    private Integer hitsCount;

    private Integer range;

    public Mage(String name, int healthCapacity, int attack, int defense, Integer manaPool, Integer manaCost, Integer spellPow, Integer hitsCount, Integer range) {
        super(name, healthCapacity, attack, defense);
        mana = new Resource(manaPool);
        mana.addAmount(mana.getPool()/4);
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
            List<Enemy> enemyList = enemiesInRange.get(this, range);
            while(hits < hitsCount && !enemyList.isEmpty()){
                Enemy randomEnemy = enemyList.get(rand.nextInt(enemyList.size()));
                randomEnemy.takeDmg(spellPow, this);
                hits++;
            }
        }
        else
        {
            this.fronEndCallbacks.displayMessage(BarGenerator.Color.RED + "Out of Mana!" + BarGenerator.Color.RESET);
        }
    }

    @Override
    public void onGameTick() {
        super.onGameTick();
        mana.addAmount(lvl);
    }

    @Override
    public void levelUp(){
        fronEndCallbacks.displayMessage("Mana: " + mana.getPool() + " -> " + mana.getPool() + gainedMana());
        fronEndCallbacks.displayMessage("SpellPower: " + spellPow + " -> " + spellPow + gainedSpellPow());

        mana.updatePool(mana.getPool() + gainedMana());
        mana.addAmount(mana.getPool()/4);
        spellPow += gainedSpellPow();
        super.levelUp();
    }

    public int gainedMana()
    {
        return 25*lvl;
    }
    public int gainedSpellPow()
    {
        return 10*lvl;
    }
}
