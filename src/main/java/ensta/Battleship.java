package ensta;

public class Battleship  extends AbstractShip {
    public Battleship( Oriontation oriontation) {
        super('B', "Battleship",4, oriontation);
    }
    public Battleship( ) {
        super('B', "Battleship",4, Oriontation.valueOf("EAST"));
    }

}
