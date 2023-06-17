package BusinessLayer.Tiles;

import BusinessLayer.Position;
import BusinessLayer.Resource;
import BusinessLayer.Callbacks.SwapCallback;
import BusinessLayer.Visitor;
import FrontEnd.MessageCallback;

public abstract class Unit extends Tile implements Visitor {
	protected String name;
    protected Resource hp;
    protected int attack;
    protected int defense;
    protected MessageCallback msgCallback;

    protected SwapCallback swapCallback;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.hp = new Resource(healthCapacity);
        this.hp.addAmount(healthCapacity);
        this.attack = attack;
        this.defense = defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback, SwapCallback swapCallback){
        this.position = position;
        this.msgCallback = messageCallback;
        this.swapCallback = swapCallback;
    }

    protected void battle(Unit u){
        u.takeDmg(rollAttack(), u);
    }

    protected void takeDmg(int atk, Unit attacker){
        int dmg = atk - rollDefence() > 0 ? atk - rollDefence() : 0;
        this.hp.takeAmount(dmg);
        if(hp.getCurrAmount() == 0)
        {
            onDeath(attacker);
        }
    }

    public int rollAttack(){
        return (int)Math.random() * (getAttack() + 1);
    }
    public int rollDefence(){
        return (int)Math.random() * (getDefense() + 1);
    }


	// Should be automatically called once the unit finishes its turn
    public abstract void processStep();
	
	// What happens when the unit dies
    public abstract void onDeath(Unit killer);

	// This unit attempts to interact with another tile.
    public void interact(Tile tile){
		tile.accept(this);
    }

    public void visit(Empty e){
		swapCallback.swap(this, e);
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void visit(Wall w){
        ;
    }
    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

    public abstract void onGameTick();
    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return hp.getCurrAmount();
    }

    public int getAttack()
    {
        return attack;
    }

    public int getDefense()
    {
        return defense;
    }
}
