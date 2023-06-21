package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.BoardStuff.EnemiesInRange;
import BusinessLayer.Visitor;
import FrontEnd.MessageCallback;
import FrontEnd.InputReader;

import java.util.Scanner;

public abstract class Player extends Unit implements HeroicUnit{
    protected Integer xp;
    protected Integer lvl;
    private InputReader inputReader;

    protected static final char SPECIAL = 'e';

    protected EnemiesInRange enemiesInRange;
    protected Player(String name, int healthCapacity, int attack, int defense) {
        super('@', name, healthCapacity, attack, defense);
        xp = 0;
        lvl = 1;
    }

    public void initializeReader(InputReader inputReader){
        this.inputReader = inputReader;
    }

    @Override
    public void kill(Mortal victim)
    {
        victim.onDeath(this);
    }
    @Override
    public void onDeath(Enemy killer) {
        messageCallback.send(this.name + "was slain by " + killer.getName());
        messageCallback.send("GAME OVER");
        tile = 'X';
        System.exit(0);//change to deathcallback
    }
    @Override
    public void onDeath(Player player)
    {
        ;
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
        if(xp < lvl*50)
        {
            return;
        }

        messageCallback.send("HP: " + hp.getPool() + " -> " + hp.getPool()+gainedHP());
        messageCallback.send("Atk: " + attack + " -> " + attack+gainedHP());
        messageCallback.send("Defence: " + defense + " -> " + defense+gainedHP());

        xp -= 50*lvl;
        lvl++;
        hp.updatePool(hp.getPool() + gainedHP());
        hp.addAmount(hp.getPool());
        attack += gainedAttack();
        defense += gainedDefence();
    }

    public int gainedAttack()
    {
        return 4*lvl;
    }
    public int gainedHP()
    {
        return 10*lvl;
    }
    public int gainedDefence()
    {
        return lvl;
    }


    public void addExp(int val){
        xp += val;
        levelUp();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }



    public void onGameTick()
    {
        messageCallback.send("Enter your move:");
        char PlrMove = inputReader.read().charAt(0);
        switch (PlrMove)
        {
            case SPECIAL:
                castSpecial();
                break;
            default:
                move(PlrMove);
        }
    }

    @Override
    public String toString()
    {
        return BarGenerator.Color.GREEN + super.toString() + BarGenerator.Color.RESET;
    }
}
