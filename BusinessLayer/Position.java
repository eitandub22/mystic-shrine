package BusinessLayer;

public class Position implements Comparable<Position>{
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public double range(Position p){
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }
    @Override
    public int compareTo(Position o) {
        if(this.y - o.y != 0) return this.y - o.y;
        return this.x - o.x;
    }
}
