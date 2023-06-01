package BusinessLayer;

public class Position implements Comparable<Position>{
    private int x;
    private int y;

    public double range(Position p){
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }
    @Override
    public int compareTo(Position o) {
        /*if(this.x - o.x != 0) return this.x - o.x;
        return this.y - o.y;*/
        return -1;
    }
}
