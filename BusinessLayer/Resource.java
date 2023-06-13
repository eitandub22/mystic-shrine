package BusinessLayer;

public class Resource {
    private int pool;
    private int currAmount;

    public Resource(int pool)
    {
        this.pool = pool;
        this.currAmount = 0;
    }

    public void addAmount(int toAdd)
    {
        this.currAmount = Math.min(this.currAmount + toAdd, this.pool);
    }

    public void updatePool(int newPool)
    {
        this.pool = newPool;
        this.currAmount = Math.min(this.currAmount, this.pool);
    }

    public void takeAmount(int toTake)
    {
        this.currAmount = Math.max(0, this.currAmount - toTake);
    }




}
