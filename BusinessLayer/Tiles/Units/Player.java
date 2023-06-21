package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.BoardStuff.EnemiesInRange;
import BusinessLayer.Visitor;

public abstract class Player extends Unit implements HeroicUnit{
    protected Integer xp;
    protected Integer lvl;
    protected static final char SPECIAL = 'e';

    protected Player(String name, int healthCapacity, int attack, int defense) {
        super('@', name, healthCapacity, attack, defense);
        xp = 0;
        lvl = 1;
    }
    @Override
    public void kill(Mortal victim)
    {
        victim.onDeath(this);
    }
    @Override
    public void onDeath(Enemy killer) {
        fronEndCallbacks.displayMessage(this.name + "was slain by " + killer.getName());
        fronEndCallbacks.displayMessage("GAME OVER");
        tile = 'X';
        System.exit(0);//change to deathcallback
    }
    @Override
    public void onDeath(Player player)
    {
        ;
    }

    @Override
    public void visit(Player p) {
        ;
    }

    @Override
    public void visit(Enemy e) {
        battle(e);
    }


    public void levelUp(){
        if(xp < lvl*50)
        {
            return;
        }

        fronEndCallbacks.displayMessage("HP: " + hp.getPool() + " -> " + (hp.getPool()+gainedHP()));
        fronEndCallbacks.displayMessage("Atk: " + attack + " -> " + (attack+gainedAttack()));
        fronEndCallbacks.displayMessage("Defence: " + defense + " -> " + (defense+gainedDefence()));

        xp -= 50*lvl;
        lvl++;
        hp.updatePool(hp.getPool() + gainedHP());
        hp.addAmount(hp.getPool());
        attack += gainedAttack();
        defense += gainedDefence();
    }

    public int gainedAttack()
    {
        return 4*lvl;
    }
    public int gainedHP()
    {
        return 10*lvl;
    }
    public int gainedDefence()
    {
        return lvl;
    }


    public void addExp(int val){
        xp += val;
        levelUp();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }



    public void onGameTick()
    {
        fronEndCallbacks.displayMessage(describe());
        char playerMove = 'E';
        boolean moved = false;
        String line = "";
        while(!moved) {
            fronEndCallbacks.displayMessage("Enter your move:");
            line = fronEndCallbacks.readLine();
            playerMove = line.length() > 0 ? line.charAt(0) : 'E';
            switch (playerMove) {
                case SPECIAL:
                    castSpecial();
                    moved = true;
                    break;
                default:
                    moved = move(playerMove);
            }
        }
    }

    @Override
    public String toString()
    {
        return BarGenerator.Color.GREEN + super.toString() + BarGenerator.Color.RESET;
    }

    @Override
    public String describe(){

        return String.format(super.describe() + "\t\tLevel: %d\n\t\tExperience: %d/%d", lvl, xp, lvl*50);
    }
}
