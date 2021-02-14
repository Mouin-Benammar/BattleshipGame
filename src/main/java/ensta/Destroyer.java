package ensta;

public class Destroyer extends AbstractShip {
    public Destroyer(Oriontation oriontation) {
        super('D', "Destroyer", 2, oriontation);
    }
    public Destroyer() {
        super('D', "Destroyer", 2,  Oriontation.valueOf("EAST"));
    }

}
