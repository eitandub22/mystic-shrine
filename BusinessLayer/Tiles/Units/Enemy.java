package BusinessLayer.Tiles.Units;

import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Callbacks.GetTile;
import BusinessLayer.Callbacks.PlayerInRange;
import BusinessLayer.Callbacks.SwapCallback;
import BusinessLayer.Position;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Visitor;
import FrontEnd.MessageCallback;

public abstract class Enemy extends Unit{
    private Integer expVal;

    protected PlayerInRange playerInRange;
    private boolean isDead;
    protected GetTile getTile;
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
        swapCallback.swap(this, empty);
        swapCallback.swap(empty, killer);
        this.isDead = true;
        msgCallback.send(String.format("%s died. %s gained %d experience", name, killer.getName(), expVal));
    }

    public void initialize(Position position, MessageCallback messageCallback, SwapCallback swapCallback, EnemiesInRange enemiesInRange, GetTile getTile, PlayerInRange playerInRange)
    {
        super.initialize(position, messageCallback, swapCallback, enemiesInRange, getTile);
        this.playerInRange = playerInRange;
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
}
