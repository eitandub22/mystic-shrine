package BusinessLayer;

public class Resource {
    private int pool;
    private int currAmount;
    private BarGenerator.Color color;

    public Resource(int pool)
    {
        this.pool = pool;
        this.currAmount = 0;
    }

    public void setColor(BarGenerator.Color color)
    {
        this.color = color;
    }


    public void addAmount(int toAdd)
    {
        this.currAmount = Math.min(this.currAmount + toAdd, this.pool);
    }

    public void updatePool(int newPool)
    {
        this.pool = newPool;
    }

    public void takeAmount(int toTake)
    {
        this.currAmount = Math.max(0, this.currAmount - toTake);
    }

    public int getPool()
    {
        return this.pool;
    }

    public int getCurrAmount()
    {
        return currAmount;
    }

    @Override
    public String toString()
    {
        return "";
    }
}
