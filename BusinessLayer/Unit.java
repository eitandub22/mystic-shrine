package BusinessLayer;

import BusinessLayer.Resource;
import FrontEnd.MessageCallback;

public abstract class Unit extends Tile {
	private String name;
    private Resource hp;
    private int attack;
    private int defence;
    private MessageCallback msgCallbck;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.hp = new Resource(healthCapacity);
        this.hp.addAmount(healthCapacity);
        this.attack = attack;
        this.defence = defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback){
        this.position = position;
        this.msgCallbck = messageCallback;
    }
	
    protected int attack(){
		return 0;
    }

    public int rollAttack(){
        return 0;
    }
    public int rollDefence(){
        return 0;
    }


	// Should be automatically called once the unit finishes its turn
    public abstract void processStep();
	
	// What happens when the unit dies
    public abstract void onDeath();

	// This unit attempts to interact with another tile.
    public void interact(Tile tile){
		tile.accept(this);
    }

    public void visit(Empty e){
		//
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

	// Combat against another unit.
    protected void battle(Unit u){
        //
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }
}
