package ensta;

public class Carrier  extends AbstractShip{

    public Carrier(  Oriontation oriontation) {
        super('C', "Carrier", 5, oriontation);

    }
    public Carrier( ) {
        super('C', "Carrier", 5, Oriontation.valueOf("EAST"));

    }

}
