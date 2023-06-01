package BusinessLayer;

public class Empty extends Tile{

    public Empty(){
        super('.');
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
