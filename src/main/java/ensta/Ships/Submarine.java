package ensta.Ships;

public class Submarine  extends AbstractShip {
    public Submarine(  Oriontation oriontation) {
        super('S', "Submarine", 3, oriontation);
    }
    public Submarine(  ) {
        super('S', "Submarine", 3,Oriontation.valueOf("EAST"));
    }
}
