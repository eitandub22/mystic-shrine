package BusinessLayer.Tiles;

import BusinessLayer.Visitor;

public class Enemy extends Unit{
    private Integer expVal;
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense, int expVal) {
        super(tile, name, healthCapacity, attack, defense);
        this.expVal = expVal;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath(Unit killer) {
        Empty empty = new Empty(this.position.getX(), this.position.getY());
        swapCallback.swap(this, empty);
        swapCallback.swap(empty, killer);
        msgCallback.send(String.format("%s died. %s gained %d experience", name, killer.getName(), expVal));
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
}
