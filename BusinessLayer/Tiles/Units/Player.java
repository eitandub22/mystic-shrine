package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.BoardStuff.EnemiesInRange;
import BusinessLayer.Visitor;

public abstract class Player extends Unit implements HeroicUnit{
    protected Integer xp;
    protected Integer lvl;
    protected static final char SPECIAL = 'e';

    protected EnemiesInRange enemiesInRange;
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

        fronEndCallbacks.displayMessage("HP: " + hp.getPool() + " -> " + hp.getPool()+gainedHP());
        fronEndCallbacks.displayMessage("Atk: " + attack + " -> " + attack+gainedHP());
        fronEndCallbacks.displayMessage("Defence: " + defense + " -> " + defense+gainedHP());

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
        char playerMove = 'a';
        boolean moved = false;
        while(!moved) {
            fronEndCallbacks.displayMessage("Enter your move:");
            playerMove = fronEndCallbacks.readLine().charAt(0);
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
}
