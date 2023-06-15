package BusinessLayer.Tiles;

import BusinessLayer.Visitor;

public abstract class Player extends Unit{
    protected Integer exp;
    protected Integer level;
    protected Player(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile, name, healthCapacity, attack, defense);
        exp = 0;
        level = 1;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath(Unit killer) {
        msgCallback.send("GAME OVER");
        tile = 'X';
        //call endGame
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
        exp -= 50*level;
        level++;
        hp.updatePool(hp.getPool() + 10*level);
        hp.addAmount(hp.getPool());
        attack += 4*level;
        defense += level;
    }

    public void addExp(int val){
        exp += val;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public abstract void castSpecial();
}
