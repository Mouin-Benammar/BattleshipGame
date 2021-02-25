package ensta;

import java.util.NoSuchElementException;

public enum Oriontation  {
    NORTH("NORTH", 0), SOUTH("SOUTH", 1), EAST("EAST", 2),WEST("WEST", 3);
    public  String val;
    public int val2;
    private Oriontation(String s,int x){
        this.val=s;
        this.val2=x;
    }
    public static Oriontation fromInt(int value) {
        for (Oriontation o : Oriontation.values()) {
            if (o.val2 == value) {
                return o;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }
    public String toString() {
        return this.val;
    }
}
