package ensta;

public class Submarine  extends AbstractShip{
    public Submarine(  Oriontation oriontation) {
        super('S', "Submarine", 3, oriontation);
    }
    public Submarine(  String name, int size[]) {
        super('S', "Submarine", 3,Oriontation.valueOf("EAST"));
    }
}
