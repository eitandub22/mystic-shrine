package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Visitor;

public abstract class Enemy extends Unit{
    private int expVal;
    private boolean isDead;
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense, int expVal) {
        super(tile, name, healthCapacity, attack, defense);
        this.expVal = expVal;
        this.isDead = false;
    }

    @Override
    public void kill(Mortal victim)
    {
        victim.onDeath(this);
    }
    @Override
    public void onDeath(Enemy killer) {
        ;
    }
    @Override
    public void onDeath(Player killer)
    {
        boardCallbacks.swap(new Empty( position.getX(), position.getY()), boardCallbacks.dealWithDying(this));
        this.isDead = true;
        fronEndCallbacks.displayMessage(String.format("%s was slain!. %s gained %d exp", name, killer.getName(), getExpVal()));
        killer.addExp(expVal);
    }
        @Override
    public void visit(Player p) {
        battle(p);
    }

    @Override
    public void visit(Enemy e) {
        ;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean isDead(){
        return this.isDead;
    }

    public int getExpVal()
    {
        return expVal;
    }

    @Override
    public String toString()
    {
        return BarGenerator.Color.RED + super.toString() + BarGenerator.Color.RESET;
    }
}
