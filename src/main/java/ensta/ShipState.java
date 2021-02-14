package ensta;

public class ShipState {
    private boolean struck;
    private AbstractShip reference;

    public ShipState(boolean struck, AbstractShip reference) {
        this.struck = struck;
        this.reference = reference;
    }

    public boolean isStruck() {
        return struck;
    }

    public void addStruck(boolean struck,int x ,int y) {
        this.reference
        this.struck = struck;
    }

    public AbstractShip getReference() {
        return reference;
    }

    public void setReference(AbstractShip reference) {
        this.reference = reference;
    }
    public void addStrike(){

    }

    public String toString(){

    }
    boolean isSunk(){

    }

}
