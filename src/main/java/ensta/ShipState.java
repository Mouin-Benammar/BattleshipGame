package ensta;

import java.io.Serializable;

public class ShipState implements Serializable {
    public void setStruck(boolean struck) {
        this.struck = struck;
    }

    private boolean struck;

    public AbstractShip getReference() {
        return reference;
    }

    private AbstractShip reference;

    public ShipState( AbstractShip reference,boolean struck) {
        this.struck = struck;
        this.reference = reference;
    }

    public boolean isStruck() {
        return struck;
    }

    public void addStrike() {
        this.reference.addStrike();

    }

    public AbstractShip getShip() {
        return reference;
    }

    public void setReference(AbstractShip reference) {
        this.reference = reference;
    }


    public String toString(){
        String s=new String("");

            if(struck) s+=Character.toLowerCase(reference.getLabel()) ;
            else s+= reference.getLabel().toString();
        return s;

    }
    public boolean isSunk(){
     return reference.getSize()==reference.getStrikeCount();
    }

}
