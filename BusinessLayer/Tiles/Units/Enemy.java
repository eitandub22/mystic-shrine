package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.BoardStuff.PlayerInRange;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Visitor;

public abstract class Enemy extends Unit{
    private Integer expVal;
    private boolean isDead;
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense, int expVal) {
        super(tile, name, healthCapacity, attack, defense);
        this.expVal = expVal;
        this.isDead = false;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath(Unit killer) {
        Empty empty = new Empty(this.position.getX(), this.position.getY());
        boardCallbacks.swap(this, empty);
        boardCallbacks.swap(empty, killer);
        this.isDead = true;
        messageCallback.send(String.format("%s was slain!. %s gained %d exp", name, killer.getName(), expVal));
    }
        @Override
    public void visit(Player p) {
        battle(p);
    }

    @Override
    public void visit(Enemy e) {
        ;
    }

    public abstract void onGameTick();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean isDead(){
        return this.isDead;
    }

    @Override
    public String toString()
    {
        return BarGenerator.Color.RED + super.toString() + BarGenerator.Color.RESET;
    }
}
