package BusinessLayer.Tiles.Units;

import BusinessLayer.BarGenerator;
import BusinessLayer.Callbacks.EnemiesInRange;
import BusinessLayer.Visitor;

import java.awt.*;
import java.util.Scanner;

public abstract class Player extends Unit{
    protected Integer xp;
    protected Integer lvl;
    private Scanner in;

    protected static final char SPECIAL = 'e';

    protected EnemiesInRange enemiesInRange;
    protected Player(String name, int healthCapacity, int attack, int defense) {
        super('@', name, healthCapacity, attack, defense);
        xp = 0;
        lvl = 1;
        in = new Scanner(System.in);
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath(Unit killer) {
        msgCallback.send(this.name + "Killed by " + killer.getName());
        msgCallback.send("GAME OVER");
        tile = 'X';
        System.exit(0);
    }

    @Override
    public void visit(Player p) {
        //we don't have multiplayer!;
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

        xp -= 50*lvl;
        lvl++;
        hp.updatePool(hp.getPool() + 10*lvl);
        hp.addAmount(hp.getPool());
        attack += 4*lvl;
        defense += lvl;
    }

    public void addExp(int val){
        xp += val;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public abstract void castSpecial();


    public void onGameTick()
    {
        msgCallback.send("Enter your move:");
        char move = in.next().charAt(0);
        switch (move)
        {
            case SPECIAL:
                castSpecial();
                break;
            default:
                move(move);
        }
    }

    @Override
    public String toString()
    {
        return BarGenerator.Color.GREEN + super.toString() + BarGenerator.Color.RESET;
    }
}
