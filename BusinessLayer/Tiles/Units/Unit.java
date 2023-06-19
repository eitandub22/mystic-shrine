package BusinessLayer.Tiles.Units;

import BusinessLayer.*;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.EnvironmentObjects.Wall;
import BusinessLayer.Tiles.Tile;
import FrontEnd.MessageCallback;

public abstract class Unit extends Tile implements Visitor {
	protected String name;
    protected Resource hp;
    protected int attack;
    protected int defense;
    protected BoardCallbacks boardCallbacks;
    protected MessageCallback messageCallback;

    protected static final char UP = 'w';
    protected static final char DOWN = 's';
    protected static final char LEFT = 'a';
    protected static final char RIGHT = 'd';

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.hp = new Resource(healthCapacity);
        this.hp.setColor(BarGenerator.Color.RED);
        this.hp.addAmount(healthCapacity);
        this.attack = attack;
        this.defense = defense;
    }

    public void initialize(Position position, MessageCallback messageCallback, BoardCallbacks boardCallbacks){
        this.position = position;
        this.boardCallbacks = boardCallbacks;
        this.messageCallback = messageCallback;
    }

    protected void battle(Unit u){
        u.takeDmg(rollAttack(), u);
    }

    public void takeDmg(int atk, Unit attacker){
        int def = rollDefence();
        int dmg = atk - def > 0 ? atk - def : 0;
        this.hp.takeAmount(dmg);
        if(hp.getCurrAmount() <= 0)
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
		boardCallbacks.swap(this, e);
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void visit(Wall w){
        ;
    }
    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

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

    protected void move(char direction)
    {
        switch (direction)
        {
            case UP:
                interact(boardCallbacks.getTile(position.getX(), position.getY() - 1));
                break;
            case DOWN:
                interact(boardCallbacks.getTile(position.getX(), position.getY() + 1));
                break;
            case LEFT:
                interact(boardCallbacks.getTile(position.getX() - 1, position.getY()));
                break;
            case RIGHT:
                interact(boardCallbacks.getTile(position.getX() + 1, position.getY()));
                break;
            default:
        }
    }
}
