package BusinessLayer;

public interface Visitor {
    public void visit(Empty empty);
    public void visit(Wall wall);

    //public void visit(Player player);

    //public void visit(Enemy enemy);
}
