package BusinessLayer;

import BusinessLayer.Resource;

public abstract class Unit extends Tile {
	private String name;
    private char tle;
    private Resource hp;
    private int attack;
    private int defence;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.hp = 
    }

    protected void initialize(Position position, MessageCallback messageCallback){
        ...
    }
	
    protected int attack(){
		...
    }

    public int defend(){
        ...
    }

	// Should be automatically called once the unit finishes its turn
    public abstract void processStep();
	
	// What happens when the unit dies
    public abstract void onDeath();

	// This unit attempts to interact with another tile.
    public void interact(Tile tile){
		...
    }

    public void visit(Empty e){
		...
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

	// Combat against another unit.
    protected void battle(Unit u){
        ...
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }
}
