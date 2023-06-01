package BusinessLayer;

public class Wall extends Tile{
    public Wall(){
        super('#');
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
