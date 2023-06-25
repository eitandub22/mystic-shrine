package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.BoardStuff.BoardCallbacks;
import BusinessLayer.Position;
import BusinessLayer.Resource;
import BusinessLayer.Tiles.EnvironmentObjects.Empty;
import BusinessLayer.Tiles.EnvironmentObjects.Wall;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Visitor;
import FrontEnd.FronEndCallbacks;

public abstract class Unit extends Tile implements Visitor, Killer, Mortal{
    protected String name;
    protected Resource hp;
    protected int attack;
    protected int defense;
    protected BoardCallbacks boardCallbacks;
    protected FronEndCallbacks fronEndCallbacks;
    protected double pierce;//hidden stat, because we need some

    protected static final char UP = 'w';
    protected static final char DOWN = 's';
    protected static final char LEFT = 'a';
    protected static final char RIGHT = 'd';
    protected static final char NOTHING = 'q';


    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.hp = new Resource(healthCapacity);
        this.hp.setColor(BarGenerator.Color.RED);
        this.hp.addAmount(healthCapacity);
        this.attack = attack;
        this.defense = defense;
        this.pierce = 0;
    }

    public void initialize(Position position, FronEndCallbacks fronEndCallbacks, BoardCallbacks boardCallbacks){
        this.position = position;
        this.boardCallbacks = boardCallbacks;
        this.fronEndCallbacks = fronEndCallbacks;
    }

    protected void battle(Unit u){
        fronEndCallbacks.displayMessage(getName() + " VS. " + u.getName());
        fronEndCallbacks.displayMessage(describe());
        fronEndCallbacks.displayMessage(u.describe());

        int atk = rollAttack();
        fronEndCallbacks.displayMessage(getName() + " Rolled " + atk + " ATTACK!");
        u.takeDmg(atk, this);
        if(u.getHealth() <= 0)
        {
            boardCallbacks.swap(this, u);
        }
    }

    public void takeDmg(int atk, Unit attacker){
        int def = rollDefence();
        int dmg = Math.max(atk - (int)((1-pierce) * def), 0);
        this.hp.takeAmount(dmg);
        fronEndCallbacks.displayMessage(getName() + " ROLLED " + def + " DEFENCE and took " + dmg +" DMG from " + attacker.getName() + "\n");
        if(hp.getCurrAmount() <= 0)
        {
            attacker.kill(this);
        }
    }

    public int rollAttack(){
        return (int)(Math.random() * (getAttack() + 1));
    }
    public int rollDefence(){
        return (int)(Math.random() * (getDefense() + 1));
    }

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty e){
        boardCallbacks.swap(this, e);
    }
    public void visit(Wall w){
        ;
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public String describe() {
        BarGenerator bg = new BarGenerator();

        return String.format("%s:\n%s HP: %s/%s  Atk: %d  Def: %d",
                getName(),
                bg.genBar(hp.getCurrAmount(), hp.getPool(), ' ', BarGenerator.Color.RED_BACKGROUND),
                getHealth(), hp.getPool(),
                getAttack(), getDefense());
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

    protected boolean move(char direction)
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
            case NOTHING:
                break;
            default:
                return false;
        }
        return true;
    }

    public abstract void onGameTick();
}
