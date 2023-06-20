package BusinessLayer.Tiles.Units;

public interface Mortal {
    public void onDeath(Player killer);
    public void onDeath(Enemy killer);
}
